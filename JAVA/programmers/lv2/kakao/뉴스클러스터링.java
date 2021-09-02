import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String str1, String str2) {
        
        ArrayList<String> fs = getMultipleSet(str1.toUpperCase());
        ArrayList<String> ss = getMultipleSet(str2.toUpperCase());
        Map<String, Integer> map = new HashMap<>();
        
        int all = 0;
        int overlap = 0;

        for(int i = 0; i < fs.size(); i++){
            String str = fs.get(i);
            Integer c = map.get(str);
            if(c != null){
                map.put(str, c+1);
            } else {
                map.put(str, 1);
            }
            all++;
        }
        
        for(int i = 0; i < ss.size(); i++){
            String str = ss.get(i);
            Integer c = map.get(str);
            if(c != null && c > 0){
                overlap++;
                map.put(str, c-1);
            } else {
                all++;
            }
        }
        if(all == 0) return 65536;
        return overlap*65536/all;
    }
    public ArrayList<String> getMultipleSet(String str){
        ArrayList<String> result = new ArrayList<>();
        
        for(int i = 0; i < str.length() - 1; i++){
            String pstr = ""+str.charAt(i)+str.charAt(i+1);
            if(pstr.matches("[A-Z]+")){
                result.add(pstr);
            }
        }
        return result;
    }
}