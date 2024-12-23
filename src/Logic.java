import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

public class Logic extends UnicastRemoteObject implements RILogic {
    private RIData data;
    private final Logger logger = Logger.getInstance();

    protected Logic(RIData data) throws RemoteException {
        this.data = data;
    }

    @Override
    public synchronized List<Student> listStudents() throws RemoteException {
        logger.log("System", "ListStudents");
        return data.getStudents();
    }

    @Override
    public synchronized List<Course> listCourses() throws RemoteException {
        logger.log("System", "ListCourses");
        return data.getCourses();
    }

    @Override
    public synchronized List<Student> getStudentsForCourse(String courseId) throws RemoteException {
        logger.log("System", "GetStudentsForCourse");
        List<Student> enrolled = new ArrayList<>();
        for (Student student : data.getStudents()) {
            if (student.getEnrolledCourses().contains(courseId)) {
                enrolled.add(student);
            }
        }
        return enrolled;
    }

    @Override
    public synchronized List<String> getStudentCourses(String studentId) throws RemoteException {
        logger.log("System", "GetStudentCourses");
        for (Student student : data.getStudents()) {
            if (student.getId().equals(studentId)) {
                return student.getEnrolledCourses();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public synchronized List<String> getCompletedCourses(String studentId) throws RemoteException {
        logger.log("System", "GetCompletedCourses");
        for (Student student : data.getStudents()) {
            if (student.getId().equals(studentId)) {
                return student.getCompletedCourses();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public synchronized void registerStudentToCourse(String studentId, String courseId, String clientId) throws RemoteException {
        logger.log(clientId, "RegisterStudentToCourse");
        for (Student student : data.getStudents()) {
            if (student.getId().equals(studentId)) {
                if (!student.getEnrolledCourses().contains(courseId)) {
                    student.getEnrolledCourses().add(courseId);
                }
                return;
            }
        }
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            RIData data = (RIData) registry.lookup("Data");
            registry.rebind("Logic", new Logic(data));
            System.out.println("Logic tier is running.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}