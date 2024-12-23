import java.io.Serializable;

public class Course implements Serializable {
    private String id;
    private String section;
    private String days;
    private String startTime;
    private String endTime;
    private String instructor;
    private String title;

    public Course(String id, String section, String days, String startTime, String endTime, String instructor, String title) {
        this.id = id;
        this.section = section;
        this.days = days;
        this.startTime = startTime;
        this.endTime = endTime;
        this.instructor = instructor;
        this.title = title;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getInstructor() { return instructor; }
}