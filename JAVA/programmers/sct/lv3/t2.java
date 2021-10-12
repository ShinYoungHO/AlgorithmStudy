package test.lv3;
import java.util.*;

public class t2 {
}

class SolutionT2_1 {
    public int solution(String s){
        int answer = 0;
        for(int i = 0; i < s.length(); i++){
            int k ;


            k = solveABA(s, i, 1)*2+1;
            if(k > answer) answer = k;

            System.out.println("i"+i);
            k = (solveABBA(s, i-1, i, 0)+1)*2;
            if(k > answer) answer = k;
        }
        return answer;
    }

    int solveABA(String s, int idx, int len){
        int lIdx = idx-len;
        int rIdx = idx+len;
        if(lIdx >= 0 && rIdx < s.length()){
            if(s.charAt(lIdx) == s.charAt(rIdx)){
                return solveABA(s, idx, len+1);
            }
        }
        return len-1;
    }

    int solveABBA(String s, int idx1, int idx2, int len){
        int lIdx = idx1-len;
        int rIdx = idx2+len;

        // 0 b 1 b 2 a
        if(lIdx >= 0 && rIdx < s.length()){
            if(s.charAt(lIdx) == s.charAt(rIdx)){
                return solveABBA(s, idx1, idx2, len+1);
            }
        }
        return len-1;
    }
}



// fail
class SolutionT2_2 {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        Queue<Integer> q = new LinkedList<>();

        for(int i = 0; i < timetable.length; i++){
            q.add(getTime(timetable[i]));
        }

        int start = 9*60;
        int past = 0;

        for(int i = 0; i < n; i++){
            int busTime = start + n*t;
            for(int j = 0; j < m; j++){
                if(!q.isEmpty()){
                    if(q.peek() <= busTime){
                        past = q.poll();
                    }
                }
            }
        }



        System.out.println(past);
        return "";
    }

    String getString(int i){
        return i/60+":"+i%60;
    }
    int getTime(String s){
        StringTokenizer st = new StringTokenizer(s, ":");
        int h = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        return h*60+m;
    }
}