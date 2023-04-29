import java.util.Arrays;
public class ConverterInt extends Converter {
    @Override
    public Spring[] convertToSprings(int[] bits) {
        int numSprings = (int) Math.ceil(bits.length / 8.0);
        Spring[] springs = new Spring[numSprings];
        int startIndex = 0;
        for (int i = 0; i < numSprings; i++) {
            int endIndex = Math.min(startIndex + 8, bits.length);
            int[] subBits = Arrays.copyOfRange(bits, startIndex, endIndex);
            Spring spring = createSpring(subBits);
            springs[i] = spring;
            startIndex = endIndex;
        }
        return springs;
    }

    @Override
    public double evaluateDecimalValue(double[] frequencyAmplitudes) {
        int numSprings = frequencyAmplitudes.length;
        double decimalValue = 0.0;
//        double base = Math.pow(2, 8 * numSprings);
        for (int i = 0; i < numSprings; i++) {
            decimalValue += frequencyAmplitudes[i] * Math.pow(2, 8 * (numSprings - i - 1));
        }
        return decimalValue;
    }

    private Spring createSpring(int[] bits) {
        Spring spring = new Spring();
        for (int bit : bits) {
            if (bit == 1) {
                Spring bitSpring = new Spring(1.0);
                spring = spring.inParallel(bitSpring);
            } else {
                Spring bitSpring = new Spring();
                spring = spring.inParallel(bitSpring);
            }
        }
        return spring;
    }
}
