package stepByStep.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


// 17점... , 곱연산 => 문자열 연산으로?
public class GasStation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] distances = stringToIntArr(br.readLine());
        int[] prices = stringToIntArr(br.readLine());

        // 곱, 합
        int minPrice = prices[0];
        int recentIdx = 0;
        int result = 0;
        for(int i = 0; i < n; i++){
            if(prices[i] <= minPrice){
                int distance = 0;
                for(int j = recentIdx; j < i; j++){
                    distance += distances[j];
                }
                result += minPrice * distance;

                minPrice = prices[i];
                recentIdx = i;

            }
        }
        System.out.println(result);
    }

    public static int[] stringToIntArr(String str){
        return Arrays
                .asList(str.split(" "))
                .stream()
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
