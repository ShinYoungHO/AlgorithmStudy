package stepByStep.I_O;

import java.io.*;
import java.util.Scanner;

public class Add{
    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String n = br.readLine();
        String[] input = n.split(" ");

        int result = Integer.parseInt(input[0])+Integer.parseInt(input[1]);
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}
//
//public class Add{
//    public static void main(String args[]) throws IOException {
//
//        Scanner sc = new Scanner(System.in);
//        String a = sc.nextLine();
//        int b = sc.nextInt();
//        System.out.println(b);
//    }
//}
