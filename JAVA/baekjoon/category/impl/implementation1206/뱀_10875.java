package category.ns.solved;

import java.io.*;
import java.util.*;

public class 뱀_10875 {
    static int l;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int INF = Integer.MAX_VALUE;
    static long solve(int l, int[][] queries){
        int x = 0;
        int y = 0;
        int d = 0;
        long curTime = 0;
        int M,P;
        M = -1*(l+1);
        P = l+1;


        List<Line> lines = new ArrayList<>();

        // 바깥 라인
        lines.add(new Line(M,M,M,P,0));
        lines.add(new Line(M,P,P,P,1));
        lines.add(new Line(P,P,P,M,0));
        lines.add(new Line(P,M,M,M,1));

        for(int i = 0; i < queries.length; i++){
            int[] query = queries[i];
            int t = query[0];
            int dd = query[1]; // dDir

            if(i!=0) {
                x+=dx[d];
                y+=dy[d];
            } else {
                t++;
            }
            int nx = x+dx[d]*(t-1);
            int ny = y+dy[d]*(t-1);

            Line line = new Line(x,y,nx,ny,d%2);

            // 겹치는 선분들 중 최소 거리
            int v = INF;

            for(int j = 0; j < lines.size(); j++){
                Line ll = lines.get(j);
                int k;

                if(line.dir==ll.dir) k = parallel(line, ll, line.dir);
                else k = cross(line, ll, line.dir);

                // 겹치는 경우 반환된 인덱스 활용
                if (k != INF) v = Math.min(v, d % 2 == 0 ? Math.abs(y-k) : Math.abs(x-k));
            }

            if(v != INF){
                curTime+=v;
                return curTime;
            }
            d=(d+dd+4)%4;
            x=nx;
            y=ny;
            curTime+=t;
            lines.add(line);
        }

        return curTime;
    }

    static int cross(Line target, Line inLines, int dir){
        if(dir==0){
            if(target.fx>=inLines.fx && target.fx<=inLines.tx
                    && target.fy<=inLines.fy && inLines.fy<=target.ty){
                return inLines.fy;
            }
        } else {
            if(target.fx<=inLines.fx&&inLines.fx<=target.tx
                    && inLines.fy<=target.fy&&target.fy<=inLines.ty){
                return inLines.fx;
            }
        }
        return INF;
    }

    static int parallel(Line target, Line inLines, int dir){
        if(dir == 0){
            if(target.fx == inLines.fx){
                if(target.ty>=inLines.fy&&target.fy<=inLines.ty){
                    return target.fy<=inLines.fy&&inLines.fy<=target.ty?inLines.fy:inLines.ty;
                }
            }
        } else {
            if(target.fy == inLines.fy){
                if(target.tx>=inLines.fx&&target.fx<=inLines.tx){
                    return target.fx<=inLines.fx&&inLines.fx<=target.tx?inLines.fx:inLines.tx;
                }
            }
        }
        return INF;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        l = Integer.parseInt(br.readLine());
        int t = Integer.parseInt(br.readLine());
        int[][] query = new int[t+1][2];
        for(int i = 0; i <= t; i++){
            int time,dd;
            if(i == t) {
                // 마지막 직진 처리
                time = 2 * l + 1;
                dd = 0;
            } else {
                st = new StringTokenizer(br.readLine()," ");
                time = Integer.parseInt(st.nextToken());
                dd = st.nextToken().equals("L") ? -1 : 1;
            }
            query[i][0] = time;
            query[i][1] = dd;
        }
        System.out.println(solve(l, query));
    }

    private static class Line{
        int dir;
        int fx,fy,tx,ty;

        public Line(int x1, int y1, int x2, int y2, int dir) {
            if(x1==x2){
                this.fy=Math.min(y1,y2);
                this.ty=Math.max(y1,y2);
                this.fx=x1;
                this.tx=x2;
                this.dir=dir; // ㅡㅡㅡㅡㅡㅡㅡ
            } else {
                this.fx=Math.min(x1,x2);
                this.tx=Math.max(x1,x2);
                this.fy=y1;
                this.ty=y2;
                this.dir=dir; // |
            }
        }

        @Override
        public String toString() {
            return "fxy,txy : "+this.fx+":"+this.fy+","+this.tx+":"+this.ty+"  "+this.dir;
        }
    }
}

/*
TestCase
https://www.acmicpc.net/board/view/14336
 */