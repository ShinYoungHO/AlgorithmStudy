package category.etc;

import java.io.*;
import java.util.*;

public class TellMid {
    static PriorityQueue<Integer> lpq;
    static PriorityQueue<Integer> rpq;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        lpq = new PriorityQueue<>((r, v) -> v - r);
        rpq = new PriorityQueue<>();

        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            int k = Integer.parseInt(br.readLine());
            if(lpq.size() > rpq.size()){
                rpq.add(k);
                lpq.add(rpq.poll());
                rpq.add(lpq.poll());
            }
            else {
                lpq.add(k);
                rpq.add(lpq.poll());
                lpq.add(rpq.poll());
            }
            if(lpq.size()==rpq.size()) k = Math.min(lpq.peek(), rpq.peek());
            else if(lpq.size() > rpq.size()) k = lpq.peek();
            else k = rpq.peek();
            sb.append(k).append("\n");
        }
        System.out.println(sb);
    }
}
