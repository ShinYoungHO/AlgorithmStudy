package stepByStep.stack;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class BiggestRight {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Stack<int[]> bucket = new Stack<>();
        int[] result = new int[n];

        for(int i = 0; i < n; i++){
            int v = input[i];
            while(!bucket.isEmpty() && bucket.peek()[1] < v){
                result[bucket.pop()[0]] = v;
            }
            bucket.push(new int[]{i, v});
        }
        while(!bucket.isEmpty()) result[bucket.pop()[0]] = -1;
        for(int i = 0; i < n; i++){
            bw.write(result[i]+" ");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
/**
 * 4
 * 9 5 4 8
 */