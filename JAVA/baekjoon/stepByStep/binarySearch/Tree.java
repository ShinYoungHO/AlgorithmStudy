package stepByStep.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Tree {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int[] trees = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), ", ");

        for(int i = 0; i < n; i++) trees[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(trees);

        int max = trees[n-1];

        long left = 0;
        long right = max;

        while(left < right){
            long mid = (left+right+1)/2;
            long calculate = 0;
            for(int i = n-1; i >= 0; i--){
                int v = trees[i];
                if(v < mid) break;
                calculate += v-mid;
            }

            if(calculate < m){
                right = mid-1;
            } else {
                left = mid;
            }
        }
        System.out.println(left);
        br.close();
    }
}
