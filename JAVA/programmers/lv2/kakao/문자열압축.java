class Solution {
    public int solution(String s) {
    
        int half = s.length()/2;
        int len = s.length();
        int answer = len;
        
        for(int i = 1; i <= half; i++){
            
            StringBuilder sb = new StringBuilder();
            String prev = "";
            int cnt = 1;
            for(int j = 0; j < len; j+= i){
                int right = i+j < len ? i+j : len;
                String next = s.substring(j, right);
                
                if(next.equals(prev)){
                    cnt++;
                } else {
                    if(cnt>1) sb.append(String.valueOf(cnt)+prev);
                    else sb.append(prev);
                    prev = next;
                    cnt = 1;
                }
            }
            if(cnt > 1) sb.append(String.valueOf(cnt)+prev);
            else sb.append(prev);
            
            int result = sb.toString().length();
            if(result < answer) answer = result;
        }
        return answer;
    }
}