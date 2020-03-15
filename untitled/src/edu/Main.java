package edu;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager("1398-2");
        new CommandProcessor(manager).run();
    }
}
