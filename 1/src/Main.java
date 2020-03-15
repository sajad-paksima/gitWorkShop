import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int k = input.nextInt();
        int n = input.nextInt();
        paint(n, k);
    }
    private static void paint(int n, int k){
        String str = "";
        for (int i = 0; i < 2 * n + 1; i++){
            for (int j = 0; j < 2 * n + 1; j++){
                /*to build first left angel*/
                if(((i <= n && j <= n) && (i + j >= n && i + j < n + k)) ||
                        /*to build second left angel*/
                        ((i > n && j <= n) && (i - j <= n && i - j > n - k)) ||
                        /*to build first right angel*/
                        ((i <= n && j > n) && (j - i <= n && j - i > n - k)) ||
                        /*to build second right angel*/
                        ((i > n && j > n) && (i + j <= 3 * n && i + j > 3 * n - k))){
                    str += "*";
                }
                else {
                    str += " ";
                }
            }
            System.out.println(str);
            str = "";
        }
    }
}
