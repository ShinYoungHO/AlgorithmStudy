package category.prefix.lazypropagate;

import java.util.*;
import java.io.*;

public class PrefixSum2{

    static int N,M,K;
    static long [] seg;
    static long [] lazy;

    static int H;

    public static void main(String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseToken(st);
        M = parseToken(st);
        K = parseToken(st);
        H = 1;

        while(H<N)H*=2;
        seg = new long[H*2];
        lazy = new long [H*2];


        for(int i =1;i<=N;i++) {
            save(i, Long.parseLong(br.readLine()), 1, 1, H);
        }

        for(int i =1;i<=M+K;i++){
            st = new StringTokenizer(br.readLine());
            int order = parseToken(st);

            if(order == 1){
                int s = parseToken(st);
                int e = parseToken(st);
                long val = Long.parseLong(st.nextToken());

                rangeQuery(1, val, 1, H, s, e);
            }else{
                int s = parseToken(st);
                int e = parseToken(st);

                sb.append(sum(1, 1, H, s, e)+"\n");
            }
        }
        System.out.println(sb);
    }
    static void save(int index, long val, int node, int left, int right){
        seg[node] += val;
        if(left == right) return;

        int mid = (left+right)/2;

        if(index <= mid) save(index, val, node*2, left, mid);
        else save(index, val, node*2+1, mid+1, right);
    }

    static void lazyUpdate(int node, int start, int end){
        if(lazy[node] == 0) return;
        // lazy 값이 있으면 segment배열 (자식들 갯수 * lazy 값 )만큼 업데이트
        seg[node] += lazy[node] * (end-start+1);

        //부모는 업데이트 됐지만 자식들은 업데이트 되지 않았으므로, lazy값 자식들에게 전파. 단, 자식들이 있는 경우에만.
        if(start != end){
            lazy[node *2] += lazy[node];
            lazy[node*2+1] += lazy[node];
        }
        lazy[node] = 0;
    }
    static long rangeQuery(int node, long val, int start, int end, int left, int right){
        lazyUpdate(node, start, end);

        // 최상단 1~H 범위에서 쿼리 범위를 만족하지 않는 노드의 값도 더해서 할당해줘야 하기 때문에 seg[node] 값 리턴.
        if(start > right || end < left) return seg[node];
        if(left <= start && end <= right){
            // 자식들이 있는 경우 업데이트할 값을 자식들의 lazy 값에 추가하고,
            if(start != end){
                lazy[node*2] += val;
                lazy[node*2+1] += val;
            }
            // 현재 업데이트할 구간에 자식수만큼 곱합 값을 더해서 업데이트 된 구간합을 리턴.
            return seg[node] += (end-start+1)*val;
        }
        int mid = (start+end)/2;
        // 모든 범위에 대해 구한 값을 리턴.
        return seg[node] = rangeQuery(node*2, val, start, mid, left, right) + rangeQuery(node*2+1, val, mid+1, end, left, right);
    }
    static long sum(int node, int start, int end, int left, int right){

        lazyUpdate(node, start, end);
        if(start > right || end < left) return 0;
        // 범위 start ~ end 가 포함되는 영역의 값 업데이트 된 후 리턴
        if(left <= start && end <= right){
            return seg[node];
        }
        int mid = (start+end)/2;
        // start ~ end 가 포함되는 모든 영역의 최상단 세그먼트 트리 값 lazy업데이트 후 더한 값을 리턴.
        return sum(node*2, start, mid, left, right) + sum(node*2+1, mid+1, end, left, right);
    }


    static int parseToken(StringTokenizer st){
        return Integer.parseInt(st.nextToken());
    }
}


/**
 * 3-8
 * 3-5  6-8
 * 3-4 5    6-7 8
 */
