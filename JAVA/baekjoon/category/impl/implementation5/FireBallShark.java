package category.implementation5;

import java.io.*;
import java.util.*;

public class FireBallShark {
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static int nPad;
    static void solve(Map[][] mtx, Queue<Map> q, int k, int N){
        int x,y;
        while(k > 0){
            int l = q.size();
            for(int i = 0; i < l; i++){
                Map m = q.poll();
                Queue<FireBall> fq = m.balls;
                while(!fq.isEmpty()){
                    FireBall f = fq.poll();
                    x = (m.x+dx[f.d]*f.s+nPad)%N;
                    y = (m.y+dy[f.d]*f.s+nPad)%N;
                    mtx[x][y].tmp.add(f);
                    q.add(mtx[x][y]);
                }
            }

            l = q.size();
            for(int i = 0; i < l; i++){
                Map p = q.poll();
                p.divide();
                q.add(p);
            }
            k--;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        nPad = 0;
        while(nPad < 1000) nPad+=N;
        Map[][] mtx = new Map[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                mtx[i][j] = new Map(i, j);
            }
        }
        int r,c,m,s,d;
        Queue<Map> q = new LinkedList<>();
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            r = Integer.parseInt(st.nextToken())-1;
            c = Integer.parseInt(st.nextToken())-1;
            m = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            mtx[r][c].balls.add(new FireBall(m,d,s));
            q.add(mtx[r][c]);
        }

        solve(mtx, q, K, N);
        int res = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                res += mtx[i][j].getTotalMass();
            }
        }
        System.out.println(res);
    }

    static class Map{
        Queue<FireBall> balls = new LinkedList<>();
        Queue<FireBall> tmp = new LinkedList<>();

        int x,y;

        public Map(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int getTotalMass(){
            Queue<FireBall> fq = balls;
            int m = 0;
            while(!fq.isEmpty()){
                m+=fq.poll().m;
            }
            return m;
        }

        void divide(){
            Queue<FireBall> t = tmp;
            Queue<FireBall> v = balls;
            if(t.size() == 0) return;
            if(t.size() == 1) {
                v.add(t.poll());
                return;
            }
            int mass = 0;
            int s = 0;
            int len = t.size();
            int pad;
            int[] tm = {0, 0};
            for(int i = 0; i < len; i++){
                FireBall f = t.poll();
                mass+=f.m;
                s+=f.s;
                if(f.d % 2 == 0) tm[0]++;
                else tm[1]++;
            }
            if(tm[0] > 0 && tm[1] > 0){
                pad = 1;
            } else pad = 0;
            mass/=5;
            s/=len;
            if(mass == 0) return;
            for(int i = 0; i < 8; i+=2){
                v.add(new FireBall(mass,i+pad,s));
            }
        }

    }

    static class FireBall{
        int m,d,s;

        public FireBall(int m, int d, int s) {
            this.m = m;
            this.d = d;
            this.s = s;
        }
    }
}
