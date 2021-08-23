package stepByStep.stack;

import java.io.*;
import java.util.Stack;

public class VPS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            String[] brackets = br.readLine().split("");
            Stack<Integer> stack = new Stack<>();

            for(int j = 0; j < brackets.length; j++){
                if(brackets[j].equals("(")) stack.push(0);
                else {
                    if(stack.size() > 0 && stack.peek() == 0) {
                        stack.pop();
                    } else {
                        stack.push(1);
                        break;
                    }
                }
            }
            if(stack.size() == 0) bw.write("YES\n");
            else bw.write("NO\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
