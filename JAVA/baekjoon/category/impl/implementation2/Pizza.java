package category.implementation2;
// 1756
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Pizza {
    static int d,n;
    static int[] dough, oven;
    static int solve(){
        int[] minArr = new int[d];
        minArr[0] = oven[0];
        int right = d;
        for(int i = 1; i < d; i++){
            int v = oven[i];
            if(v >= minArr[i-1]) minArr[i] = minArr[i-1];
            else minArr[i] = v;
        }

        // 이분 탐색 필요 없이 minArr의 맨 우측 idx부터 좌측으로 이동하는 O(d+n) 으로 풀이 가능.
        for(int i = 0; i < n; i++){
            int v = dough[i];
            int idx = binarySearch(v, right-1, minArr);
            if(idx == -1) return 0;
            right = idx;
        }
        return right+1;
    }

    static int binarySearch(int v, int right, int[] arr){
        int l = 0;
        int r = right;
        int res = -1;
        while(l <= r){
            int mid = (l+r)/2;
            if(arr[mid] >= v){
                if(mid > res) res = mid;
                l = mid+1;
            } else {
                r = mid-1;
            }
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        d = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        oven = new int[d];
        dough = new int[n];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < d; i++){
            oven[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i++){
            dough[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve());;
    }
}
