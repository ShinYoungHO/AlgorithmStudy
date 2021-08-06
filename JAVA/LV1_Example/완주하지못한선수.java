import java.util.HashMap;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap();

        for(int i = 0; i < completion.length; i++){
            String c = completion[i];

            if(map.containsKey(c)){
                map.put(c,map.get(c)+1);
            } else {
                map.put(c,1);
            }
        }

        for(int i = 0; i < participant.length; i++){
            String p = participant[i];
            
            if(map.containsKey(p)){
                if(map.get(p)-1 == 0){
                    map.remove(p);
                } else {
                    map.put(p,map.get(p)-1);
                }
            } else {
                answer = p;
                break;
            }
        }
        return answer;
    }
}


// ~100ms

