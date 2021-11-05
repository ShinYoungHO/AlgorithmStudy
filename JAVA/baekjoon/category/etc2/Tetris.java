package category.etc2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tetris {
    static int[][] Q={{0},{0,0,0,0}},
            W={{0,0}},
            E={{0,0,1},{1,0}},
            R={{1,0,0},{0,1}},
            T={{0,0,0},{0,1},{1,0,1},{1,0}},
            Y={{0,0,0},{0,0},{0,1,1},{2,0}},
            U={{0,0,0},{0,2},{1,1,0},{0,0}};
    static int[][][] blocks = {Q,W,E,R,T,Y,U};

    static int solve(int[] m, int n, int l){
        int ans = 0;
        for(int i = 0; i < blocks[n].length; i++){
            int[] block = blocks[n][i];
            for(int j = 0; j < l-block.length+1; j++){
                int v = block[0]-m[j];
                boolean t = true;
                for(int k = 1; k < block.length; k++){
                    if(v != block[k]-m[j+k]){
                        t = false;
                        break;
                    }
                }
                if(t) ans++;
            }
        }
        return ans;
    }

    static int[][] rot(int[][] b){
        int[][] res = new int[b[0].length][b.length];
        for(int i = 0; i < b.length; i++){
            for(int j = 0; j < b[0].length; j++){
                res[j][res.length-1-i] = b[i][j];
            }
        }
        return res;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int l,n;
        l = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken())-1;
        int[] m = new int[l];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < l; i++){
            m[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve(m, n, l));
    }
}
