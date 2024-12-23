import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            RILogic logic = (RILogic) registry.lookup("Logic");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("1. List Students");
                System.out.println("2. List Courses");
                System.out.println("3. Students Registered for a Course");
                System.out.println("4. Courses of a Student");
                System.out.println("5. Completed Courses of a Student");
                System.out.println("6. Register Student to Course");
                System.out.println("7. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        List<Student> students = logic.listStudents();
                        students.forEach(student -> System.out.println(student.getName()));
                    }
                    case 2 -> {
                        List<Course> courses = logic.listCourses();
                        courses.forEach(course -> System.out.println(course.getTitle()));
                    }
                    case 3 -> {
                        System.out.print("Enter Course ID: ");
                        String courseId = scanner.nextLine();
                        List<Student> students = logic.getStudentsForCourse(courseId);
                        students.forEach(student -> System.out.println(student.getName()));
                    }
                    case 4 -> {
                        System.out.print("Enter Student ID: ");
                        String studentId = scanner.nextLine();
                        List<String> courses = logic.getStudentCourses(studentId);
                        courses.forEach(System.out::println);
                    }
                    case 5 -> {
                        System.out.print("Enter Student ID: ");
                        String studentId = scanner.nextLine();
                        List<String> completedCourses = logic.getCompletedCourses(studentId);
                        completedCourses.forEach(System.out::println);
                    }
                    case 6 -> {
                        System.out.print("Enter Student ID: ");
                        String studentId = scanner.nextLine();
                        System.out.print("Enter Course ID: ");
                        String courseId = scanner.nextLine();
                        logic.registerStudentToCourse(studentId, courseId);
                        System.out.println("Student registered successfully.");
                    }
                    case 7 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
