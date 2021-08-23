package stepByStep.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class GasStation2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
//        long[] distances = stringToIntArr(br.readLine());
        String[] distances = br.readLine().split(" ");

//        long[] prices = stringToIntArr(br.readLine());
        String[] prices = br.readLine().split(" ");

        // 곱, 합
        long minPrice = Long.parseLong(prices[0]);
//        long result = Long.parseUnsignedLong("0");
        long result = 0l;

        for(int i = 0; i < n-1; i++){
            long price = Long.parseLong(prices[i]);
            if(price < minPrice){
                minPrice = price;
            }
//            System.out.println(minPrice + ":" +distances[i]);
            result += minPrice * Long.parseLong(distances[i]);

        }

//        System.out.println(Long.toUnsignedString(result));
        System.out.println(result);
        br.close();

    }

}

/**
 4
 2 3 1    distance
 5 2 4 6  price
 */