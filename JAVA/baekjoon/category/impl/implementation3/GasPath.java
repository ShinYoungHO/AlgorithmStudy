package category.implementation3;
// 2931
import java.io.*;
import java.util.*;

public class GasPath {
    static int r,c;
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static int[] dBit = new int[]{1, 2, 4, 8};
    static Map<Character, Integer> bit;

    static void solve(char[][] map){
        int[][] memo = new int[r][c];

        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(map[i][j] == '.') continue;
                add(map, i, j, memo);
            }
        }

        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(memo[i][j] <= 0) continue;
                int t = 0;
                for(int k = 0; k < 4; k++){
                    int nx = i+dx[k];
                    int ny = j+dy[k];
                    if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                    if(memo[nx][ny]<0) t|=dBit[k];
                }

                for(Character key : bit.keySet()){
                    if(bit.get(key) == t){
                        System.out.println((i+1)+" "+(j+1)+" "+key);
                        return;
                    }
                }
            }
        }
    }

    static void add(char[][] map, int x, int y, int[][] memo){
        char ch = map[x][y];
        if(ch == 'M' || ch == 'Z') {
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx<0 || ny<0 || nx >= r || ny >= c) continue;
                if(map[nx][ny] != '.') {
                    memo[nx][ny]++;
                    break;
                }
            }
            memo[x][y]--;
            return;
        }
        int t = bit.get(ch);
        int d = 0;
        for(int i = 0; i < 4; i++){
            int v = dBit[i];
            if((t&v) != 0){
                memo[x+dx[i]][y+dy[i]]++;
                d++;
            }
        }
        memo[x][y]-=d;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        bit = Map.of('-',10,'+',15, '|',5,'1',6, '2',3,'3',9,'4',12);
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        char[][] map = new char[r][c];
        for(int i = 0; i < r; i++){
            String l = br.readLine();
            for(int j = 0; j < c; j++){
                map[i][j] = l.charAt(j);
            }
        }
        solve(map);
    }
}
