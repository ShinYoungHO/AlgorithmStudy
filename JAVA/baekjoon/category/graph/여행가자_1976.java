package category.ns.solved;

import java.util.*;

// 플로이드 와샬
public class 여행가자_1976 {
    static int INF = 3_000_000,n,m,c,nx,mtx[][];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ans = "YES";
        n = sc.nextInt();
        m = sc.nextInt();
        mtx = new int[n][n];

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                mtx[i][j] = (sc.nextInt()==0)&&(i!=j) ? INF : 1;

        for(int k = 0; k < n; k++)
            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++)
                    mtx[i][j] = Math.min(mtx[i][j], mtx[i][k]+mtx[k][j]);

        c = sc.nextInt()-1;
        for(int i = 1; i < m; i++){
            nx = sc.nextInt()-1;
            if(mtx[c][c = nx] == INF){
                ans = "NO";
                break;
            }
        }
        System.out.println(ans);
    }
}
