package stepByStep.math;

import java.io.*;

public class HugeInt {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] digits = bf.readLine().split(" ");
        String[] digitArr1 = digits[0].split("");
        String[] digitArr2 = digits[1].split("");
        String[] result;

        if(digitArr1.length >= digitArr2.length){
            result = execute(digitArr1, digitArr2);
        } else {
            result = execute(digitArr2, digitArr1);
        }

        if(result[0].equals("0")){
            for(int i = 1; i < result.length; i++){
                bw.write(result[i]);
            }
        } else {
            for(int i = 0; i < result.length; i++){
                bw.write(result[i]);
            }
        }

        bw.flush();
        bw.close();
        bf.close();
    }

    public static String[] execute (String[] arr1, String[] arr2){
        // arr1 > arr2
        int len1 = arr1.length;
        int len2 = arr2.length;
        String[] result = new String[len1+1];

        boolean largerThan10 = false;

        for(int i = 0; i <= len1; i++){
            int v1 = 0;
            int v2 = 0;

            if(i < arr2.length) v2 = Integer.parseInt(arr2[len2-i-1]);
            if(i < arr1.length) v1 = Integer.parseInt(arr1[len1-i-1]);

            int add = 0;
            if(largerThan10) add = v1+v2+1;
            else add = v1+v2;

            if(add >= 10) largerThan10 = true;
            else largerThan10 = false;

            result[len1-i] = String.valueOf(add%10);
        }
        return result;
    }
}
