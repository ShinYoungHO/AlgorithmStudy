package stepByStep.sort1;

import java.io.*;
import java.util.Arrays;

public class SortAPI {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bf.readLine());
        int[] target = new int[n];

        for(int i = 0; i < n; i++) target[i] = Integer.parseInt(bf.readLine());

        Arrays.sort(target);

        for(int i = 0; i < n; i++) bw.write(target[i]+"\n");
        bw.flush();
        bf.close();
        bw.close();
    }
}
