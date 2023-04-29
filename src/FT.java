import org.apache.commons.math.complex.Complex;
public class FT {
    private final int N;  // number of samples
    private final double[] x;  // input signal
    private Complex[] spectrum;
    private Complex[] signal;
    public FT(double[] x) {
        N = x.length;
        this.x = x;
    }

    public void transform() {
        int N = signal.length;

        for (int k = 0; k < N; k++) {
            Complex sum = new Complex(0, 0);
            for (int n = 0; n < N; n++) {
                double angle = 2 * Math.PI * k * n / N;
                Complex c = new Complex(Math.cos(angle), -Math.sin(angle));
                sum = sum.add(signal[n].multiply(c));
            }
            spectrum[k] = sum;
        }
    }
    public Complex[] amplitudes() {
        int N = spectrum.length;
        Complex[] amplitudes = new Complex[N];

        for (int k = 0; k < N; k++) {
            amplitudes[k] = spectrum[k];
        }

        return amplitudes;
    }

    public double[] frequencies(double dt) {
        int N = spectrum.length;
        double[] frequencies = new double[N];

        for (int k = 0; k < N; k++) {
            frequencies[k] = k / (N * dt);
        }

        return frequencies;
    }

    // Returns the complex Fourier coefficients for the input signal
    public Complex[] coefficients() {
        Complex[] c = new Complex[N];
        for (int k = 0; k < N; k++) {
            Complex sum = new Complex(0, 0);
            for (int n = 0; n < N; n++) {
                Complex wn = new Complex(0, -2 * Math.PI * k * n / N).exp();
                sum = sum.add(wn.multiply(x[n]));
            }
            c[k] = sum;
        }
        return c;
    }

    // Returns the power spectrum of the input signal
    public double[] powerSpectrum() {
        Complex[] c = coefficients();
        double[] psd = new double[N/2];
        for (int i = 0; i < N/2; i++) {
            psd[i] = Math.pow(c[i].abs(), 2) + Math.pow(c[N-i-1].abs(), 2);
        }
        return psd;
    }

    // Returns the reconstructed signal from the Fourier coefficients
    public double[] signal() {
        Complex[] c = coefficients();
        double[] signal = new double[N];
        for (int n = 0; n < N; n++) {
            Complex sum = new Complex(0, 0);
            for (int k = 0; k < N; k++) {
                Complex wn = new Complex(0, 2 * Math.PI * k * n / N).exp();
                sum = sum.add(wn.multiply(c[k]));
            }
            signal[n] = sum.getReal() / N;
        }
        return signal;
    }
}