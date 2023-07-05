package ca.bytetube._01_complexity;

public class DecimalToBinary {
    public static void main(String[] args){
        int decimal = 27;
        String binary = coverDecimalToBinary(decimal);
        System.out.println("Binary number of " + decimal + " is " + binary);
    }

//    public static String coverDecimalToBinary(int decimal) {
//        if (decimal == 0) {
//            return "0";
//        }
//
//        StringBuilder binary = new StringBuilder();
//
//        while (decimal > 0){
//            int remainder = decimal % 2;
//            binary.insert(0, remainder);
//            decimal /= 2;
//        }
//        return binary.toString();
//    }
    public static String coverDecimalToBinary(int decimal){
        if (decimal == 0){
            return  "0";
        }

        StringBuilder binary = new StringBuilder();

        while (decimal > 0) {
            int remainder = decimal % 2;
            binary.insert(0, remainder);
            decimal /= 2;
        }
        return binary.toString();
    }
}
