package category.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Gear {
    static int[][] gears;
    static int l = 6;
    static int r = 2;
    static void solve(int num, int dir){
        int left = gears[num][l];
        int right = gears[num][r];
        int prevDir;
        int[] candi = new int[4];
        candi[num] = dir;

        prevDir = dir;
        for(int i = num+1; i < 4; i++){
            if(gears[i][l] == right) break;
            right = gears[i][r];
            prevDir*=-1;
            candi[i] = prevDir;
        }

        prevDir = dir;
        for(int i = num-1; i >= 0; i--){
            if(gears[i][r] == left) break;
            left = gears[i][l];
            prevDir*=-1;
            candi[i] = prevDir;
        }

        for(int i = 0; i < 4; i++){
            if(candi[i] != 0){
                spin(i, candi[i]);
            }
        }
    }


    static void spin(int num, int dir){
        int[] target = gears[num];
        int tmp;
        if(dir == 1){
            // >>
            tmp = target[7];
            for(int i = 7; i > 0; i--) target[i] = target[i-1];
            target[0] = tmp;
        } else {
            // <<
            tmp = target[0];
            for(int i = 1; i < 8; i++) target[i-1] = target[i];
            target[7] = tmp;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        gears = new int[4][8];
        int result = 0;

        for(int i = 0; i < 4; i++){
            String gear = br.readLine();
            for(int j = 0; j < 8; j++){
                gears[i][j] = gear.charAt(j)-'0';
            }
        }

        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine()," ");
            int number = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            solve(number-1, direction);
        }

        for(int i = 0; i < 4; i++){
            result+=gears[i][0]*Math.pow(2, i);
        }
        System.out.println(result);
    }
}
