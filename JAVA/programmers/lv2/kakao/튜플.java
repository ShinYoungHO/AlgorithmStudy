import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int[] solution(String s) {
        String[] elements = s.split("\\},\\{");
        
        ArrayList<int[]> sArr = new ArrayList<>();
        int max = 0;
        
        for(int i = 0; i < elements.length; i++){
            StringTokenizer st = new StringTokenizer(elements[i], "{,}");
            int[] arr = new int[st.countTokens()];
            
            for(int j = 0; j < arr.length; j++){
                arr[j] = Integer.parseInt(st.nextToken());
            }
            if(arr.length > max) max = arr.length;
            
            sArr.add(arr);
        }
        
        Collections.sort(sArr, (e1, e2) -> e1.length - e2.length);
        
        int[] answer = new int[max];
        
        for(int i = 0; i < sArr.size(); i++){
            int[] arr = sArr.get(i);
            for(int j = 0; j < arr.length; j++){
                int v = arr[j]; // 
                int idx = -1;
                for(int k = 0; k < i; k++){
                    if(v == answer[k]){
                        
                        idx = k;
                        break;
                    }
                }
                if(idx == -1) {
                    answer[i] = v; 
                    break;
                }
            }
        }
        return answer;
    }
}