import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {
    private static Logger instance;
    private BufferedWriter writer;

    private Logger() {
        try {
            writer = new BufferedWriter(new FileWriter("log.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public synchronized void log(String clientId, String commandType) {
        try {
            String timestamp = LocalDateTime.now().toString();
            writer.write(String.format("%s, %s, %s%n", clientId, commandType, timestamp));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
