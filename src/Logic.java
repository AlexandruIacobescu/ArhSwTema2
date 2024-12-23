
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

public class Logic extends UnicastRemoteObject implements RILogic {
    private RIData data;

    protected Logic(RIData data) throws RemoteException {
        this.data = data;
    }

    @Override
    public List<Student> listStudents() throws RemoteException {
        return data.getStudents();
    }

    @Override
    public List<Course> listCourses() throws RemoteException {
        return data.getCourses();
    }

    @Override
    public List<Student> getStudentsForCourse(String courseId) throws RemoteException {
        List<Student> enrolled = new ArrayList<>();
        for (Student student : data.getStudents()) {
            if (student.getEnrolledCourses().contains(courseId)) {
                enrolled.add(student);
            }
        }
        return enrolled;
    }

    @Override
    public List<String> getStudentCourses(String studentId) throws RemoteException {
        for (Student student : data.getStudents()) {
            if (student.getId().equals(studentId)) {
                return student.getEnrolledCourses();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public List<String> getCompletedCourses(String studentId) throws RemoteException {
        for (Student student : data.getStudents()) {
            if (student.getId().equals(studentId)) {
                return student.getCompletedCourses();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public void registerStudentToCourse(String studentId, String courseId) throws RemoteException {
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