package category.etc2;

import java.io.*;
import java.util.*;

public class MooPath3 {
    static PriorityQueue<int[]> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>((n1, n2) -> n1[0]-n2[0]);
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int arrive = Integer.parseInt(st.nextToken());
            int due = Integer.parseInt(st.nextToken());
            pq.add(new int[]{arrive, due});
        }
        int time = 0;
        while(!pq.isEmpty()){
            int[] qv = pq.poll();
            if(qv[0] > time) time = qv[0];
            time+=qv[1];
        }
        System.out.println(time);
    }
}
