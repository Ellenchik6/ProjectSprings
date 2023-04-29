public class Converter8Bit extends Converter {
    private Spring[] springs;
    public Converter8Bit() {
        initializeSprings();
    }

    private void initializeSprings() {
        springs = new Spring[8];
        for (int i = 0; i < 8; i++) {
            springs[i] = new Spring();
        }
    }

    @Override
    public Spring[] convertToSprings(int[] binarySequence) {
        if (binarySequence.length != 8) {
            throw new IllegalArgumentException("Invalid number of bits! Expected 8 bits.");
        }
        Spring[] convertedSprings = new Spring[8];
        for (int i = 0; i < 8; i++) {
            if (binarySequence[i] == 0) {
                convertedSprings[i] = new Spring();
            } else if (binarySequence[i] == 1) {
                convertedSprings[i] = springs[i];
            } else {
                throw new IllegalArgumentException("Invalid bit value! Expected 0 or 1.");
            }
        }
        return convertedSprings;
    }

    @Override
    public double evaluateDecimalValue(double[] frequencyAmplitudes) {
        double decimalValue = 0.0;
        if (frequencyAmplitudes.length != 8) {
            throw new IllegalArgumentException("Invalid number of frequency amplitudes! Expected 8 amplitudes.");
        }
        for (int i = 0; i < 8; i++) {
            decimalValue += frequencyAmplitudes[i] * Math.pow(2, i);
        }
        return decimalValue;
    }
}
