import java.util.*;

class Solution {
    char[][] cBoard;
    TreeMap<Character, int[]> map;
    public String solution(int m, int n, String[] board) {
        cBoard = new char[m][n];
        map = new TreeMap<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                char c = board[i].charAt(j);
                cBoard[i][j] = c;
                if(c == '.' || c == '*') continue;
                if(map.get(c) != null){
                    int[] tmp = map.get(c);
                    tmp[2] = i;
                    tmp[3] = j;
                } else {
                    map.put(c, new int[]{i, j, 0, 0});
                }
            }
        }

        return solve();
    }

    String solve(){
        StringBuilder sb = new StringBuilder();
        while(!map.isEmpty()){
            boolean isChanged = false;
            for(Character k : map.keySet()){
                int[] tmp = map.get(k);
                boolean result = isValid(tmp[0], tmp[1], tmp[2], tmp[3], k);
                if(result){
                    cBoard[tmp[0]][tmp[1]] = '.';
                    cBoard[tmp[2]][tmp[3]] = '.';
                    isChanged = true;
                    map.remove(k);
                    sb.append(k);
                    break;
                }
            }
            if(!isChanged) return "IMPOSSIBLE";
        }
        return sb.toString();
    }

    boolean isValid(int x1, int y1, int x2, int y2, Character c){
        if(x1 > x2 && y1 > y2){
            return (chkCol(y2, y1, x1, c) && chkRaw(x2, x1, y2, c)) || (chkCol(y2, y1, x2, c) && chkRaw(x2, x1, y1, c));
        } else if(x1 < x2 && y1 < y2){
            return (chkCol(y1, y2, x1, c) && chkRaw(x1, x2, y2, c)) || (chkCol(y1, y2, x2, c) && chkRaw(x1, x2, y1, c));
        } else if(x1 > x2 && y1 < y2){
            return (chkCol(y1, y2, x1, c) && chkRaw(x2, x1, y2, c)) || (chkCol(y1, y2, x2, c) && chkRaw(x2, x1, y1, c));
        } else if(x1 < x2 && y1 > y2){
            return (chkCol(y2, y1, x1, c) && chkRaw(x1, x2, y2, c)) || (chkCol(y2, y1, x2, c) && chkRaw(x1, x2, y1, c));
        }

        if(x1 == x2) return y1 > y2 ? chkCol(y2, y1, x1, c) : chkCol(y1, y2, x1, c);
        if(y1 == y2) return x1 > x2 ? chkRaw(x2, x1, y1, c) : chkRaw(x1, x2, y1, c);
        return true;
    }

    boolean chkRaw(int f, int t, int c, Character ch){
        for(int i = f; i <= t; i++){
            if(i == f && cBoard[i][c] == ch) continue;
            if(i == t && cBoard[i][c] == ch) continue;
            if(cBoard[i][c] != '.' && cBoard[i][c] != ch) return false;
        }
        return true;
    }

    boolean chkCol(int f, int t, int r, Character ch){
        for(int i = f; i <= t; i++){
            if(i == f && cBoard[r][i] == ch) continue;
            if(i == t && cBoard[r][i] == ch) continue;
            if(cBoard[r][i] != '.' && cBoard[r][i] != ch) return false;
        }
        return true;
    }
}