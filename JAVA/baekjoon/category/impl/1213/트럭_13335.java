package category.ns.solved;

import java.io.*;
import java.util.*;

public class 트럭_13335 {
    static int n,w,l;

    static void solve(int[] d){
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < w; i++) q.add(0);

        int total = 0;
        int time = 0;
        int i = 0;
        while(i < n){
            time++;
            int weight = d[i];
            total -= q.poll();
            if(weight+total <= l){
                q.add(weight);
                total+=weight;
                i++;
            } else {
                q.add(0);
            }
        }

        while(total != 0){
            time++;
            total-=q.poll();
        }
        System.out.println(time);
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        int[] d = new int[n];

        st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < n; i++){
            d[i] = Integer.parseInt(st.nextToken());
        }

        solve(d);
    }
}
