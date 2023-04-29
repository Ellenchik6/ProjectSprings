public class Main {
    public static void main(String[] args) {
        double[] bits1 = {0, 1, 0, 1, 0, 1, 0, 1};
        double[] bits2 = {1, 1, 1, 1, 0, 0, 0, 0};
        double[] bits3 = {0, 0, 0, 0, 0, 0, 0, 0};
        double[] bits4 = {1, 0, 1, 0};
        double[] bits5 = {1, 1, 1, 0, 0, 0};
        double[] bits6 = {1, 0, 0, 0, 1, 0, 1, 1, 0};

        // Testing the implemented Converter8Bit class for several cases
        Converter8Bit converter8Bit = new Converter8Bit();
        System.out.println("Testing the implemented Converter8Bit class for several cases");
        // Test Case 1:
        System.out.println("Binary 1010 = Decimal " + converter8Bit.evaluateDecimalValue(bits1));
        // Test Case 2:
        System.out.println("Binary 11110000 = Decimal " + converter8Bit.evaluateDecimalValue(bits2));
        // Test Case 3:
        System.out.println("Binary 00000000 = Decimal " + converter8Bit.evaluateDecimalValue(bits3));
        System.out.println();


        // Testing the implemented ConverterInt class for several cases
        ConverterInt converterInt = new ConverterInt();
        System.out.println("Testing the implemented ConverterInt class for several cases");
        // Test case 1:
        System.out.println("Binary 1010 = Decimal " + converterInt.evaluateDecimalValue(bits4));
        // Test case 2:
        System.out.println("Binary 111000 = Decimal " + converterInt.evaluateDecimalValue(bits5));
        // Test case 3:
        System.out.println("Binary 100001011 = Decimal " + converterInt.evaluateDecimalValue(bits6));
        System.out.println();



        // Testing the implemented ConverterFloat class for several cases
        ConverterFloat converterFloat = new ConverterFloat();
        System.out.println("Testing the implemented ConverterFloat class for several cases");
        // Test case 1:
        double[] binaryIntegerPart = {1, 0, 1};
        int[] binaryFractionalPart = {0, 0, 1, 0, 0, 1};
        double decimalValue1 = converterFloat.evaluateDecimalValue(binaryIntegerPart);
        System.out.println("Binary 101.001 = Decimal " + decimalValue1);

        // Test case 2:
        double[] binaryIntegerPart2 = {1, 1};
        double[] binaryFractionalPart2 = {1, 1, 1, 0};
        double decimalValue2 = converterFloat.evaluateDecimalValue(binaryIntegerPart2);
        System.out.println("Binary -11.11 = Decimal " + decimalValue2);


    }

}