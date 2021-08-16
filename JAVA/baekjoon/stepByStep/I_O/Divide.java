package stepByStep.I_O;

import java.io.*;

public class Divide {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int a = bf.read();
        System.out.println(a);
//        String[] input = bf.readLine().split(" ");
//        Double result = Double.parseDouble(input[0]) / Double.parseDouble(input[1]);
//        bw.write(String.valueOf(result));
        bw.close();
        bf.close();
    }
}
