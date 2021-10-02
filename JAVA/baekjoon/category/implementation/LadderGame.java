package category.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LadderGame {
    static boolean[][] link;
    static int[] position;
    static int n,m,h;
    static int max;
    static int solved;
    static int result;
    static void solve(int p){
//        System.out.println(p);
        if(p == h){
            boolean test = isValid();
            if(test) if(result > solved) result = solved;
//            System.out.println("====");
            return;
        }
        for(int i = 0; i <= max; i++){
//            System.out.println(i);
            ret(i, 0, p, 0);
        }
    }

    static void ret(int targetSwitch, int switched, int idx, int start){
//        System.out.println(targetSwitch+":"+idx+":"+h);
        if(targetSwitch == switched && idx < h){
//            System.out.println(1113123213);
            for(int i = 0; i < n-1; i++) {
                if (link[idx][i]) swap(i);
//                System.out.println(link[idx][i]);
            }
            solve(idx + 1);
            for(int i = 0; i < n-1; i++) if(link[idx][i]) swap(i);
            return;
        }
        for(int i = start; i < n-1; i++){
            if(!link[idx][i] &&( n-1 == n-1 || !link[idx][i+1])){
                link[idx][i] = true;
                solved++;
                ret(targetSwitch, switched+1, idx, i);
                link[idx][i] = false;
                solved--;
            }
        }
    }

    static boolean isValid(){
        boolean test = true;
        for(int i = 0; i < n; i++){
            if(position[i] != i) {
                test = false;
                break;
            }
        }
        return test;
    }

    static void swap(int i){
        int tmp = position[i];
        position[i] = position[i+1];
        position[i+1] = tmp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken()); // 세로선
        m = Integer.parseInt(st.nextToken()); // 연결선
        h = Integer.parseInt(st.nextToken()); // 가로선

        link = new boolean[h][n-1];
        position = new int[n];
        max = (int)Math.ceil((double)(n-1)/(double)2);
        solved = 0;
        result = Integer.MAX_VALUE;

        for(int i = 0; i < position.length; i++) position[i] = i;

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            link[r-1][c-1] = true;
        }

        solve(0);
        System.out.println(result);
    }
}
