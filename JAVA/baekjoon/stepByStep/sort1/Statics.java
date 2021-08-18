package stepByStep.sort1;

import java.io.*;

public class Statics {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] bucket = new int[8001];
        int max = -4001;
        int min = 4001;
        int sum = 0;
        int mid = n/2;

        for(int i = 0; i < n; i++) {
            int v = Integer.parseInt(br.readLine());
            sum += v;
            bucket[4000+v]++;
        }


        int count = 0;
        int maxCount = -1;
        int maxValue = -1;
        boolean overlap = false;
        int midValue = 4001;
        for(int i = 0; i < 8001; i++){
            int value = bucket[i];
            if(value > 0){
                if(min == 4001) min = i-4000;
                max = i-4000;
                count+=value;
                if(count > mid && midValue == 4001){
                    midValue = i-4000;
                }
                if(value >= maxCount){
                    if(value == maxCount){
                        if(overlap) continue;
                        overlap = true;
                    }
                    if(value > maxCount) overlap = false;
                    maxCount = value;
                    maxValue = i-4000;
                }
            }
        }

        System.out.println(Math.round((double)sum/(double)n));
        System.out.println(midValue); //
        System.out.println(maxValue); //
        if(n == 1) System.out.println(0);
        else System.out.println(max-min);

        br.close();
    }
}
/**
 첫째 줄에는 산술평균을 출력한다. 소수점 이하 첫째 자리에서 반올림한 값을 출력한다.

 둘째 줄에는 중앙값을 출력한다.

 셋째 줄에는 최빈값을 출력한다. 여러 개 있을 때에는 최빈값 중 두 번째로 작은 값을 출력한다.

 넷째 줄에는 범위를 출력한다.
 */