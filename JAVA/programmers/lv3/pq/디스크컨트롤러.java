import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] jobs) {
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt((int[] p) -> p[1]));
        Arrays.sort(jobs, Comparator.comparingInt((int[] e) -> e[0]));
        
        int time = 0;
        int total = 0;
        int jobsIdx = 0;
        while(true){
            while(jobsIdx < jobs.length && jobs[jobsIdx][0] <= time){
                pq.add(new int[]{ jobs[jobsIdx][0], jobs[jobsIdx++][1] });
            }
            if(pq.isEmpty()) {
                if(jobsIdx < jobs.length){
                    time=jobs[jobsIdx][0];
                    continue;
                }
                break;
            }
            int[] job = pq.poll();
            total += job[1] - job[0] + time;
            time += job[1];
        }
        return total/jobs.length;
    }
}