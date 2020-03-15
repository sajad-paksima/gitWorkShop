import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String inputString = input.nextLine();
                                        /*pattern for messages*/
        Pattern pattern = Pattern.compile("@#(forward|keeper),x=([1-9]\\d*|0),y=([1-9]\\d*|0),distance=([1-9]\\d*|0)#@");
        Matcher matcher = pattern.matcher(inputString);
                                        /*array list for saving coordinates and distance*/
        ArrayList<Integer> XYDistance = new ArrayList<>();
        int counter = process(matcher, XYDistance);
        System.out.println(counter);
    }
    private static int process(Matcher matcher, ArrayList<Integer> XYDistance){
        int lastDistance = 0;
        boolean owner = false;
        int counter = 0;
        int end = 0;
        while (matcher.find()){
            int start = matcher.start();
            /*error*/
            if (start - end >= 200){
                break;
            }
            else {
                end = matcher.end();
                /*partialize commands*/
                String commandProcess = matcher.group();
                /*forward state*/
                if (commandProcess.charAt(2) == 'f') {
                    /*finding numbers in string*/
                    Pattern number = Pattern.compile("\\d+");
                    Matcher str = number.matcher(commandProcess);
                    /*extract coordination and distance*/
                    while (str.find()) {
                        XYDistance.add(Integer.parseInt(str.group()));
                    }
                    int distanceFromBall = XYDistance.get(XYDistance.size()-1);
                                        /*start goal processing with conditions*/
                    if(distanceFromBall <= 10 && !owner){
                        owner = true;
                        lastDistance = distanceFromBall;
                        continue;
                    }
                    if (owner){
                                        /*state in which distance from ball is not increasing*/
                        if (distanceFromBall <= lastDistance){
                            owner = false;
                                        /*if after unsuccessful goal again possess ball*/
                            if(distanceFromBall <= 10){
                                owner = true;
                            }
                        }
                        else {
                                        /*calculate distance from goal*/
                            double distanceFromGoal = Math.sqrt(Math.pow(XYDistance.get(XYDistance.size()-3), 2) + Math.pow(XYDistance.get(XYDistance.size()-2), 2));
                                        /*if conditions are true*/
                            if (Math.abs(distanceFromGoal - distanceFromBall) <= 10){
                                counter++;
                                owner = false;
                            }
                        }
                                        /*save last distance for next comparison*/
                        lastDistance = distanceFromBall;
                    }
                }
            }
        }
        return counter;
    }
}
