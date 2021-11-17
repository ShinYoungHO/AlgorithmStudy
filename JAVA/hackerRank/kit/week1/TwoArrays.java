package hackerRank.kit.week1;


import java.util.*;

public class TwoArrays {
    static String YES = "YES";
    static String NO = "NO";
    public static String twoArrays(int k, List<Integer> A, List<Integer> B) {
        PriorityQueue<Integer> pqA = new PriorityQueue<>();
        PriorityQueue<Integer> pqB = new PriorityQueue<>();
        Stack<Integer> tmpB = new Stack<>();
        for(int i = 0; i < B.size(); i++){
            pqA.add(A.get(i));
            pqB.add(B.get(i));
        }
        while(!pqA.isEmpty()){
            int a = pqA.poll();
            int t = k-a;
            while(!pqB.isEmpty() && pqB.peek() < t){
                tmpB.push(pqB.poll());
            }
            if(pqB.isEmpty()) return NO;
            pqB.poll();
            while(!tmpB.isEmpty()) pqB.add(tmpB.pop());
        }
        return YES;
    }
}
