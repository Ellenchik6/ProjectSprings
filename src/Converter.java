import org.apache.commons.math.complex.Complex;
public abstract class Converter {
    public abstract double evaluateDecimalValue(double[] frequencyAmplitudes);
    public abstract Spring[] convertToSprings(int[] binarySequence);

    public double[] computeOscillations(Spring[] springs, double t, double dt, double x0, double v0) {
        int numSprings = springs.length;
        double[] x = new double[numSprings + 1];
        double[] v = new double[numSprings + 1];
        x[0] = x0;
        v[0] = v0;
        for (int i = 0; i < numSprings; i++) {
            Spring spring = springs[i];
            double[] springOscillations = spring.move(t, dt, x[i], v[i]);
            x[i + 1] = springOscillations[springOscillations.length - 1];
            v[i + 1] = (x[i + 1] - x[i]) / dt;
        }
        return x;
    }


    public double[] computeFrequencyAmplitudes(double[] oscillations,  double dt) {
        int N = oscillations.length;
        FT ft = new FT(oscillations);
        ft.transform();
        Complex[] amplitudes = ft.amplitudes();
        double[] frequencies = ft.frequencies(dt);
        int numComponents = amplitudes.length;
        double[] frequencyAmplitudes = new double[numComponents];
        for (int i = 0; i < numComponents; i++) {
            double amplitude = Math.sqrt(amplitudes[i].getReal() * amplitudes[i].getReal() + amplitudes[i].getImaginary() * amplitudes[i].getImaginary()) / N;
            frequencyAmplitudes[i] = amplitude;
        }
        return frequencyAmplitudes;
    }

}
