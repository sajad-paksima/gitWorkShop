import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        input.nextLine();
        String[] players = new String[n];
        String[] teamName = new String[m];
        String[][] teamPlayers = new String[m][];
                                /*build boolean to check repeated teams*/
        boolean[] Guilty = new boolean[m];
                                /*Creating array list for guilty teams*/
        ArrayList<String> guilty=new ArrayList<>();
        getPlayerList(input, players);
        getTeamAndPlayersAndProcess(input, teamName, teamPlayers, guilty, Guilty, players);
        print(guilty);
    }
    private static void isRepeatedInOneTeam(String[][] teamPlayers, int teamNumber, int playerNumber, String[] teamName, ArrayList<String> guilty, boolean[] Guilty){
        for (int k=0; k<playerNumber; k++){
                            /*check if a player name exists in one team more than once*/
            if(teamPlayers[teamNumber][k].equals(teamPlayers[teamNumber][playerNumber]) && !Guilty[teamNumber]){
                guilty.add(teamName[teamNumber]);
                Guilty[teamNumber] = true;
            }
        }
    }
    private static void haveContactWithMoreThanOneTeam(int teamNumber, int playerNumber, String[][] teamPlayers, boolean[] Guilty, ArrayList<String> guilty, String[] teamName){
        for (int t=0; t<teamNumber; t++){
                                /*check if a player has contact with more than one team*/
            if (Arrays.asList(teamPlayers[t]).contains(teamPlayers[teamNumber][playerNumber])){
                if (!Guilty[teamNumber]){
                    Guilty[teamNumber] = true;
                    guilty.add(teamName[teamNumber]);
                }
                                /*check if another team was added to guiltyList*/
                if (!Guilty[t]){
                    Guilty[t] = true;
                    guilty.add(teamName[t]);
                }
            }
        }
    }
    private static void notExistedInList(String[] players, String[][] teamPlayers, boolean[] Guilty, ArrayList<String> guilty, String[]teamName, int teamNumber, int playerNumber){
                                    /*check if player does not exist in list*/
        if(!Arrays.asList(players).contains(teamPlayers[teamNumber][playerNumber]) && !Guilty[teamNumber]) {
            Guilty[teamNumber] = true;
            guilty.add(teamName[teamNumber]);
        }
    }
    private static void print(ArrayList<String> guilty){
                                    /*sort in alphabetical order*/
        Collections.sort(guilty);
        for (String member: guilty) {
            System.out.println(member);
        }
    }
    private static void getPlayerList(Scanner input, String[] players){
                                    /*scan player name*/
        for (int i=0; i<players.length; i++){
            players[i] = input.nextLine();
        }
    }
    private static void getTeamAndPlayersAndProcess(Scanner input, String[] teamName, String[][] teamPlayers, ArrayList<String> guilty, boolean[] Guilty, String[] players){
        for (int i=0; i<teamName.length; i++){
                                     /*scan team name*/
            teamName[i] = input.nextLine();
                                    /*scan player numbers*/
            int number = input.nextInt();
            input.nextLine();
                                      /*creating array containing player names*/
            teamPlayers[i] = new String[number];
            for(int j=0; j<number; j++){
                teamPlayers[i][j] = input.nextLine();
                                        /*check if they are guilty*/
                notExistedInList(players, teamPlayers, Guilty, guilty, teamName, i, j);
                haveContactWithMoreThanOneTeam(i, j, teamPlayers, Guilty, guilty, teamName);
                isRepeatedInOneTeam(teamPlayers, i, j, teamName, guilty, Guilty);
            }
        }
    }
}

