package category.twoPointer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SubTotal {
    static int res;
    static int n;
    static int s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        res = n+2;
        st = new StringTokenizer(br.readLine(), " ");

        for(int i = 1; i <= n; i++){
            int v = Integer.parseInt(st.nextToken());
            arr[i-1] = v;
        }
        solve2(arr);
        if(res == n+2) System.out.println(0);
        else System.out.println(res);
        br.close();
    }

    static void setMin(int left, int right){
        System.out.println("setMin"+left+":"+right);
        if(res > right-left+1) res = right-left+1;
    }


    static void solve2(int[] arr){
        int left = 0;
        int right = 0;
        int v = arr[0];
        while(left <= right){
            if(v < s && right+1 < n){
                v += arr[++right];
            } else if(v >= s){
                if(left==right){
                    setMin(0, 0);
                    return;
                }
                setMin(left, right);
                v-=arr[left++];
            } else break;

        }
    }
}