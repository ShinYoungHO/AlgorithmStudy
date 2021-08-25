package stepByStep.dnc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Confetti {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] matrix = new int[n][n];

        Queue<int[][]> q = new LinkedList();
        q.add(matrix);

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int j = 0;
            while(st.hasMoreTokens()){
                matrix[i][j] = Integer.parseInt(st.nextToken());
                j++;
            }
        }

        int[] result = new int[2];
        while(!q.isEmpty()){
            int[][] partialMatrix = q.poll();
            int color = partialMatrix[0][0];
            int len = partialMatrix.length;
            if(isSquare(partialMatrix, len, color)){
                result[color]++;
            } else {
                divide(partialMatrix, len, q);
            }
        }
        br.close();
        System.out.println(result[0]+"\n"+result[1]);
    }

    public static boolean isSquare(int[][] matrix, int len, int color){
        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                if(matrix[i][j] != color){
                    return false;
                }
            }
        }
        return true;
    }

    public static void divide(int[][] matrix, int len, Queue<int[][]> q){
//        System.out.println("len : "+len);
        for(int i = 0; i < 4; i++){
            int x = i % 2;
            int y = i / 2;
            int n = len / 2;
            int[][] partial = new int[n][n];


            int first = n * (x + 1);
            int second = n * (y + 1);

            int partialIdx = 0;
            for(int j = n * x; j < first; j++){
                int[] line = new int[n];
                int idx = 0;
                for(int k = n * y; k < second; k++){
                    line[idx] = matrix[j][k];
                    idx++;
                }
                partial[partialIdx] = line;
                partialIdx++;
            }
            q.add(partial);
        }
    }
}
