package mocktest.powerCoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 청소년상어_19236 {
    static int[] dx = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = new int[]{0, -1, -1, -1, 0, 1, 1, 1};
    static int answer;

    static void bt(Fish[][] map, int sx, int sy, int dir, int result){
        Fish tmp;
        Fish f;
        int x,y,nx,ny;
        PriorityQueue<Fish> pq = new PriorityQueue<>((Fish f1, Fish f2) -> f1.number-f2.number);

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                Fish a = map[i][j];
                if(a.dead) continue;
                pq.add(a);
            }
        }

        while(!pq.isEmpty()){
            f = pq.poll();
            x = f.x;
            y = f.y;
            f.dir--;
            for(int j = 0; j < 8; j++){
                int k = (1 + f.dir) % 8;
                f.dir = k;
                nx = x + dx[k];
                ny = y + dy[k];
                if(nx < 0 || ny < 0 || nx >= 4 || ny >= 4) continue;
                if(nx == sx && ny == sy) continue;
                tmp = map[nx][ny];
                map[nx][ny] = map[x][y];
                map[x][y] = tmp;
                map[x][y].swapPos(map[nx][ny]);
                break;
            }
        }
        x = sx;
        y = sy;
        for(int i = 0; i < 3; i++){
            x += dx[dir];
            y += dy[dir];
            if(x < 0 || y < 0 || x >= 4 || y >= 4) break;
            if(map[x][y].dead) continue;
            f = map[x][y];
            f.dead = true;
            bt(slice(map), x, y, f.dir, result + f.number);
            f.dead = false;
        }

        if(answer < result) answer = result;
    }


    static Fish[][] slice(Fish[][] map){
        Fish[][] newMap = new Fish[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                newMap[i][j] = map[i][j].slice();
            }
        }
        return newMap;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        answer = 0;
        Fish[][] map = new Fish[4][4];
        for(int i = 0; i < 4; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < 4; j++){
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken())-1;
                map[i][j] = new Fish(a, b, i, j);
            }
        }

        int result = map[0][0].number;
        int dir = map[0][0].dir;
        map[0][0].dead = true;
        bt(map, 0, 0, dir, result);

        System.out.println(answer);
    }

    static class Fish{
        boolean dead = false;
        int number;
        int dir;
        int x,y;
        Fish(int number, int dir, int x, int y){
            this.number = number;
            this.dir = dir;
            this.x = x;
            this.y = y;
        }

        Fish slice(){
            Fish f = new Fish(this.number, this.dir, this.x, this.y);
            f.dead = this.dead;
            return f;
        }
        void swapPos(Fish f){
            int nx, ny;
            nx = f.x;
            ny = f.y;

            f.x = this.x;
            f.y = this.y;
            this.x = nx;
            this.y = ny;
        }
    }
}


// bet

