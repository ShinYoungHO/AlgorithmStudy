package category.pq;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;



public class MaxHeap {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>((Integer e1, Integer e2)->e2-e1);
        for(int i=0; i<n; i++){
            int v = Integer.parseInt(br.readLine());
            if(v==0){
                if(pq.isEmpty()) sb.append("0\n");
                else sb.append(pq.poll()).append("\n");
            } else {
                pq.add(v);
            }
        }

        System.out.println(sb);
    }
}
