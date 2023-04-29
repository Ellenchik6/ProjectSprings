public class ConverterFloat extends Converter {
    @Override
    public double evaluateDecimalValue(double[] frequencyAmplitudes) {
        int n = frequencyAmplitudes.length;
        int integerPartLength = n / 2;
        int fractionalPartLength = n - integerPartLength;
        double decimalValue = 0.0;
        // Calculate the decimal value for the integer part
        for (int i = 0; i < integerPartLength; i++) {
            double digitValue = frequencyAmplitudes[i] * Math.pow(2, integerPartLength - i - 1);
            decimalValue += digitValue;
        }
        // Calculate the decimal value for the fractional part
        for (int i = integerPartLength; i < n; i++) {
            double digitValue = frequencyAmplitudes[i] * Math.pow(2, -(i - integerPartLength + 1));
            decimalValue += digitValue;
        }
        return decimalValue;
    }

    @Override
    public Spring[] convertToSprings(int[] binarySequence) {
        int n = binarySequence.length;
        int integerPartLength = n / 2;
        int fractionalPartLength = n - integerPartLength;
        Spring[] springs = new Spring[n];
        // Create springs for the integer part
        for (int i = 0; i < integerPartLength; i++) {
            springs[i] = new Spring();
        }
        // Create springs for the fractional part
        for (int i = integerPartLength; i < n; i++) {
            springs[i] = new Spring();
        }
        return springs;
    }
}
