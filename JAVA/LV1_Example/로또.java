class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = {};
        int matchNum = 0;
        int zeroNum = 0;
        for(int i=0; i<lottos.length; i++){
            if(lottos[i] == 0) {
                zeroNum++;
                continue;
            }
            for(int j=0; j<win_nums.length; j++){
                if(lottos[i] == win_nums[j]){
                    matchNum++;
                }
            }
        }
        answer[0] = find(matchNum);
        answer[1] = find(matchNum+zeroNum);
        return answer;
    }
    public int find(int n){
        switch (n){
            case 6:
                return 1;
            case 5:
                return 2;
            case 4:
                return 3;
            case 3:
                return 4;
            case 2:
                return 5;
            default:
                return 6;
        }
    }
}




class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
        int zeroCount = 0;

        for(int lotto : lottos) {
            if(lotto == 0) {
                zeroCount++;
                continue;
            }
            // map.put
            map.put(lotto, true);
        }


        int sameCount = 0;
        for(int winNum : win_nums) {
            // map.containsKey
            if(map.containsKey(winNum)) sameCount++;
        }

        int maxRank = 7 - (sameCount + zeroCount);
        int minRank = 7 - sameCount;
        if(maxRank > 6) maxRank = 6;
        if(minRank > 6) minRank = 6;

        return new int[] {maxRank, minRank};
    }
}