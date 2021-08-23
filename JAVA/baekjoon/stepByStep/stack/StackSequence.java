package stepByStep.stack;

import java.io.*;
import java.util.Stack;

public class StackSequence {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] target = new int[n];
        for(int i = 0; i < n; i++) target[i] = Integer.parseInt(br.readLine());

        Stack<Integer> bucket = new Stack<>();
        int current = 0;
        for(int i = 0; i < n; i++){
            int v = target[i];
            if(current < v){
                while(current < v){
                    current++;
                    bucket.push(current);
                    sb.append("+\n");
                }
            }

            if(!bucket.isEmpty() && bucket.peek() == v){
                bucket.pop();
                sb.append("-\n");
            } else {
                System.out.println("NO");
                br.close();
                return;
            }
        }
        System.out.println(sb);
        br.close();
    }
}
