package stepByStep.array;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;


public class MinMax {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        String[] input = bf.readLine().split(" ");

        int max = Integer.parseInt(input[0]);
        int min = Integer.parseInt(input[0]);

        for(int i = 1; i < n; i++){
            int v = Integer.parseInt(input[i]);
            if(max < v){
                max = v;
            } else if(v <= min){
                min = v;
            }
        }

        System.out.println(min + " " + max);
        bf.close();
    }
}
