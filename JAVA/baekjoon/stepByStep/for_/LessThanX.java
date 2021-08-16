package stepByStep.for_;


import java.io.*;
import java.util.Scanner;

public class LessThanX {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] state = bf.readLine().split(" ");
        String[] input = bf.readLine().split(" ");

        int n = Integer.parseInt(state[0]);
        int X = Integer.parseInt(state[1]);
        Boolean isStart = false;

        for(int i = 0; i < n; i++){
            String v = input[i];
            if(Integer.parseInt(v) <= X){
                if(!isStart){
                    bw.write(v);
                    isStart = true;
                    continue;
                }
                bw.write(" "+v);
            }
        }
        bw.flush();
        bw.close();
        bf.close();
    }
}

 class LessThanX_ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] state = sc.nextLine().split(" ");
        String[] input = sc.nextLine().split(" ");

        int n = Integer.parseInt(state[0]);
        int X = Integer.parseInt(state[1]);
        Boolean isStart = false;
        StringBuffer result = new StringBuffer();

        for(int i = 0; i < n; i++){
            String v = input[i];
            if(Integer.parseInt(v) < X){
                if(isStart){
                    result.append(" "+v);
                } else {
                    isStart = true;
                    result.append(v);
                }
            }
        }

        System.out.println(result);
        sc.close();
    }
}