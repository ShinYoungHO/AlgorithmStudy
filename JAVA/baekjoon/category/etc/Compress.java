package category.etc;

import java.io.*;
import java.util.*;

public class Compress {
    static int[] pair = new int[51];
    static String input;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();
        input = br.readLine();

        for(int i = 0; i < input.length(); i++) {
            char value = input.charAt(i);
            if(value == '(') stack.add(i);
            if(value == ')') pair[stack.pop()] = i;
        }

        System.out.println(solve(0, input.length()));
    }

    private static int solve(int start, int end) {
        int l = 0;
        for(int i = start; i < end; i++) {
            if(input.charAt(i) == '(') {
                l += (input.charAt(i-1) - '0') * solve(i+1, pair[i]) - 1;
                i = pair[i];
            } else {
                l++;
            }
        }
        return l;
    }
}

// 33(562(71(9)))