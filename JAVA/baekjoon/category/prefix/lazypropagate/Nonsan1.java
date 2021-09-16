package category.prefix.lazypropagate;
// 19951 timeout
// Lazy-propagation

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Nonsan1 {
    static int[] seg;
    static int[] lazy;
    static int H;
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int n = parseToken(st);
        int m = parseToken(st);
        count = n;
        H = 1;

        while(H < n) H*=2;

        seg = new int[2*H];
        lazy = new int[2*H];

        st = new StringTokenizer(br.readLine(), " ");

        for(int i = 1; i <= n; i++) init(i,1,  parseToken(st), 1, H);

        for(int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int queryX = parseToken(st);
            int queryY = parseToken(st);
            int v = parseToken(st);

            updateQuery(1, queryX, queryY, v, 1, H);
        }


        getValue(1, 1, H, sb);

        System.out.println(sb);
        br.close();
    }

    static void getValue(int node, int left, int right, StringBuilder sb){
        lazyUpdate(node, left, right);
        if(left == right){
            if(count > 0){
                sb.append(seg[node]);
                sb.append(" ");
                count--;
            }
            return;
        }
        int mid = (left+right) >> 1;
        getValue(node*2, left, mid, sb);
        getValue(node*2+1, mid+1, right, sb);
    }

    static void lazyUpdate(int node, int l, int r){
        if(lazy[node] != 0){
            seg[node] += lazy[node] * (r - l + 1);
            if(l != r){
                lazy[node*2] += lazy[node];
                lazy[node*2+1] += lazy[node];
            }
            lazy[node] = 0;
        }
    }

    static int updateQuery(int node,int qX, int qY, int v, int left, int right){
        lazyUpdate(node, left, right);

        if(right < qX || qY < left ) return seg[node];
        if(qX <= left && right <= qY){
            if(left != right){
                lazy[node*2]+=v;
                lazy[node*2+1]+=v;
            }
            return seg[node] += v * (right - left + 1);
        }
        int mid = (left+right) >> 1;

        return seg[node] = updateQuery(node*2, qX, qY, v, left, mid) + updateQuery(node*2+1, qX, qY, v, mid+1, right);
    }

    // initialize segment tree
    static void init(int idx, int node, int v, int l, int r){
        seg[node] +=v;
        int mid = (l+r) >> 1;
        if(l == r) return;
        if(idx <= mid) init(idx, node*2, v, l ,mid);
        else init(idx, node*2+1, v, mid+1, r);
    }


    static int parseToken(StringTokenizer st){
        return Integer.parseInt(st.nextToken());
    }
}
