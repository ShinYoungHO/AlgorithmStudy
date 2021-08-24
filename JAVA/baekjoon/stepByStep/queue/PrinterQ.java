package stepByStep.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PrinterQ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            String[] input = br.readLine().split(" ");
            int[] documents = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int m = Integer.parseInt(input[0]);
            int idx = Integer.parseInt(input[1]);
            int prior = 0;
            SubPQ subQ = new SubPQ();
            for(int j = 0; j < m; j++){
                // [idx, value]
                subQ.add(new int[]{j, documents[j]});
                if(j == idx) prior = documents[j];
            }
            subQ.setPrior(documents);
            int result = 0;
            int id = subQ.q.peek()[0];
            int v = subQ.q.peek()[1];
            int curPrior = subQ.prior.peek();
            while(curPrior != prior || idx != id){
                if(v < curPrior) {
                    subQ.q.add(subQ.q.poll());
                }
                else {
                    result++;
                    subQ.prior.poll();
                    curPrior = subQ.prior.peek();
                    subQ.q.poll();
                }
                id = subQ.q.peek()[0];
                v = subQ.q.peek()[1];
            }
            sb.append((result+1)+"\n");
        }

        System.out.println(sb);
    }
}

class SubPQ {
    Queue<Integer> prior = new LinkedList<>();
    Queue<int[]> q = new LinkedList<>();

    public void setPrior (int[] arr) {
        int[] arr2 = Arrays.stream(arr).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();
        for(int i = 0; i < arr2.length; i++){
            prior.add(arr2[i]);
        }
    }
    public void add(int[] v){
        q.add(v);
    }
}