package hackerRank.kit.week2;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class MaxMin {
    class Result {
        public int maxMin(int k, List<Integer> arr) {
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            LinkedList<Integer> dq = new LinkedList<>();

            for(int i = 0; i < arr.size(); i++){
                pq.add(arr.get(i));
            }
            int min = Integer.MAX_VALUE;
            while(dq.size() < k-1) dq.addLast(pq.poll());
            while(!pq.isEmpty()){
                dq.addLast(pq.poll());
                int v = dq.peekLast()-dq.pollFirst();
                if(v<min)min=v;
            }
            return min;
        }
    }
}
