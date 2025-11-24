package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import edu.ccrm.exception.EnrollmentException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EnrollmentService {

    private final List<Enrollment> enrollments = new ArrayList<>();
    private final StudentService studentService;
    private final CourseService courseService;

    // Constructor to get instances of other services
    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    /**
     * Enrolls a student in a course after checking business rules.
     * @param studentRegNo The registration number of the student.
     * @param courseCode The code of the course.
     * @throws EnrollmentException if the enrollment cannot be completed.
     */
    public void enrollStudent(String studentRegNo, String courseCode) throws EnrollmentException {
        Student student = studentService.findStudentByRegNo(studentRegNo)
                .orElseThrow(() -> new EnrollmentException("Enrollment failed: Student not found."));

        Course course = courseService.findCourseByCode(courseCode)
                .orElseThrow(() -> new EnrollmentException("Enrollment failed: Course not found."));

        // Rule: Check if the student is already enrolled in this course
        boolean alreadyEnrolled = enrollments.stream()
                .anyMatch(e -> e.getStudent().equals(student) && e.getCourse().equals(course));

        if (alreadyEnrolled) {
            throw new EnrollmentException("Enrollment failed: Student is already enrolled in this course.");
        }

        // If all rules pass, create and add the new enrollment
        Enrollment newEnrollment = new Enrollment(student, course);
        enrollments.add(newEnrollment);
    }

    /**
     * Finds all enrollments for a specific student.
     * @param student The student to find enrollments for.
     * @return A list of the student's enrollments.
     */
    public List<Enrollment> getEnrollmentsByStudent(Student student) {
        return enrollments.stream()
                .filter(e -> e.getStudent().equals(student))
                .collect(Collectors.toList());
    }
}