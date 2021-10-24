package category.etc;
// BFS
import java.io.*;
import java.util.*;

public class HideAndSeek {

    static int solve(Queue<int[]> q, int f, int t){
        if(f == t) return 0;
        q.add(new int[]{f, 0});
        boolean[] visited = new boolean[100002];
        int x,y,z,v,c;
        while(true){
            int l = q.size();
            for(int i = 0; i < l; i++){
                int[] qv = q.poll();
                v = qv[0];
                c = qv[1];
                x = v*2;
                y = v-1;
                z = v+1;
                if(x == t || y == t || z == t) return c+1;
                if(!OOB(x)&&!visited[x]) {
                    visited[x] = true;
                    q.add(new int[]{x, c + 1});
                }
                if(!OOB(y)&&!visited[y]) {
                    visited[y] = true;
                    q.add(new int[]{y, c + 1});
                }
                if(!OOB(z)&&!visited[z]) {
                    visited[z] = true;
                    q.add(new int[]{z, c + 1});
                }

            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        Queue<int[]> q = new LinkedList<>();

        int f = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        System.out.println(solve(q, f, t));

    }

    static boolean OOB(int x){
        if(x < 0 || x >= 100002) return true;
        return false;
    }
}
