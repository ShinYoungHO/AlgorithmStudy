package stepByStep.stack;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class BiggestRight3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] input = new int[n];

        for (int i = 0; i < n; i++) {
            input[i]=Integer.parseInt(st.nextToken());
        }

        Stack<Integer> bucket = new Stack<>();
        int[] result = new int[n];
        result[n-1] = -1;

        int idx = -1;
        for(int i = 0; i < n; i++){
            int v = input[i];
            while(!bucket.isEmpty() && bucket.peek() < v){
                result[idx--] = v;
                bucket.pop();
            }
            bucket.push(v);
            idx++;
        }
        while(idx>0) result[--idx] = -1;
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