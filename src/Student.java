import java.io.Serializable;
import java.util.List;

public class Student implements Serializable {
    private String id;
    private String name;
    private String specialization;
    private List<String> completedCourses;
    private List<String> enrolledCourses;

    public Student(String id, String name, String specialization, List<String> completedCourses, List<String> enrolledCourses) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.completedCourses = completedCourses;
        this.enrolledCourses = enrolledCourses;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
    public List<String> getCompletedCourses() { return completedCourses; }
    public List<String> getEnrolledCourses() { return enrolledCourses; }
}