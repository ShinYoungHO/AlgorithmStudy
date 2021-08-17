package stepByStep.math;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Deci {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(System.out));
        int from = sc.nextInt();
        int to = sc.nextInt();

        boolean[] chkArr = new boolean[to+1];
        chkArr[0] = true;
        chkArr[1] = true;

        for(int i = 2; i < to; i++){
            if(chkArr[i]) continue;

            boolean notDeci = true;
            if(i == 2 || i == 3){
                notDeci = false;
            } else {
                notDeci = checkDeci(i);
            }

            if(!notDeci){
                for(int j = 2 * i; j <= to; j += i ){
                    chkArr[j] = true;
                }
            }

        }
        for(int i = from; i <= to; i++){
            if(!chkArr[i]){
                bf.write(String.valueOf(i)+"\n");
            }
        }


        bf.flush();
        bf.close();
        sc.close();
    }

    public static boolean checkDeci(int v){
        boolean result = false;
        for(int i = 2; i <= Math.sqrt(v); i++){
            if(v % i == 0){
                result = true;
                break;
            }
        }
        return result;
    }
}
