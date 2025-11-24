package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Instructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseService {

    // In-memory database for courses
    private final List<Course> courseList = new ArrayList<>();

    /**
     * Adds a new course if a course with the same code doesn't already exist.
     * @param course The course to add.
     * @return true if added, false otherwise.
     */
    public boolean addCourse(Course course) {
        if (findCourseByCode(course.getCode()).isEmpty()) {
            courseList.add(course);
            return true;
        }
        return false;
    }

    /**
     * Finds a course by its unique code.
     * @param courseCode The code to search for.
     * @return An Optional containing the course if found.
     */
    public Optional<Course> findCourseByCode(String courseCode) {
        // Using the Stream API to find a match
        return courseList.stream()
                .filter(course -> course.getCode().equalsIgnoreCase(courseCode))
                .findFirst();
    }

    /**
     * Returns a copy of the list of all courses.
     * @return A list of all courses.
     */
    public List<Course> getAllCourses() {
        return new ArrayList<>(courseList);
    }

    /**
     * Searches for courses taught by a specific instructor.
     * This demonstrates the Stream API filter requirement.
     * @param instructor The instructor to filter by.
     * @return A list of courses taught by the given instructor.
     */
    public List<Course> findCoursesByInstructor(Instructor instructor) {
        return courseList.stream()
                .filter(course -> instructor.equals(course.getInstructor()))
                .collect(Collectors.toList());
    }
}