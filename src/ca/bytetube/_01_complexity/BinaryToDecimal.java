package ca.bytetube._01_complexity;

public class BinaryToDecimal {
    public static void main(String[] args){
        String binary = "11011";
        int decimal = convertBinaryToDecimal(binary);
        System.out.println("Decimal of " + binary +" is " + decimal);
    }
//    public static int convertBinaryToDecimal(String binary){
//        int decimal = 0;
//        int power = 0 ;
//
//        for (int i = binary.length() - 1 ; i >=0 ; i--) {
//            char bit = binary.charAt(i);
//
//            if (bit == '1'){
//                decimal += Math.pow(2, power);
//            }
//
//            power++;
//        }
//
//        return decimal;
//    }
    public static int convertBinaryToDecimal(String binary){
        int decimal = 0;
        int power = 0;

        for (int i = binary.length()-1; i >= 0 ; i--) {
            char bit = binary.charAt(i);
            if (bit == '1'){
                decimal += Math.pow(2, power);
            }

            power++;
        }
        return decimal;
    }

}
