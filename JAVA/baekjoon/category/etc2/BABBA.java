package category.etc2;

import java.io.*;

public class BABBA {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int v = Integer.parseInt(br.readLine());
        int[] k = {1, 0};
        int a,b;
        while(v > 0){
            a = k[1];
            b = k[0]+k[1];
            k[0] = a;
            k[1] = b;
            v--;
        }
        sb.append(k[0]).append(" ").append(k[1]);
        System.out.println(sb);
    }
}
