import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
                                    /*scan time and relation numbers*/
        int t = input.nextInt();
        int n = input.nextInt();
                                    /*creating array list which describes relationship*/
        ArrayList<Integer>[] neighbor;
        neighbor = new ArrayList[12];
        for (int i=1; i<=11; i++){
            neighbor[i] = new ArrayList<>();
        }
                                    /*scan numbers which have relationship with each other*/
        for (int i=1; i<=n; i++){
            int firstNode = input.nextInt();
            int lastNode = input.nextInt();
                                    /*add relationship*/
            addNode(neighbor,firstNode, lastNode);
        }
                                  /*understand source and destination*/
        int src = 1;
        int destination = 11;
                                /*create array which expresses distance from source*/
        int[] distanceFromFirst = new int[12];
        if (!Path(neighbor, src, destination, distanceFromFirst)){
            System.out.println(0);
            return;
        }
        System.out.println(90/(distanceFromFirst[destination]*t));
    }
    private static void addNode(ArrayList<Integer>[] neighbor, int source, int destination){
        neighbor[source].add(destination);
        neighbor[destination].add(source);
    }
    private static boolean Path(ArrayList<Integer>[] neighbor, int src, int destination, int[] distanceFromFirst){
                                        /*boolean which says if we visit node or not*/
        boolean[] visited = new  boolean[12];
                                        /*building queue which expresses path*/
        Queue<Integer> queue = new LinkedList<>();
                                        /*start from source*/
        visited[src] = true;
        distanceFromFirst[src] = 0;
        queue.add(src);
                                        /*check if we check all nodes*/
        while (!queue.isEmpty()) {
                                        /*move one step and delete previous node*/
            int nextNode = queue.poll();
                                        /*check unvisited relationships that u has*/
            for (int i = 0; i < neighbor[nextNode].size(); i++) {
                if (!visited[neighbor[nextNode].get(i)]) {
                    visited[neighbor[nextNode].get(i)] = true;
                                        /*increase path length*/
                    distanceFromFirst[neighbor[nextNode].get(i)] = distanceFromFirst[nextNode] + 1;
                                         /*add node to the path and check if it is destination*/
                    queue.add(neighbor[nextNode].get(i));
                    if (neighbor[nextNode].get(i) == destination)
                        return true;
                }
            }
        }
        return false;
    }
}


