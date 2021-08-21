package stepByStep.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ATM {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        int[] customers = new int[n];
        for(int i = 0; i < n; i++) customers[i] = Integer.parseInt(input[i]);

        Arrays.sort(customers);

        int result = 0;
        int current = 0;
        for(int i = 0; i < n; i++){
            int time = customers[i];
            result += current+time;
            current+=time;
        }

        System.out.println(result);
        br.close();
    }
}
