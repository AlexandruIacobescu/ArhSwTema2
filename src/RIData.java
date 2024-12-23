import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RIData extends Remote {
    List<Student> getStudents() throws RemoteException;
    List<Course> getCourses() throws RemoteException;
}

