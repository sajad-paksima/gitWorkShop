package edu;

import java.util.ArrayList;
import java.util.Set;

public class Manager {
    private ArrayList<Student> students;
    private ArrayList<Professor> professors;
    private ArrayList<Course> coursesThisSemester;
    private ArrayList<Course> coursesHistory;
    private String currentSemester;

    public Manager(String semester) {
        this.currentSemester = semester;
        this.students = new ArrayList<>();
        this.professors = new ArrayList<>();
        this.coursesThisSemester = new ArrayList<>();
        this.coursesHistory = new ArrayList<>();
    }
    public void addStudent(String studentId, String firstName, String lastName){
        students.add(new Student(studentId, firstName, lastName));
    }
    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Professor> getProfessors() {
        return professors;
    }

    public ArrayList<Course> getCoursesThisSemester() {
        return coursesThisSemester;
    }

    public ArrayList<Course> getCoursesHistory() {
        return coursesHistory;
    }

    public String getSemester() {
        return currentSemester;
    }
    public void addProfessor(String firstName, String lastName, String rank){
        professors.add(new Professor(firstName, lastName, rank));
    }
    public void addCourse(String courseName, String professorFirstName, String professorLastName, ArrayList<String> preCourses){
        for (Professor professor : professors) {
            if (professor.getFirstName().equals(professorFirstName) && professor.getLastName().equals(professorLastName)){
                coursesThisSemester.add(new Course(courseName, professor, currentSemester, preCourses));
                break;
            }
        }
    }
    public Student getStudentById(String studentId){
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)){
                return student;
            }
        }
        return null;
    }
    public Course getCourseByName(String courseName){
        for (Course course : coursesThisSemester) {
            if (course.getCourseName().equals(courseName)){
                return course;
            }
        }
        return null;
    }
    public boolean hasCourse(Student student, Course course){
        return student.getCoursesThisSemester().containsKey(course);
    }
    public boolean takeCourseForStudent(String studentId, String courseName){
        Student student = getStudentById(studentId);
        Course course = getCourseByName(courseName);
        if (!hasCourse(student, course) && (course.getPreCourses().size()==0)){
            student.takeCourse(course);
            return true;
        }
        return false;
    }
    public Set<Course> getStudentCoursesThisSemester(String studentId) {
        Student student = getStudentById(studentId);
        return student.getCoursesThisSemester().keySet();
    }
}
