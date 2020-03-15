package edu;

import java.util.ArrayList;

class CourseInfo{
    private float mark;
    public float getMark() {
        return mark;
    }
    public void setMark(float mark) {
        this.mark = mark;
    }
}

public class Course {
    private String courseName;
    private Professor professor;
    private String semester;
    private ArrayList<String> preCourses;

    public Course(String courseName, Professor professor, String semester, ArrayList<String> preCourses) {
        this.courseName = courseName;
        this.professor = professor;
        this.semester = semester;
        this.preCourses = preCourses;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", professor=" + professor +
                ", semester='" + semester + '\'' +
                ", preCourses=" + preCourses +
                '}';
    }

    public String getCourseName() {
        return courseName;
    }

    public Professor getProfessor() {
        return professor;
    }

    public String getSemester() {
        return semester;
    }

    public ArrayList<String> getPreCourses() {
        return preCourses;
    }
}
