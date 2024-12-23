import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RILogic extends Remote {
    List<Student> listStudents() throws RemoteException;
    List<Course> listCourses() throws RemoteException;
    List<Student> getStudentsForCourse(String courseId) throws RemoteException;
    List<String> getStudentCourses(String studentId) throws RemoteException;
    List<String> getCompletedCourses(String studentId) throws RemoteException;
    void registerStudentToCourse(String studentId, String courseId, String clientId) throws RemoteException;
}
