package edu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CommandProcessor {
    private Manager manager;
    private Scanner scanner;
    public CommandProcessor(Manager manager) {
        this.manager = manager;
        this.scanner = new Scanner(System.in);
    }

    private void processAddStudent(String[] splitInput){
        manager.addStudent(splitInput[2], splitInput[3], splitInput[4]);
    }
    private void processShowStudents() {
        for (Student student : manager.getStudents()) {
            System.out.println(student);
        }
    }
    private void processAddProfessor(String[] splitInput){
        manager.addProfessor(splitInput[2], splitInput[3], splitInput[4]);
    }
    private void processShowProfessors(){
        for (Professor professor : manager.getProfessors()) {
            System.out.println(professor);//professor.toString()
        }
    }
    private void processAddCourse(String[] splitInput){
        ArrayList<String> preCourse = new ArrayList<>();
        if (splitInput.length == 6){
            String[] inputPreCourses = splitInput[5].split(",");
            Collections.addAll(preCourse, inputPreCourses);
        }
        manager.addCourse(splitInput[2], splitInput[3], splitInput[4], preCourse);
    }
    private void processShowCoursesThisSemester(){
        for (Course course : manager.getCoursesThisSemester()) {
            System.out.println(course);
        }
    }
    private void processTakeCourse(String[] splitInput){
        if (manager.takeCourseForStudent(splitInput[2], splitInput[3])){
            System.out.println("Successfully took course!");
        }
        else
            System.out.println("Failed to take course!");
    }
    private void processShowCoursesForStudent(String[] splitInput){
        for (Course course : manager.getStudentCoursesThisSemester(splitInput[4])) {
            System.out.println(course);
        }
    }

    public void run(){
        String input;
        System.out.println("Enter your command:");
        while (! (input = scanner.nextLine()).equalsIgnoreCase("end")){
            if (input.startsWith("add student")){
                processAddStudent(input.split(" "));
            }
            else if ((input.startsWith("add professor"))){
                processAddProfessor(input.split("\\s"));
            }
            else if (input.startsWith("add course")){
                processAddCourse(input.split("\\s"));
            }
            else if (input.startsWith("show students")){
                processShowStudents();
            }
            else if (input.startsWith("show professors")){
                processShowProfessors();
            }
            else if (input.startsWith("show courses this semester")){
                processShowCoursesThisSemester();
            }
            else if (input.startsWith("take course")){
                processTakeCourse(input.split("\\s"));
            }
            else if (input.startsWith("show courses for student")){
                processShowCoursesForStudent(input.split("\\s"));
            }
            else {
                System.out.println("Invalid Command");
            }
        }
    }

}
