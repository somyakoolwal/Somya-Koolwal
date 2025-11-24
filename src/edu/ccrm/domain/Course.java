package edu.ccrm.domain;

// This class uses the Builder Pattern
public final class Course {
    private final String code;
    private final String title;
    private final int credits;
    private final String department;
    private Instructor instructor; // We will create the Instructor class later

    // Private constructor, only called by the Builder
    private Course(Builder builder) {
        this.code = builder.code;
        this.title = builder.title;
        this.credits = builder.credits;
        this.department = builder.department;
    }

    // Only getters, making the object immutable
    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public String getDepartment() { return department; }
    public Instructor getInstructor() { return instructor; }
    public void setInstructor(Instructor instructor) { this.instructor = instructor; }


    @Override
    public String toString() {
        return "Course [Code=" + code + ", Title=" + title + ", Credits=" + credits + "]";
    }

    // The static nested Builder class
    public static class Builder {
        private final String code; // Required
        private final String title; // Required
        private int credits;      // Optional
        private String department;  // Optional

        public Builder(String code, String title) {
            this.code = code;
            this.title = title;
        }

        public Builder credits(int credits) {
            this.credits = credits;
            return this; // Allows for chaining methods
        }

        public Builder department(String department) {
            this.department = department;
            return this;
        }

        public Course build() {
            return new Course(this);
        }
    }
}