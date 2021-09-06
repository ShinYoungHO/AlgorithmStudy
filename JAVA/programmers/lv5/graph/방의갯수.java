import java.util.*;

class Solution {
    public int solution(int[] arrows) {
        int result = 0;
        Set<String> coordSet = new HashSet<>();
        Set<String> halfSet = new HashSet<>();
        Set<String> pathSet = new HashSet<>();
        
        int[] dx = new int[]{0, 1, 1, 1, 0, -1, -1, -1};
        int[] dy = new int[]{1, 1, 0, -1, -1, -1, 0, 1};
        int x = 0; 
        int y = 0;
        coordSet.add(x+","+y);
        
        for(int i = 0; i < arrows.length; i++){
            int nx = x + dx[arrows[i]];
            int ny = y + dy[arrows[i]]; 
            
            String[] path = new String[]{x+","+y, nx+","+ny};
            String key = path[0].compareTo(path[1]) > 0 ? path[0]+path[1] : path[1]+path[0];
            if(addToSet(pathSet, key)) {
                if(!addToSet(coordSet, nx+","+ny)) result++;
                if(!addToSet(halfSet, (float)(nx+x)/2+","+(float)(y+ny)/2)) result++;
            }
            x = nx;
            y = ny;
        }
        return result;
    }
    
    boolean addToSet(Set<String> set, String str){
        if(set.contains(str)) return false;
        set.add(str);
        return true;
    }
}