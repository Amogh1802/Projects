import java.util.*;

class Course {
    String code, title, description, schedule;
    int capacity;
    int enrolled;

    Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolled = 0;
    }
}

class Student {
    String id, name;
    List<String> registeredCourses;

    Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
}

public class CourseRegistrationSystem {
    static List<Course> courses = new ArrayList<>();
    static List<Student> students = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void addCourse(String code, String title, String description, int capacity, String schedule) {
        courses.add(new Course(code, title, description, capacity, schedule));
    }

    public static void registerStudent(String studentId, String studentName) {
        students.add(new Student(studentId, studentName));
    }

    public static void listCourses() {
        for (Course course : courses) {
            System.out.println(course.code + ": " + course.title + " (" + course.enrolled + "/" + course.capacity + ") - " + course.schedule);
        }
    }

    public static void enrollStudent(String studentId, String courseCode) {
        Student student = students.stream().filter(s -> s.id.equals(studentId)).findFirst().orElse(null);
        Course course = courses.stream().filter(c -> c.code.equals(courseCode)).findFirst().orElse(null);

        if (student == null || course == null) {
            System.out.println("Invalid student ID or course code.");
            return;
        }

        if (course.enrolled < course.capacity) {
            student.registeredCourses.add(courseCode);
            course.enrolled++;
            System.out.println("Enrollment successful.");
        } else {
            System.out.println("Course is full.");
        }
    }

    public static void dropCourse(String studentId, String courseCode) {
        Student student = students.stream().filter(s -> s.id.equals(studentId)).findFirst().orElse(null);
        Course course = courses.stream().filter(c -> c.code.equals(courseCode)).findFirst().orElse(null);

        if (student == null || course == null || !student.registeredCourses.contains(courseCode)) {
            System.out.println("Invalid request.");
            return;
        }

        student.registeredCourses.remove(courseCode);
        course.enrolled--;
        System.out.println("Course dropped successfully.");
    }

    public static void displayStudentDetails(String studentId) {
        Student student = students.stream().filter(s -> s.id.equals(studentId)).findFirst().orElse(null);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Student ID: " + student.id);
        System.out.println("Student Name: " + student.name);
        System.out.println("Registered Courses:");
        for (String courseCode : student.registeredCourses) {
            Course course = courses.stream().filter(c -> c.code.equals(courseCode)).findFirst().orElse(null);
            if (course != null) {
                System.out.println(course.code + ": " + course.title);
            }
        }
    }

    public static void displayMenu() {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. List Courses");
            System.out.println("2. Register Student");
            System.out.println("3. Enroll in Course");
            System.out.println("4. Drop Course");
            System.out.println("5. Display Student Details");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String studentName = scanner.nextLine();
                    registerStudent(studentId, studentName);
                    System.out.println("Student registered successfully.");
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    String enrollStudentId = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    String enrollCourseCode = scanner.nextLine();
                    enrollStudent(enrollStudentId, enrollCourseCode);
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    String dropStudentId = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    String dropCourseCode = scanner.nextLine();
                    dropCourse(dropStudentId, dropCourseCode);
                    break;
                case 5:
                    System.out.print("Enter Student ID: ");
                    String displayStudentId = scanner.nextLine();
                    displayStudentDetails(displayStudentId);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        addCourse("CS101", "Intro to Programming", "Basic programming concepts", 30, "MWF 10AM");
        addCourse("MATH201", "Calculus", "Differentiation & Integration", 40, "TTH 2PM");

        displayMenu();
    }
}
