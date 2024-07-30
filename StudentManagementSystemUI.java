import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Student Class
class Student {
    private String name;
    private String rollNumber;
    private String grade;
    private String email;
    private String phoneNumber;

    public Student(String name, String rollNumber, String grade, String email, String phoneNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade +
               ", Email: " + email + ", Phone Number: " + phoneNumber;
    }
}

// StudentManagementSystem Class
class StudentManagementSystem {
    private List<Student> students;

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public boolean removeStudent(String rollNumber) {
        return students.removeIf(student -> student.getRollNumber().equals(rollNumber));
    }

    public Student searchStudent(String rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber().equals(rollNumber)) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    // Save data to a file
    public void saveData(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Student student : students) {
                writer.println(student.getName() + "," + student.getRollNumber() + "," +
                               student.getGrade() + "," + student.getEmail() + "," +
                               student.getPhoneNumber());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving data.");
            e.printStackTrace();
        }
    }

    // Load data from a file
    public void loadData(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Student student = new Student(parts[0], parts[1], parts[2], parts[3], parts[4]);
                    addStudent(student);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading data.");
            e.printStackTrace();
        }
    }
}

//StudentManagementSystemUI Class
public class StudentManagementSystemUI {
    private StudentManagementSystem sms;
    private Scanner scanner;

    public StudentManagementSystemUI() {
        this.sms = new StudentManagementSystem();
        this.scanner = new Scanner(System.in);
        // Optionally, load data from a file at startup
        sms.loadData("students.txt");
    }

    public void start() {
        while (true) {
            System.out.println("Student Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save and Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    sms.saveData("students.txt");
                    System.out.println("Data saved successfully. Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void addStudent() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter roll number: ");
        String rollNumber = scanner.nextLine();
        System.out.print("Enter grade: ");
        String grade = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        if (name.isEmpty() || rollNumber.isEmpty() || grade.isEmpty()) {
            System.out.println("Name, roll number, and grade are required.");
            return;
        }

        Student student = new Student(name, rollNumber, grade, email, phoneNumber);
        sms.addStudent(student);
        System.out.println("Student added successfully.");
    }

    private void removeStudent() {
        System.out.print("Enter roll number of student to remove: ");
        String rollNumber = scanner.nextLine();

        if (sms.removeStudent(rollNumber)) {
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private void searchStudent() {
        System.out.print("Enter roll number to search: ");
        String rollNumber = scanner.nextLine();

        Student student = sms.searchStudent(rollNumber);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
    }

    private void displayAllStudents() {
        sms.displayAllStudents();
    }

    public static void main(String[] args) {
        new StudentManagementSystemUI().start();
    }
}

