package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentService {

    // This list will act as our in-memory database for students
    private final List<Student> studentList = new ArrayList<>();

    /**
     * Adds a new student to the list.
     * Prevents adding a student if another student with the same registration number already exists.
     * @param student The student object to add.
     * @return true if the student was added successfully, false otherwise.
     */
    public boolean addStudent(Student student) {
        // Check for duplicates before adding
        if (findStudentByRegNo(student.getRegNo()).isEmpty()) {
            studentList.add(student);
            return true;
        }
        return false; // A student with this regNo already exists
    }

    /**
     * Finds a student by their registration number.
     * @param regNo The registration number to search for.
     * @return An Optional containing the student if found, otherwise an empty Optional.
     */
    public Optional<Student> findStudentByRegNo(String regNo) {
        for (Student student : studentList) {
            if (student.getRegNo().equalsIgnoreCase(regNo)) {
                return Optional.of(student); // Found the student
            }
        }
        return Optional.empty(); // No student found
    }

    /**
     * Returns a list of all students.
     * @return A copy of the list of all students.
     */
    public List<Student> getAllStudents() {
        // Return a copy to prevent external modification of the original list
        return new ArrayList<>(studentList);
    }
}