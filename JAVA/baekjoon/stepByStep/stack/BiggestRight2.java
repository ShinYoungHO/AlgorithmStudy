package stepByStep.stack;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class BiggestRight2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] input = new int[n];
        for (int i = 0; i < n; i++) {
            input[i]=Integer.parseInt(st.nextToken());
        }
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