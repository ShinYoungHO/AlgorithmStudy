package category.prefix;
// 19951 timeout -- naive
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Nonsan {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int n = parseToken(st);
        int m = parseToken(st);

        int[] res = new int[n+1];
        int[][] delta = new int[n+1][101];
        st = new StringTokenizer(br.readLine(), " ");

        for(int i = 1; i <= n; i++) res[i] = parseToken(st);



        for(int i = 1; i <= m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int from = parseToken(st);
            int to = parseToken(st);
            int v = parseToken(st);

            for(int j = from; j <= to; j++){
                if(v >= 0) delta[j][v]+=1;
                else delta[j][-v]-=1;
            }
        }

        for(int i = 1; i <= n; i++){
            for(int j = 0; j <= 100; j++){
                res[i] += j*delta[i][j];
            }
            sb.append(res[i]);
            sb.append(" ");
        }

        System.out.println(sb);
        br.close();
    }

    static int parseToken(StringTokenizer st){
        return Integer.parseInt(st.nextToken());
    }
}
