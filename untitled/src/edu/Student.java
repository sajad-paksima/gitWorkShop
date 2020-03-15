package edu;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {
    private String studentId;
    private String firstName;
    private String lastName;
    private ArrayList<Course> passedCourses;
    private HashMap<Course, CourseInfo> coursesThisSemester;

    public Student(String studentId, String firstName, String lastName) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.coursesThisSemester = new HashMap<>();
        this.passedCourses = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
    public void takeCourse(Course course){
        coursesThisSemester.put(course, new CourseInfo());
    }

    public ArrayList<Course> getPassedCourses() {
        return passedCourses;
    }

    public HashMap<Course, CourseInfo> getCoursesThisSemester() {
        return coursesThisSemester;
    }

    public String getStudentId() {
        return studentId;
    }
}
