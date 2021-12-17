package category.ns.programmersTMP._lv2;

public class 카펫 {
    class Solution {
        public int[] solution(int brown, int yellow) {
            int[] ans = new int[2];
            int sum = brown+yellow;
            for(int i = 1; i <= brown; i++){
                if(sum%i!=0) continue;
                int x = i;
                int y = sum/i;
                if(2*(x+y)-4==brown){
                    ans[0] = Math.max(x,y);
                    ans[1] = Math.min(x,y);
                    break;
                }
            }
            return ans;
        }
    }
}
