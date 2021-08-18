package stepByStep.sort1;

import java.io.*;

public class CountingSort {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bf.readLine());
        int[] bucket = new int[10001];

        for(int i = 0; i < n; i++) {
            int v = Integer.parseInt(bf.readLine());
            bucket[v]++;
        }

        for(int i = 0; i < 10001; i++){
            int c = bucket[i];
            if(c > 0){
                for(int j = 0; j < c; j++){
                    bw.write(i+"\n");
                }
            }
        }

        bw.flush();
        bw.close();
        bf.close();
    }
}
