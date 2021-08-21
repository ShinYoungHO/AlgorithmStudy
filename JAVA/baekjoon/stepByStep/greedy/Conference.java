package stepByStep.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Conference {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] conferences = new int[n][2];
        for(int i = 0; i < n; i++){
            String[] conf = br.readLine().split(" ");
            conferences[i][0] = Integer.parseInt(conf[0]);
            conferences[i][1] = Integer.parseInt(conf[1]);
        }
        Arrays.sort(conferences, (c1, c2) -> {
            if(c1[1] > c2[1]) return 1;
            else if(c1[1] < c2[1]) return -1;
            else {
                if(c1[0] > c2[0]) return 1;
                else return -1;
            }
        });

        int count = 0;
        int time = 0;
        for(int i = 0; i < n; i++){
            if(time <= conferences[i][0]) {
                count++;
                time = conferences[i][1];
            }
        }
        System.out.println(count);
    }
}
