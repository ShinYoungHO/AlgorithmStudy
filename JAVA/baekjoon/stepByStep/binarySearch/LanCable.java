package stepByStep.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LanCable {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);
        int[] cables = new int[n];

        for(int i = 0; i < n; i++) cables[i] = Integer.parseInt(br.readLine());
        Arrays.sort(cables);

        long left = 1;
        long right = cables[n-1];

        while(left < right){
            int tmp = 0;
            long mid = getMid(left, right+1);
            for(int i = n-1; i >= 0; i--){
                int v = cables[i];
                if(v < mid) break;
                tmp += v/mid;
                if(tmp > k) break;
            }

            if(tmp < k){
                right = mid-1;
            } else {
                left = mid;
            }
        }
        System.out.println(left);
        br.close();
    }

    public static long getMid(long a, long b){
        long A = a;
        long B = b;
        while(B != 0){
            long carry = A & B;
            A = A ^ B;
            B = carry << 1;
        }
        System.out.println(a+"+"+b+"="+A);
        return A >> 1;
    }
}
