package ca.bytetube._01_complexity;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Timer {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SS");
    public interface Task {
        void execute();
    }

    public static void test(String title, Task task) {
        title = (title == null) ? "" : ("[" + title + "]");
        System.out.println(title);
        System.out.println("start:" + sdf.format(new Date()));
        long start = System.currentTimeMillis();

        task.execute();

        long end = System.currentTimeMillis();
        System.out.println("end:" + sdf.format(new Date()));
        double interval = (end - start) / 1000.00;
        System.out.println("task spend" + interval + "secs");
        System.out.println("==============================");
    }
}
