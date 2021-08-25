package stepByStep.dnc;

import java.util.Scanner;

public class Multiply {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long A = Long.parseLong(sc.next());
        long B = Long.parseLong(sc.next());
        long C = Long.parseLong(sc.next());

        System.out.println(recur(A, B, C) % C);;
    }
    public static long recur(long A, long n, long C){
        if(n == 1) return A % C;
        if(n == 0) return 1;
        long res = (recur(A, n/2, C) % C);
        if(n % 2 == 0) {
            return (res * res) % C;
        } else {
            return ((res * res) % C * (A % C))%C;
        }
    }
}
