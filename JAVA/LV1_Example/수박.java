public class Solution {
    public String solution(int n){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < n; i++){
            sb.append(i % 2 == 0 ? "수" : "박");
        }
        return sb.toString();
    }
}