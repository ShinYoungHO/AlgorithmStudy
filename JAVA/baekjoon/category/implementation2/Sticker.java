package category.implementation2;
// 18808
import java.io.*;
import java.util.StringTokenizer;


public class Sticker {
    static int n,m,k;
    static int[][] mtx;

    static void solve(int[][] sticker, int r, int c, int rotated){
        if(rotated == 4) return;
        boolean valid = false;
        for(int i = 0; i <= n-r; i++){
            for(int j = 0; j <= m-c; j++){
                if(mtx[i][j] + sticker[0][0] == 2) continue;
                valid = check(sticker, r, c, i, j);
                if(valid){
                    stitch(sticker, r, c, i, j);
                    return;
                }
            }
        }
        if(!valid) solve(rotate(sticker, r,c), c, r, rotated+1);
    }

    static void stitch(int[][] sticker, int r, int c, int i, int j){
        for(int p = 0; p < r; p++){
            for(int q = 0; q < c; q++){
                if(sticker[p][q] == 1){
                    mtx[i+p][j+q] = 1;
                }
            }
        }
    }

    static boolean check(int[][] sticker, int r, int c, int i, int j){
        for(int p = 0; p < r; p++){
            for(int q = 0; q < c; q++){
                if(i+p >= n || j+q >= m) return false;
                if (mtx[i+p][j+q] + sticker[p][q] > 1) return false;
            }
        }
        return true;
    }

    static int[][] rotate(int[][] sticker, int r, int c){
        int[][] result = new int[c][r];
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                int v = sticker[i][j];
                result[j][r-1-i] = v;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        mtx = new int[n][m];

        int r,c,ans = 0;

        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine(), " ");
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            int[][] sticker = new int[r][c];

            for(int j = 0; j < r; j++){
                st = new StringTokenizer(br.readLine(), " ");
                for(int m = 0; m < c; m++){
                    sticker[j][m] = Integer.parseInt(st.nextToken());
                }
            }

            solve(sticker, r, c, 0);
        }


        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(mtx[i][j] == 1) ans++;
            }
        }
        System.out.println(ans);
    }
}
