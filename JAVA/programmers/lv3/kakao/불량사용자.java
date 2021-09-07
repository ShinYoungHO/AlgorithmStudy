import java.util.*;

class Solution {
    String[] users;
    String[] banned;
    boolean[] visited;
    int[] map;
    Set<String> result;
    public int solution(String[] user_id, String[] banned_id) {
        this.users = user_id;
        this.banned = banned_id;
        this.visited = new boolean[user_id.length];
        this.map = new int[banned_id.length];
        this.result = new HashSet<>();
        DFS(0);
        
        return result.size();
    }
    
    void DFS(int idx){
        if(idx == banned.length){
            sortAndAdd();
            return;
        }
        for(int j = 0; j < users.length; j++){
            boolean match = isMatch(banned[idx], users[j]);    
            if(!visited[j] && match){
                visited[j] = true;
                map[idx] = j;
                DFS(idx+1);
                visited[j] = false;
            }
        }
    }
    void sortAndAdd(){
        int[] tmp = new int[map.length];
        for(int i = 0; i < map.length; i++){
            tmp[i] = map[i];
        }
        Arrays.sort(tmp);
        result.add(Arrays.toString(tmp));
    }
    
    boolean isMatch(String banned, String user){
        return user.matches(banned.replace("*","."));
    }
}