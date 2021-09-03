import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < scoville.length; i++){
            pq.offer(scoville[i]);
        }
        while(pq.peek() < K && pq.size() > 1){
            pq.offer(pq.poll()+2*pq.poll());
            answer++;
        }
        
        if(pq.peek() < K){
            return -1;
        }
        return answer;
    }
}