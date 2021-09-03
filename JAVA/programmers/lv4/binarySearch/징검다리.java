import java.util.Arrays;

class Solution {
    boolean[] visited;
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        Arrays.sort(rocks);
        int left = 1;
        int right = distance;
        int mid = 0;
        int cnt = 0;
        while(left <= right){
            mid = (left+right) >> 1;
            cnt = solve(rocks, mid, distance, n);
            if(cnt > n){
                right = mid - 1;
            } else {
                if(answer < mid) answer = mid;
                left = mid + 1;
            }
        }
        return answer;
    }

    public int solve(int[] rocks, int mid, int distance, int n){
        int prev = 0;
        int cnt = 0;
        for(int i = 0; i < rocks.length; i++){
            if(rocks[i] - prev < mid){
                cnt++;
            } else {
                prev = rocks[i];            
            }
            if(cnt > n) return cnt;
        }
        if(distance - prev < mid) cnt++;
        return cnt;
    }

}