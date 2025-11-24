package edu.ccrm.domain;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends Person {

    private String department;
    private List<Course> assignedCourses;

    public Instructor(int id, String fullName, String email, String department) {
        // Call the constructor of the parent class (Person)
        super(id, fullName, email);
        this.department = department;
        this.assignedCourses = new ArrayList<>();
    }

    @Override
    public String getProfile() {
        return "Instructor Profile: " + getFullName() + " | Department: " + department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}