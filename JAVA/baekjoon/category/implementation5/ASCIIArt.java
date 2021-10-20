package category.implementation5;

import java.io.*;
import java.util.StringTokenizer;

public class ASCIIArt {
    static int inR, inG, inB;
    static int[] rv = { 510_000, 1_020_000, 1_530_000, 2_040_000};
    static char[] rc = {'#', 'o', '+', '-'};
    static char parseASCII(int k){
        char v = '.';
        for(int i = 0; i < 4; i++){
            if(k < rv[i]) {
                v = rc[i];
                break;
            }
        }
        return v;
    }

    static int Intensity(int r, int g, int b){
        return inR*r + inG*g + inB*b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        int R, G, B, r, c;
        inR = 2126;
        inG = 7152;
        inB = 722;
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        for(int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < c; j++){
                R = Integer.parseInt(st.nextToken());
                G = Integer.parseInt(st.nextToken());
                B = Integer.parseInt(st.nextToken());
                sb.append(parseASCII(Intensity(R,G,B)));
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
