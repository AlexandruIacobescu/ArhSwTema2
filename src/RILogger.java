import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RILogger extends Remote {
    void log(String clientId, String commandType) throws RemoteException;
}