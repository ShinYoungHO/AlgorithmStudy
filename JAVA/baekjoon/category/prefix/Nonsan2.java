package category.prefix;
// 다른분 코드. 어쩐지 실버1 문제치고 어렵더라구요...
import java.util.*;
import java.io.*;

public class Nonsan2 {
    public static int[] prefix;
    public static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()),
                m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        prefix = new int[n + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            prefix[a - 1] += k;
            prefix[b] += -k;
        }

        int k = 0;
        for (int i = 0; i < n; i++) {
            k += prefix[i];
            sb.append(arr[i] + k + " ");
        }
        System.out.println(sb);
    }
}
/**
 * concept *
 * a ~ b 구간 k만큼 더하기.
 * 모든 구간 업데이트를 N으로 수행하기 위해 변화량의 변화량을 prefix에 저장.
 * 나중에 한번에 prefix를 돌면서 변화량의 변화량을 누적시켜가며 arr[i] 의 값에 더한 값을 print.
 *
 * ex)
 * 1 ~ 3 구간 2만큼 더하기는 prefix[0] = 2; prefix[3] = -2;
 * 2~4 구간 4만큼 빼기 prefix[1] = -4; prefix[4] = 4;
 * arr 가 [0, 1, 2, 3, 4] 라 했을 때, 변화량을 k라 하고 변화량의 변화량이 저장되있는 prefix의 값으로 k업데이트.
 * idx:0 -> k = 2 , arr[i] = 2;
 * idx:1 -> k = -2 , arr[i] = -1;
 * idx:2 -> k = -2 , arr[i] = 0;
 * idx:3 -> k = -4 , arr[i] = -1;
 * idx:4 -> k = 0 , arr[i] = 4;
 *
 * arr 에 변화량 더한 결과
 * [2, -1, 0, -1, 4]
 *
 * arr에 하나씩 더한 결과
 * 0, 1, 2, 3, 4
 * 2, 3, 4, 3, 4 :: 1~3 + 2
 * 2, -1, 0, -1, 4 :: 2~4 -4
 *
 */