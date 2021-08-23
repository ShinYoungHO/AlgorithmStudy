package stepByStep.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Zero {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] digits = new int[n];
        for(int i = 0; i < n; i++) digits[i] = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < n; i++){
            if(digits[i] == 0) stack.pop();
            else stack.push(digits[i]);
        }

        int result = 0;
        for(int i = 0; i < stack.size(); i++){
            result += stack.elementAt(i);
        }

        System.out.println(result);
        br.close();
    }
}
