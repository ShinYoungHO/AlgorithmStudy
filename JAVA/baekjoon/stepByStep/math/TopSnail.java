package stepByStep.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TopSnail {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] inp =  bf.readLine().split(" ");

        int A = Integer.parseInt(inp[0]);
        int B = Integer.parseInt(inp[1]);
        int V = Integer.parseInt(inp[2]);

        int top = V-B;
        int bottom = A-B;

        if(bottom <= 0){
            System.out.println(-1);
            return;
        }

        if(top % bottom == 0){
            System.out.println(top/bottom);
        } else {
            System.out.println(top/bottom+1);
        }
        bf.close();
    }
}
