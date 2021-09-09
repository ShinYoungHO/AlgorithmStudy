package sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Easy2048 {
    static int[] order = new int[5];
    static int n;
    static int[][] mtx;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        mtx = new int[n][n];
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++){
                mtx[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        generate(0);
        System.out.println(max);
        br.close();
    }


    static void generate(int idx){
        if(idx == 5){
            System.out.println(Arrays.toString(order));
            int[][] tmp = new int[n][n];
            for(int i = 0; i < n; i++){
                tmp[i] = mtx[i].clone();
            }
            solve(tmp);
            return;
        }
        for(int i = 0; i < 4; i++){
            order[idx] = i;
            generate(idx+1);
        }
    }

    static void printAll(int[][] matrix){
        for(int i = 0; i < n; i++){
            System.out.println(Arrays.toString(matrix[i]));;
        }
        System.out.println("----------------------");
    }

    static void solve(int[][] target){
        LinkedList<Integer> l = new LinkedList<>();
        printAll(target);

        for(int i = 0; i < 5; i++){
            int t = 0;
            if(order[i] == 0){
                for(int j = 0; j < n; j++) {
                    boolean flip = false;
                    for (int k = 0; k < n; k++) {
                        if (!l.isEmpty() && l.peekLast() == target[k][j] && !flip) {
                            l.pollLast();
                            l.add(t = target[k][j] << 1);
                            flip = true;
                        } else if((t = target[k][j]) != 0) {
                            flip = false;
                            l.add(target[k][j]);
                        }
                        if(t > max) max = t;
                    }
                    for (int k = 0; k < n; k++) {
                        if (l.isEmpty()) target[k][j] = 0;
                        else target[k][j] = l.pollFirst();
                    }
                }
            }
            else if(order[i] == 1){
                for(int j = 0; j < n; j++) {
                    boolean flip = false;

                    for (int k = n-1; k >= 0; k--) {
                        if (!l.isEmpty() && l.peekLast() == target[k][j] && !flip) {
                            l.pollLast();
                            l.add(t = target[k][j] << 1);
                            flip = true;
                        } else if((t = target[k][j]) != 0) {
                            l.add(target[k][j]);
                            flip = false;
                        }
                        if(t > max) max = t;
                    }
                    for (int k = n-1; k >= 0; k--) {
                        if (l.isEmpty()) target[k][j] = 0;
                        else target[k][j] = l.pollFirst();
                    }
                }
            }
            else if(order[i] == 2){
                for(int j = 0; j < n; j++) {
                    boolean flip = false;
                    for (int k = n-1; k >= 0; k--) {
                        if (!l.isEmpty() && l.peekLast() == target[j][k] && !flip) {
                            l.pollLast();
                            l.add(t = target[j][k] << 1);
                            flip = true;
                        } else if((t = target[j][k]) != 0) {
                            l.add(target[j][k]);
                            flip = false;
                        }
                        if(t > max) max = t;
                    }
                    for (int k = n-1; k >= 0; k--) {
                        if (l.isEmpty()) target[j][k] = 0;
                        else target[j][k] = l.pollFirst();
                    }
                }
            }
            else {
                for(int j = 0; j < n; j++) {
                    boolean flip = false;
                    for (int k = 0; k < n; k++) {
                        if (!l.isEmpty() && l.peekLast() == target[j][k]  && !flip) {
                            l.pollLast();
                            l.add(t = target[j][k] << 1);
                            flip = true;
                        } else if((t = target[j][k]) != 0) {
                            l.add(target[j][k]);
                            flip = false;
                        }
                        if(t > max) max = t;
                    }
                    for (int k = 0; k < n; k++) {
                        if (l.isEmpty()) target[j][k] = 0;
                        else target[j][k] = l.pollFirst();
                    }
                }
            }
            printAll(target);

        }
    }
}
