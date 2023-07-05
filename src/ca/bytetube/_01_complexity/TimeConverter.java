package ca.bytetube._01_complexity;

public class TimeConverter {
    public static void main(String[] args){
        int inputSeconds = 86399;
        convertSecondsToTime(inputSeconds);
    }
//    public static void convertSecondsToTime(int seconds){
//        int hours = seconds / 3600;
//        int minutes = (seconds % 3600) / 60;
//        int remainder = (seconds % 3600) % 60;
//
//        String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, remainder);
//        System.out.println("Converted Time: " + formattedTime);
//    }

    public static void convertSecondsToTime(int seconds){
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int remain = (seconds % 3600) % 60;

        String fomarttedTime = String.format("%02d:%02d:%02d", hours, minutes, remain);
        System.out.println("converted time: " + fomarttedTime);
    }

}
