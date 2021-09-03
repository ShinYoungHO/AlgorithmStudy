import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

class Solution {
    Map<String, Integer> map ;
    String[] comb;
    boolean[] visited;
    ArrayList<String[]> splitOrder;
    
    public String[] solution(String[] orders, int[] course) {
        ArrayList<String> answer = new ArrayList<>();
        
        this.splitOrder = new ArrayList<>();
        
        for(int i = 0; i < orders.length; i++){
            String[] elem = orders[i].split("");
            Arrays.sort(elem);
            splitOrder.add(elem);
        }
        
        for(int i = 0; i < course.length; i++){
            int n = course[i];
            this.comb = new String[n];
            this.map = new HashMap<>();
            ArrayList<String> tmp = new ArrayList<>();
            for(int j = 0; j < splitOrder.size(); j++){
                String[] target = splitOrder.get(j);
                if(target.length < n) continue;
                this.visited = new boolean[target.length];
                combination(0, 0, n, target);
                
            }
            int max = 2;
            for(String key : map.keySet()){
                
                int v = map.get(key);
                if(v > max) {
                    tmp = new ArrayList<>();
                    tmp.add(key);
                    max = v;
                } else if(v == max) {
                    tmp.add(key);
                }
            }
            for(int j = 0; j < tmp.size(); j++){
                answer.add(tmp.get(j));
            }
            
        }
        String[] result = new String[answer.size()];
        for(int i = 0; i < answer.size(); i++){
            result[i] = answer.get(i);
        }
        Arrays.sort(result);
        return result;
    }
    
    public void combination(int cnt, int idx, int max, String[] target){
        if(cnt == max){
            String result = String.join("", comb);
            Integer v = map.get(result);
            if(v != null) map.put(result, v+1);
            else map.put(result, 1);
            
            return;
        }
        for(int i = idx; i < target.length; i++){
            if(!visited[i]){
                visited[i] = true;
                comb[cnt] = target[i];
                combination(cnt+1, i+1, max, target);
                visited[i] = false;
            }  
        }
    }
}