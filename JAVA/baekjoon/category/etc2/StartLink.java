package category.etc2;

import java.io.*;
import java.util.*;

public class StartLink {
    static int f,s,g,u,d;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        f = Integer.parseInt(st.nextToken())+1; // 총 f 층
        s = Integer.parseInt(st.nextToken()); // from
        g = Integer.parseInt(st.nextToken()); // to
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        if(s == g) {
            System.out.println(0);
            return;
        }
        int[] visited = new int[f];
        int res = -1;
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        int nv;
        while(!q.isEmpty()){
            int v = q.poll();
            for(int i = 0; i < 2; i++){
                nv = next(i, v);
                if(nv == v) continue;
                if(nv <= 0 || nv >= f || visited[nv] > 0) continue;
                if(nv == g) {
                    res = visited[v] + 1;
                    break;
                }
                visited[nv] = visited[v]+1;
                q.add(nv);
            }
        }

        System.out.println(res == -1 ? "use the stairs":res);
    }

    static int next(int i, int k){
        if(i == 0) return k+u;
        return k-d;
    }
}
