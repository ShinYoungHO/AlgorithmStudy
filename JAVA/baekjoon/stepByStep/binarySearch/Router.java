package stepByStep.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Router {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//        String[] input = br.readLine().split(" ");
//        int n = Integer.parseInt(input[0]);
//        int k = Integer.parseInt(input[1]);
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] routers = new int[n][2];

        for(int i = 0; i < n; i++) {
            int v = Integer.parseInt(br.readLine());
            routers[i] = new int[]{v, 0};
        }
//        Arrays.sort(routers, Comparator.comparingInt(e -> e[0]));
        Arrays.sort(routers, (l, r) -> {
            return l[0] - r[0];
        });

        for(int i = 1; i < n; i++){
            routers[i][1] = routers[i][0] - routers[i-1][0];
        }
        // k개 설치,
        int left = 0;
        int right = routers[n-1][0];
        int dst = 0;

        while(left <= right){
            int mid = (left+right) >> 1;
            int cnt = 1;
            int curLen = 0;

            for(int i = 0; i < n; i++){
                curLen += routers[i][1];
                if(curLen >= mid){
                    cnt++;
                    curLen = 0;
                }
            }
            if(cnt < k){
                right = mid-1;
            } else {
                if(mid > dst) dst = mid;
                left = mid+1;
            }
        }
        System.out.println(dst);
        br.close();
    }
}
// 1 2 4 8 9