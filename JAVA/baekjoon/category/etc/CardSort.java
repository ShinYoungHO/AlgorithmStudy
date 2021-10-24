package category.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class CardSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for(int i = 0; i < n; i++){
            pq.add(Long.parseLong(br.readLine()));
        }

        long res = 0;
        while(pq.size() >= 2){
            long k = pq.poll()+pq.poll();
            pq.add(k);
            res+=k;
        }
        System.out.println(res);
    }
}
