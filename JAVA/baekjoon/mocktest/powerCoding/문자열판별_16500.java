package mocktest.powerCoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.*;
import java.util.*;


// backtracking

public class 문자열판별_16500 {
    static void solve(String target, String[] words, int n){
        // n : words.length
        int[][] dp = new int[target.length()][n];
        boolean[] used = new boolean[n];

        for(int i = 0; i < words.length; i++){
            String word = words[i];
            for(int j = 0; j < target.length(); j++){
                boolean isValid = true;
                for(int k = 0; k < word.length(); k++){
                    if(j+k >= target.length() || word.charAt(k) != target.charAt(j+k)){
                        isValid = false;
                        break;
                    }
                }
                if(isValid) dp[j][i] = word.length();
            }
        }

        if(!bt(dp, used, 0, target.length())){
            System.out.println(0);
        }
    }

    static boolean bt(int[][] dp, boolean[] used, int start, int max){
        if(start == max) {
            System.out.println(1);
            return true;
        }
        if(start > max) return false;

        for(int i = 0; i < used.length; i++){
            if(!used[i] && dp[start][i] > 0) {
                used[i] = true;
                if(bt(dp, used, start+dp[start][i], max)) return true;
                used[i] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String target = br.readLine();
        int n = Integer.parseInt(br.readLine());

        String[] words = new String[n];

        for(int i = 0; i < n; i++){
            words[i] = br.readLine();
        }

        solve(target, words, n);
    }
}



