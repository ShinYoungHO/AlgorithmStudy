package stepByStep.sort2;

import java.io.*;
import java.util.Arrays;

public class Users {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] order = new int[n][2];
        String[] values = new String[n];

        for(int i = 0; i < n; i++){
            String[] value = br.readLine().split(" ");
            order[i][0] = i;
            order[i][1] = Integer.parseInt(value[0]);
            values[i] = value[1];
        }

        Arrays.sort(order, (e1, e2) -> {
            if(e1[1] > e2[1]) return 1;
            else if(e1[1] < e2[1]) return -1;
            else {
                if(e1[0] > e2[0]) return 1;
                else return -1;
            }
        });

        for(int i = 0; i < n; i++){
            bw.write(order[i][1] +" "+ values[order[i][0]]+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

