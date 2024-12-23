import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;
import java.nio.file.*;
import java.io.*;

public class Data extends UnicastRemoteObject implements RIData {
    private List<Student> students;
    private List<Course> courses;

    protected Data() throws RemoteException {
        students = new ArrayList<>();
        courses = new ArrayList<>();
        loadStudents("Studenti.txt");
        loadCourses("Cursuri.txt");
    }

    private void loadStudents(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                String[] parts = line.split(" ");
                String id = parts[0];
                String name = parts[1] + " " + parts[2];
                String specialization = parts[3];
                List<String> completedCourses = Arrays.asList(Arrays.copyOfRange(parts, 3, parts.length));
                students.add(new Student(id, name, specialization, completedCourses, new ArrayList<>()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCourses(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                String[] parts = line.split(" ");
                String id = parts[0];
                String section = parts[1];
                String days = parts[2];
                String startTime = parts[3];
                String endTime = parts[4];
                String instructor = parts[5];
                String title = String.join(" ", Arrays.copyOfRange(parts, 6, parts.length));
                courses.add(new Course(id, section, days, startTime, endTime, instructor, title));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> getStudents() { return students; }

    @Override
    public List<Course> getCourses() { return courses; }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Data", new Data());
            System.out.println("Data tier is running.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}