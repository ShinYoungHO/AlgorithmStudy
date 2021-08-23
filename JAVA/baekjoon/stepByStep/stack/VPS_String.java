package stepByStep.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class VPS_String {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str;
        while(!(str = br.readLine()).equals(".")){
            Stack<Integer> stack = new Stack<>();
            for(int i = 0; i < str.length(); i++){
                char ch = str.charAt(i);
                if(ch == '(') stack.push(0);
                else if(ch == '[') stack.push(1);
                else if(ch == ')') {
                    if(!stack.isEmpty() && stack.peek() == 0){
                        stack.pop();
                    } else {
                        stack.push(1);
                        break;
                    }
                }
                else if(ch == ']') {
                    if(!stack.isEmpty() && stack.peek() == 1){
                        stack.pop();
                    } else {
                        stack.push(1);
                        break;
                    }
                }
            }
            if(stack.isEmpty()) System.out.println("yes");
            else System.out.println("no");
        }
        br.close();
    }
}

/**
 So when I die (the [first] I will see in (heaven) is a score list).
 [ first in ] ( first out ).
 Half Moon tonight (At least it is better than no Moon at all].
 A rope may form )( a trail in a maze.
 Help( I[m being held prisoner in a fortune cookie factory)].
 ([ (([( [ ] ) ( ) (( ))] )) ]).
 .
 .
 */