package stepByStep.dnc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Histogram {
    static int[] target;
    static int[] tree;
    static int N;
    static int OB = 1_000_000_001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while(true){
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            if(N == 0) break;

            target = new int[N+1];
            tree = new int[3 * N + 2];

            for(int i = 1; i <= N; i++) target[i] = Integer.parseInt(st.nextToken());
            setTree(1, 1, N);
            sb.append(getMax(1, N)+"\n");
        }
        System.out.println(sb);
    }

    // 세그먼트 트리 생성.
    public static int setTree(int idx, int left, int right){
        if(left == right) return tree[idx] = left;

        int mid = (left+right)/2;
        int leftMin = setTree(2*idx, left, mid);
        int rightMin = setTree(2*idx+1, mid+1, right);

        return tree[idx] = target[leftMin] > target[rightMin] ? rightMin : leftMin;
    }

    public static int getMinIdx(int start, int end, int idx, int left, int right){
        if(right < start || left > end) return OB;
        if(left <= start && end <= right) return tree[idx];

        int mid = (start + end)/2;
        int leftMinIdx = getMinIdx(start, mid, idx*2, left, right);
        int rightMinIdx = getMinIdx(mid+1, end, idx*2+1, left, right);

        return leftMinIdx == OB ? rightMinIdx
                : rightMinIdx == OB ? leftMinIdx
                : target[leftMinIdx] > target[rightMinIdx] ? rightMinIdx : leftMinIdx;
    }

    public static long getMax(int left, int right){
        // left ~ right 범위의 최대 너비

        // 1. left~right * 최소높이 너비
        int minIdx = getMinIdx(1, N, 1, left, right);

        long max = (long) target[minIdx] * (long) (right - left + 1);

        // 2. 최소높이 왼쪽에서의 최대너비
        if(left < minIdx){
            long tmp = getMax(left, minIdx-1);
            if(tmp > max) max = tmp;
        }
        // 3. 최소높이 오른쪽에서의 최대너비
        if(minIdx  < right){
            long tmp = getMax(minIdx + 1, right);
            if(tmp > max) max = tmp;
        }

        return max;
    }
}