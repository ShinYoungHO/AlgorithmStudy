package category.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BFSBitMask {
    static int n;
    static int m;
    static char[][] mtx;
    static boolean[][][] visited;
    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};
    static Map<Character, Integer> door = Map.of('A', 2, 'B', 4, 'C',8, 'D',16,'E',32,'F',64);;
    static Map<Character, Integer> key = Map.of('a', 2, 'b', 4, 'c',8, 'd',16,'e',32,'f',64);

    public static int solve(int r, int c){
        Queue<Coord> q = new LinkedList<>();
        q.add(new Coord(r, c, 0, 1));

        int x,y,dist,keys;
        int nx, ny;
        int k;
        char v;

        while(!q.isEmpty()){
            Coord qv = q.poll();
            x = qv.x;
            y = qv.y;
            dist = qv.dist;

            for(int i = 0; i < 4; i++){
                nx = x + dx[i];
                ny = y + dy[i];
                keys =  qv.keys;

                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                v = mtx[nx][ny];
                if(v == '#') continue;
                if(v == '1') return dist+1;

                // '.' or 'A-F' or 'a-f'
                if(door.containsKey(v)){
                    // A-F
                    k = door.get(v);
                    if((keys & k )!= k) continue; // 키가 없으면 다음으로
                } else if(key.containsKey(v)){
                    // a-f
                    k = key.get(v);
                    keys |= k; // 키 추가
                }

                if(visited[nx][ny][keys]) continue;

                visited[nx][ny][keys] = true;// 1번 키 2번키 따로 도착하면 1,2번 동시에 도착한 형태가 되버림

//                System.out.println("visited["+nx+"]["+ny+"]=" +visited[nx][ny]+", dst:"+(dist+1)+"   currentKey"+keys);
                q.add(new Coord(nx, ny, dist+1, keys));
            }

        }
        return -1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        mtx = new char[n][m];
        visited = new boolean[n][m][129];

        int x = 0,y = 0;
        
        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < m; j++){
                char c = str.charAt(j);
                mtx[i][j] = c;
                if(c == '0') {
                    x = i;
                    y = j;
                }
            }
        }

        System.out.println(solve(x, y));;

    }

    private static class Coord{
        int x,y,dist,keys;

        public Coord(int x, int y, int dist, int keys){
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.keys = keys;
        }
    }
}

/**
      10 5
 0        #a##1
 1        #B##A
 2        .....
 3        ####.
 4        .....
 5        C####
 6        .....
 7        ##.#.
 8        ...#.
 9        c##b0
 */