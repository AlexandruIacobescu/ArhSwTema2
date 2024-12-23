import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static final String LOG_FILE = "user_interactions.log";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void log(String clientId, String commandType) {
        try (PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            String timestamp = dateFormat.format(new Date());
            out.println(clientId + " " + commandType + " " + timestamp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}