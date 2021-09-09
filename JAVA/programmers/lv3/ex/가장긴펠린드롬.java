class Solution{
    public int solution(String s){
        int left = 1;
        int right = s.length();
        int ans = 1;
        if(left > right) return -1;
        for(int i = left; i < right; i++){
            
            if(i < right-1 && (s.charAt(i-1) == s.charAt(i+1))){
                int v = solve(s, i, i, 0);
                if(ans < v) ans = v;
            }
            if(s.charAt(i-1) == s.charAt(i)){
                int v = solve(s, i-1, i, -1);
                if(ans < v) ans = v;
            }
        }
        return ans;
    }
    
    int solve(String s, int left, int right, int pad){
        int len = s.length();
        int result = 1;
        while(left-1 >= 0 && right+1 < len && (s.charAt(left-1) == s.charAt(right+1))){
            left--;
            right++;
            result+=2;
        }
        return result-pad;
    }
}