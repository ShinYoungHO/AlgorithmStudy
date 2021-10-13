package mocktest.powerCoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.InputMismatchException;

public class 화살표그리기_15975 {

    static long solve(PriorityQueue<Node> pq){
        HashMap<Integer, Color> colors = new HashMap<>();
        Color c;
        long res = 0;
        while(!pq.isEmpty()){
            Node n = pq.poll();

            c = colors.get(n.color);
            if(c == null) {
                c = new Color(n.color);
                colors.put(n.color, c);
            };

            if(c.cv == -1){
                c.cv = n.idx;
                continue;
            }
            if(c.pv == -1){
                c.pv = c.cv;
                c.cv = n.idx;
                res+=c.cv-c.pv;
                continue;
            }
            res+=Math.min(n.idx-c.cv, c.cv-c.pv);
            c.pv = c.cv;
            c.cv = n.idx;
        }

        for(Integer key : colors.keySet()){
            Color v = colors.get(key);
            if(v.cv != -1 && v.pv != -1) res+=v.cv-v.pv;
        }

        return res;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Node> pq = new PriorityQueue<>((Node n1, Node n2) -> n1.idx - n2.idx);

        int idx,color;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            idx = Integer.parseInt(st.nextToken());
            color = Integer.parseInt(st.nextToken());
            pq.add(new Node(idx, color));
        }

        System.out.println(solve(pq));
    }


    static class Node{
        int idx, color;
        public Node(int i, int c){
            this.idx = i;
            this.color = c;
        }
    }
    static class Color{
        int pv = -1;
        int cv = -1;
        int c;
        public Color(int c){
            this.c = c;
        }
    }
}



