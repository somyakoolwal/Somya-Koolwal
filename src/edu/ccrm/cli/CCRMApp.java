package edu.ccrm.cli;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import edu.ccrm.exception.EnrollmentException;
import edu.ccrm.io.DataIOService;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.EnrollmentService;
import edu.ccrm.service.StudentService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CCRMApp {

    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);
    private static final DataIOService dataIOService = new DataIOService();

    public static void main(String[] args) {
        System.out.println("Welcome to the Campus Course & Records Manager (CCRM)!");
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            printMainMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> manageStudents(scanner);
                case 2 -> manageCourses(scanner);
                case 3 -> manageEnrollments(scanner);
                case 4 -> manageDataUtilities(scanner);
                case 9 -> {
                    exit = true;
                    System.out.println("Thank you for using CCRM. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void printMainMenu() {
        System.out.println("\n--- CCRM Main Menu ---");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Manage Enrollments");
        System.out.println("4. Data Utilities");
        System.out.println("9. Exit");
        System.out.println("----------------------");
    }

    private static void manageStudents(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Manage Students Menu ---");
            System.out.println("1. Add New Student");
            System.out.println("2. List All Students");
            System.out.println("9. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addStudent(scanner);
                case 2 -> listAllStudents();
                case 9 -> back = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addStudent(Scanner scanner) {
        System.out.println("\n-- Add New Student --");
        System.out.print("Enter Student ID (integer): ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Full Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Registration Number: ");
        String regNo = scanner.nextLine();
        Student newStudent = new Student(id, name, email, regNo);
        if (studentService.addStudent(newStudent)) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Error: A student with that registration number already exists.");
        }
    }

    private static void listAllStudents() {
        System.out.println("\n-- List of All Students --");
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                System.out.println(student.getProfile());
            }
        }
    }

    private static void manageCourses(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Manage Courses Menu ---");
            System.out.println("1. Add New Course");
            System.out.println("2. List All Courses");
            System.out.println("3. Find Course by Code");
            System.out.println("9. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addCourse(scanner);
                case 2 -> listAllCourses();
                case 3 -> findCourseByCode(scanner);
                case 9 -> back = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addCourse(Scanner scanner) {
        System.out.println("\n-- Add New Course --");
        System.out.print("Enter Course Code (e.g., CS101): ");
        String code = scanner.nextLine();
        System.out.print("Enter Course Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Credits (integer): ");
        int credits = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Department: ");
        String dept = scanner.nextLine();
        Course newCourse = new Course.Builder(code, title).credits(credits).department(dept).build();
        if (courseService.addCourse(newCourse)) {
            System.out.println("Course added successfully!");
        } else {
            System.out.println("Error: A course with that code already exists.");
        }
    }

    private static void listAllCourses() {
        System.out.println("\n-- List of All Courses --");
        List<Course> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            courses.forEach(System.out::println);
        }
    }

    private static void findCourseByCode(Scanner scanner) {
        System.out.println("\n-- Find Course by Code --");
        System.out.print("Enter Course Code: ");
        String code = scanner.nextLine();
        Optional<Course> courseOpt = courseService.findCourseByCode(code);
        if (courseOpt.isPresent()) {
            System.out.println("Course Found: " + courseOpt.get());
        } else {
            System.out.println("No course found with code: " + code);
        }
    }

    private static void manageEnrollments(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Manage Enrollments Menu ---");
            System.out.println("1. Enroll Student in Course");
            System.out.println("2. List Enrollments for a Student");
            System.out.println("9. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> enrollStudentInCourse(scanner);
                case 2 -> listEnrollmentsForStudent(scanner);
                case 9 -> back = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void enrollStudentInCourse(Scanner scanner) {
        System.out.println("\n-- Enroll Student in Course --");
        System.out.print("Enter Student Registration Number: ");
        String regNo = scanner.nextLine();
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        try {
            enrollmentService.enrollStudent(regNo, courseCode);
            System.out.println("Enrollment successful!");
        } catch (EnrollmentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listEnrollmentsForStudent(Scanner scanner) {
        System.out.println("\n-- List Enrollments for a Student --");
        System.out.print("Enter Student Registration Number: ");
        String regNo = scanner.nextLine();
        Optional<Student> studentOpt = studentService.findStudentByRegNo(regNo);
        if (studentOpt.isPresent()) {
            List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudent(studentOpt.get());
            if (enrollments.isEmpty()) {
                System.out.println("This student is not enrolled in any courses.");
            } else {
                System.out.println("Courses enrolled by " + studentOpt.get().getFullName() + ":");
                for (Enrollment enrollment : enrollments) {
                    System.out.println("- " + enrollment.getCourse().toString());
                }
            }
        } else {
            System.out.println("Error: Student not found.");
        }
    }

    private static void manageDataUtilities(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Data Utilities Menu ---");
            System.out.println("1. Export All Data");
            System.out.println("2. Create Backup");
            System.out.println("9. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> exportAllData();
                case 2 -> createBackup();
                case 9 -> back = true;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void exportAllData() {
        System.out.println("\nExporting all data...");
        try {
            dataIOService.exportData(studentService.getAllStudents(), courseService.getAllCourses());
        } catch (IOException e) {
            System.err.println("An error occurred during data export: " + e.getMessage());
        }
    }
    
    private static void createBackup() {
        System.out.println("\nCreating backup...");
        try {
            long backupSize = dataIOService.backupData();
            System.out.printf("Total size of backup: %d bytes%n", backupSize);
        } catch (IOException e) {
            System.err.println("An error occurred during backup: " + e.getMessage());
        }
    }
}