package edu.ccrm.domain;

import java.util.ArrayList;
import java.util.List;

// 'Student' inherits from 'Person'
public class Student extends Person {

    private String regNo;
    private StudentStatus status;
    private List<Course> enrolledCourses;

    public Student(int id, String fullName, String email, String regNo) {
        // Calling the constructor of the parent class (Person)
        super(id, fullName, email);
        this.regNo = regNo;
        this.status = StudentStatus.ACTIVE;
        this.enrolledCourses = new ArrayList<>();
    }

    // Overriding the abstract method from Person (Polymorphism)
    @Override
    public String getProfile() {
        return "Student Profile: " + getFullName() + " | Registration No: " + regNo;
    }
    public String getRegNo() {
        return regNo;
    }

    // ... other methods for student-specific logic will go here ...
}

// You can create this enum in a new file (StudentStatus.java) in the same package
