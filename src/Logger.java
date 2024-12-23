import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger extends UnicastRemoteObject implements RILogger {
    private static final String LOG_FILE = "server_user_interactions.log";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    protected Logger() throws RemoteException {
        super();
    }

    @Override
    public synchronized void log(String clientId, String commandType) throws RemoteException {
        try (PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            String timestamp = dateFormat.format(new Date());
            out.println(clientId + " " + commandType + " " + timestamp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Logger logger = new Logger();
            java.rmi.Naming.rebind("Logger", logger);
            System.out.println("Logger is ready.");
        } catch (Exception e) {
            System.out.println("Logger failed: " + e);
        }
    }
}