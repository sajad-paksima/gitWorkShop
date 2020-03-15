import java.util.*;
import java.util.regex.*;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String inputString = input.nextLine();
        while (true){
            /*array of numbers in string and their index*/
            ArrayList<Integer> index = new ArrayList<>();
            ArrayList<Integer> numbers = new ArrayList<>();
            String command = input.nextLine();
            /*main command without spaces*/
            command = command.trim();
            String[] words = command.split(" ");
            if (isEnd(command)){
                System.out.println("END OF PROGRAM");
                break;
            }
            else {
                if (isMul(command)){
                    inputString = mul(numbers, index, inputString);
                }
                else {
                    if (isAdd(command)){
                        inputString = add(numbers, index, inputString);
                    }
                    else {
                        if (isSub(command)){
                            inputString = sub(numbers, index, inputString);
                        }
                        else {
                            if (isSum(command))
                                inputString = sum(numbers, index, inputString, words, command);
                            else {
                                /*gcd of n numbers section*/
                                if (isGcd(command)){
                                    inputString = gcd(numbers, index, inputString, words, command);
                                }
                                else {
                                    if (isReplace(command)){
                                        inputString = replace(inputString, words);
                                    }
                                    else {
                                        if (isCountEntail(command)){
                                            inputString = countEntail(inputString, words);
                                        }
                                        else {
                                            if (isInsert(command)){
                                                inputString = insert(inputString, words);
                                            }
                                            else {
                                                if (isDeleteForGeneral(command)){
                                                    inputString = delete(inputString, command, words);
                                                }
                                                else {
                                                    if (isPrint(command)){
                                                        System.out.println(inputString);
                                                    }
                                                            /*invalid command*/
                                                    else {
                                                        System.out.println("THE COMMAND IS INVALID");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    private static int processOnString(ArrayList<Integer> numbers, ArrayList<Integer> index, String inputString){
        /*pattern of numbers*/
        Pattern pattern = Pattern.compile("(-)?\\d+");
        Matcher matcher = pattern.matcher(inputString);
        int counter = 0;
        while(matcher.find()) {
            /*find how many numbers repeated*/
            counter++;
            /*add number and index to array*/
            numbers.add(Integer.parseInt(matcher.group()));
            index.add(inputString.indexOf(matcher.group()));
        }
        /*return counter*/
        return  counter;
    }
    private static String finalStringSub(String inputString, ArrayList<Integer> numbers){
        /*subtract of two numbers and replace*/
        int temp = numbers.get(0) - numbers.get(1);
        /*delete string between two numbers*/
        inputString = inputString.replaceFirst("-?\\d+\\D+-?\\d+", Integer.toString(temp));
        return inputString;
    }
    private static String finalStringMul(String inputString, ArrayList<Integer> numbers){
        /*multiple of two numbers and replace*/
        int temp = numbers.get(0) * numbers.get(1);
        /*delete string between two numbers*/
        inputString = inputString.replaceFirst("-?\\d+\\D+-?\\d+", Integer.toString(temp));
        return inputString;
    }
    private static String finalStringAdd(String inputString, ArrayList<Integer> numbers){
        /*addition of two numbers and replace*/
        int temp = numbers.get(0) + numbers.get(1);
        /*delete string between two numbers*/
        inputString = inputString.replaceFirst("-?\\d+\\D+-?\\d+", Integer.toString(temp));
        return inputString;
    }
    private static String processOnSum(String inputString, String command, ArrayList<Integer> numbers, int numToCheck, int counter){
        if (command.matches("sum \\d+ -b")){
            int temp = 0;
            for (int i = 0; i < numToCheck; i++){
                /*add numbers of string to temp*/
                temp += numbers.get(i);
            }
            String stringTmp = "S" + temp + "S";
            /*add addition to string*/
            inputString = inputString.concat(stringTmp);
            return inputString;
        }
        else {
            int temp = 0;
            for (int i=counter-numToCheck; i<counter; i++){
                /*add numbers of string to temp*/
                temp += numbers.get(i);
            }
            String stringTmp = "S" + temp + "S";
            /*add addition to string*/
            inputString = inputString.concat(stringTmp);
            return inputString;
        }
    }
    private static String processOnGcd(String inputString, String command, ArrayList<Integer> numbers, int numToCheck){
        if (command.matches("gcd \\d+ -b")){
            /*find gcd from beginning*/
            int gcd = findGcd(numbers, numToCheck, 0);
            String stringTmp = "G" + gcd + "G";
            /*add to main string*/
            inputString = inputString.concat(stringTmp);
            return inputString;
        }
        else {
            /*find gcd from beginning*/
            int gcd = findGcd(numbers, numToCheck, numbers.size()-numToCheck);
            String stringTmp = "G" + gcd + "G";
            /*add to main string*/
            inputString = inputString.concat(stringTmp);
            return inputString;
        }
    }
    private static int gcd(int num1, int num2){
        if (num1 == 0)
            return num2;
        return gcd(num2%num1, num1);
    }
    private static int findGcd(ArrayList<Integer> numbers, int numToCheck, int start){
        int result = Math.abs(numbers.get(start));
        for (int i=start + 1; i< start + numToCheck; i++){
            /*find gcd of numbers step by step*/
            result = gcd(result, Math.abs(numbers.get(i)));
        }
        return result;
    }
    private static String replace(String inputString, String[] words){
        /*understand target and replacement*/
        String str1 = words[1], str2 = words[2];
        /*find how many words should be replaced*/
        int n = Integer.parseInt(words[3]);
        Pattern str = Pattern.compile(str1);
        Matcher mat = str.matcher(inputString);
        int counter = 0;
        while (mat.find()){
            /*check and find how many words found*/
            if (counter == n)
                break;
            counter++;
            inputString = inputString.replaceFirst(mat.group(), str2);
            mat = str.matcher(inputString);
        }
        System.out.println(inputString);
        return inputString;
    }
    private static String processOnCountEntail(String inputString, int counter){
        /*add to string*/
        inputString += "C" + counter + "C";
        return inputString;
    }
    private static void printErr(){
        System.out.println("CANNOT PERFORM THE COMMAND SUCCESSFULLY");
    }
    private static String processOnInsert(String[] words, String inputString, int num){
        String str = words[1];
        /*if command has two words*/
        if (words.length == 2){
            inputString += str;
            return inputString;
        }
        /*second state with num which is index*/
        else {
            inputString = inputString.substring(0, num) + str + inputString.substring(num);
            return inputString;
        }
    }
    private static String processOnDelete1(String inputString, String str){
        /*replace with ""*/
        inputString = inputString.replaceFirst(str, "");
        return inputString;
    }
    private static String processOnDelete2(String inputString, String str){
        /*find index of string repeated for the last time and add*/
        int start = inputString.lastIndexOf(str);
        inputString = inputString.substring(0, start) + "" + inputString.substring(start+str.length());
        return inputString;
    }
    private static boolean isMul(String command){
        return command.equals("mul");
    }
    private static String mul(ArrayList<Integer> numbers, ArrayList<Integer> index, String inputString){
        /*times number repeats*/
        int counter = processOnString(numbers, index, inputString);
        /*error section*/
        if (counter < 2){
            printErr();
        }
        else {
            /*string after changes*/
            inputString = finalStringMul(inputString, numbers);
            System.out.println(inputString);
        }
        return inputString;
    }
    private static boolean isAdd(String command){
        return command.equals("add");
    }
    private static String add(ArrayList<Integer> numbers, ArrayList<Integer> index, String inputString){
        /*times number repeats*/
        int counter = processOnString(numbers, index, inputString);
        /*error section*/
        if (counter < 2){
            printErr();
        }
        else {
            /*string after changes*/
            inputString = finalStringAdd(inputString, numbers);
            System.out.println(inputString);
        }
        return inputString;
    }
    private static boolean isSub(String command){
        return command.equals("sub");
    }
    private static String sub(ArrayList<Integer> numbers, ArrayList<Integer> index, String inputString){
        /*times number repeats*/
        int counter = processOnString(numbers, index, inputString);
        /*error section*/
        if (counter < 2){
            printErr();
        }
        else {
            /*string after changes*/
            inputString = finalStringSub(inputString, numbers);
            System.out.println(inputString);
        }
        return inputString;
    }
    private static boolean isSum(String command){
        return command.matches("sum \\d+ -[bf]");
    }
    private static String sum(ArrayList<Integer> numbers, ArrayList<Integer> index, String inputString, String[] words, String command){
        /*how many numbers existed in string*/
        int counter = processOnString(numbers, index, inputString);
        /*number of digits that we should calculate*/
        int n = Integer.parseInt(words[1]);
        /*error section*/
        if (counter < n){
            printErr();
        }
        else {
            /*string after changes*/
            inputString = processOnSum(inputString, command, numbers, n, counter);
            System.out.println(inputString);
        }
        return inputString;
    }
    private static boolean isGcd(String command){
        return command.matches("gcd \\d+ -[bf]");
    }
    private static String gcd(ArrayList<Integer> numbers, ArrayList<Integer> index, String inputString, String[] words, String command){
        /*how many numbers existed in string*/
        int counter = processOnString(numbers, index, inputString);
        /*number of digits that we should calculate*/
        int n = Integer.parseInt(words[1]);
        /*error section*/
        if (counter < n){
            printErr();
        }
        else {
            /*string after changes*/
            inputString = processOnGcd(inputString, command, numbers, n);
            System.out.println(inputString);
        }
        return inputString;
    }
    private static boolean isReplace(String command){
        return command.matches("replace \\S+ \\S+ \\d+");
    }
    private static boolean isCountEntail(String command){
        return command.matches("count_entail \\S+");
    }
    private static String countEntail(String inputString, String[] words){
        /*string we should count*/
        String str = words[1];
        int counter = 0;
        for (int i=0; i<inputString.length()-str.length()+1; i++){
            /*check how many times string repeated*/
            if (str.equals(inputString.substring(i, i+str.length()))){
                counter++;
            }
        }
        /*error section*/
        if (counter == 0)
            printErr();
        else {
            /*string after changes*/
            inputString = processOnCountEntail(inputString, counter);
            System.out.println(inputString);
        }
        return inputString;
    }
    private static boolean isInsert(String command){
        return command.matches("insert \\S+") || command.matches("insert \\S+ \\d+");
    }
    private static String insert(String inputString, String[] words){
        int num = 0;
        /*second form of command*/
        if (words.length > 2){
            /*index od string inserted*/
            num = Integer.parseInt(words[2]);
            /*error section*/
            if (num > inputString.length()){
                printErr();
            }
            else {
                /*string after changes*/
                inputString = processOnInsert(words, inputString, num);
                System.out.println(inputString);
            }
        }
        /*first form of command*/
        else {
            /*string after changes*/
            inputString = processOnInsert(words, inputString, num);
            System.out.println(inputString);
        }
        return inputString;
    }
    private static boolean isDeleteForGeneral(String command){
        return command.matches("delete \\S+") || command.matches("delete \\S+ -f");
    }
    private static String delete(String inputString, String command, String[] words){
        /*string we should count*/
        String str = words[1];
        /*two different states*/
        if (command.matches("delete \\S+")){
            /*error section*/
            if (!inputString.contains(str)){
                printErr();
            }
            else {
                /*string after changes*/
                inputString = processOnDelete1(inputString, str);
                System.out.println(inputString);
            }
        }
        /*second form of command*/
        else {
            if (!inputString.contains(str)){
                printErr();
            }
            else {
                inputString = processOnDelete2(inputString, str);
                System.out.println(inputString);
            }
        }
        return inputString;
    }
    private static boolean isPrint(String command){
        return command.equals("print");
    }
    private static boolean isEnd(String command){
        return command.equals("end");
    }
}


