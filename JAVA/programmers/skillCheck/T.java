package programmers.skillCheck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class T{
    public static void main(String[] args) {
    }
}

class Solution1 {
    int start = 9*60;

    public String solution(int n, int t, int m, String[] timetable) {
        int[] times = convert(timetable);

        int ans = start;
        int idx = 0;

        for(int i = 0; i < n; i++){
            int bustime = start+t*i;
            int cnt = 0;
            ans = bustime;
            Integer last = null;
            for(int j = 0; j < m; j++){
                if(idx < times.length && times[idx]<=bustime) {
                    last = times[idx];
                    idx++;
                    cnt++;
                }
            }
            if(cnt == m) ans = last-1;
            else continue;
        }




        return intToTime(ans);
    }

    int[] convert(String[] timetable){
        int[] times = new int[timetable.length];

        for(int i = 0; i < timetable.length; i++){
            times[i] = timeToInt(timetable[i]);
        }
        Arrays.sort(times);
        return times;
    }

    int timeToInt(String time){
        StringTokenizer st = new StringTokenizer(time, ":");
        return Integer.parseInt(st.nextToken())*60+Integer.parseInt(st.nextToken());
    }

    String intToTime(int t){
        StringBuilder sb = new StringBuilder();
        int h,m;
        h = t/60;
        m = t%60;
        sb.append(h<10?"0"+h:h).append(":").append(m<10?"0"+m:m);

        return sb.toString();
    }
}


class Solution2 {
    long min = 0;
    long max = 1187654321000000l;

    public long solution(int n, int[] times) {
        long l = min;
        long r = max;
        long ans = max;

        while(l <= r){
            long mid = getMid(l, r);
            long cnt = solve(mid, times);
            if(cnt < n){
                l = mid+1;
            } else {
                if(ans > mid) ans = mid;
                r = mid-1;
            }
        }
        return ans==max?-1:ans;
    }

    long solve(long target_time, int[] times){
        long ans = 0;
        for(int i = 0; i < times.length; i++){
            int per_time = times[i];
            ans += target_time/per_time;
        }
        return ans;
    }

    long getMid(long l, long r){
        return l/2+r/2;
    }

}

class Solution3 {
    public int[] solution(int n, int s) {
        if(s/n == 0) return new int[]{-1};
        int[] answer = new int[n];
        int sub = s - (s/n)*n;
        Arrays.fill(answer, s/n);
        for(int i = n-1; i >= 0; i--){
            if(sub == 0) break;
            sub--;
            answer[i]++;
        }
        return answer;
    }
}

class Solution4 {
    public int solution(int[][] triangle) {
        for(int i = triangle.length-1; i > 0; i--){
            int[] bottom = triangle[i];
            int[] up = triangle[i-1];
            for(int j = 0; j < up.length; j++){
                int fix = up[j];
                up[j] = Math.max(bottom[j]+fix, bottom[j+1]+fix);
            }
        }
        return triangle[0][0];
    }
}