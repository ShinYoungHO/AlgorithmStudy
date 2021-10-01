package stepByStep.math;

import java.util.Scanner;

public class zeroSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        long two = solve(n,2)-solve(m, 2)-solve(n-m,2);
        long five = solve(n,5)-solve(m, 5)-solve(n-m,5);

        if(m == 0|| two <= 0 || five <= 0) System.out.println(0);
        else System.out.println(Math.min(two, five));
    }

    static long solve(long right, long digit){
        long res = 0;
        for(long i = 1; i <= right; i*=digit) res += right/i;
        return res;
    }
}
/*
1~1 0 * 1
2~3 1 * 2
4~7 2 *
8~15 3
16~31 4

 */
