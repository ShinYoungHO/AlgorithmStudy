package stepByStep.bruteforce;

import java.util.Scanner;

public class BulkOrder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[][] students = new int[n][2];
        for(int i = 0; i < n; i++){
            String[] v = sc.nextLine().split(" ");
            students[i] = new int[]{Integer.parseInt(v[0]), Integer.parseInt(v[1])};
        }
        String[] result = new String[n];

        for(int i = 0; i < n; i++){
            int targetKg = students[i][0];
            int targetHeight = students[i][1];
            int count = 1;
            for(int j = 0; j < n; j++){
                if(i == j) continue;
                int kg = students[j][0];
                int height = students[j][1];

                if(targetHeight < height && targetKg < kg) count++;
            }
            result[i] = String.valueOf(count);
        }
        System.out.println(String.join(" ",result));
        sc.close();
    }
}
