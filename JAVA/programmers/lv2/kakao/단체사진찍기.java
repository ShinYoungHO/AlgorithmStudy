import java.util.Map;


class Solution {
    
    Map<Character, Integer> map;
    int result;
    boolean[] visited;
    int[] kakaoOrder;
    String[] queries;
    int len;
    public int solution(int n, String[] data) {
        result = 0;
        len = n;
        map  = Map.of('A', 0, 'C', 1, 'F', 2, 'J', 3, 'M', 4, 'N', 5, 'R', 6, 'T', 7);
        visited = new boolean[8];
        kakaoOrder = new int[8];
        queries = data;
        
        DFS(0);
        return result;
    }
    
    public void DFS(int idx){
        if(idx == 8){
            if(checkValid()){
                result++;   
            }
            return;
        }
        for(int i = 0; i < 8; i++){
            if(!visited[i]){
                visited[i] = true;
                kakaoOrder[idx] = i;
                DFS(idx+1);
                visited[i] = false;
            }
        }
    }
    
    public boolean checkValid(){
        for(int i = 0; i < len; i++){
            String input = queries[i];
            int X = kakaoOrder[map.get(input.charAt(0))];
            int Y = kakaoOrder[map.get(input.charAt(2))];
            char cond = input.charAt(3);
            int inputDst = input.charAt(4) - '0';
            int dst = Math.abs(X-Y)-1;
            if(cond == '=' && dst != inputDst){
                return false;
            } else if(cond == '<' && dst >= inputDst){
                return false;
            } else if (cond == '>' && dst <= inputDst){
                return false;
            }
        }
        return true;
    }
}