class Solution {
    public long solution(int n, int[] times) {
        long left = 0l;
        long right = Long.MAX_VALUE;
        long mid = 0l;
        long ans = Long.MAX_VALUE;
        while(left <= right){
            mid = (right >> 1) + ((left) >> 1);
            long result = solve(times, mid, n);
            if(result >= n){
                right = mid-1;
                if(ans > mid) ans = mid;
            } else {
                left = mid+1;
            }
        }
        return ans;
    }

    public long solve(int[] times, long mid, long n){
        long result  = 0l;
        for(int i = 0; i < times.length; i++){
            result += mid/times[i];
            if(result > n) return result;
        }
        return result;
    }
}