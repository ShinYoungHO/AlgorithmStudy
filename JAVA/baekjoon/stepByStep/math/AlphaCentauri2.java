package stepByStep.math;

import java.io.*;
import java.util.HashMap;

public class AlphaCentauri2 {
    public static void main(String[] args) throws IOException{

        for(int i = 0; i <=20; i++){
            System.out.println(execute2(i)+":"+i);
        }
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCases = Integer.parseInt(bf.readLine());
        HashMap<Integer, Integer> memo = new HashMap<>();

        for(int i = 1; i <= testCases; i++){
            String[] test = bf.readLine().split(" ");
            int distance = Integer.parseInt(test[1]) - Integer.parseInt(test[0]);
            if(memo.get(distance) != null){
                bw.write(memo.get(distance)+"\n");
            } else {
                int v = execute(distance);
                memo.put(distance,v);
                bw.write(v+"\n");
            }
        }
        bf.close();
        bw.close();
    }

    // time out
    public static int execute(int v){
        int n = 1;
        int count = 0;
        int total = 0;
        while(true){
            if(total+2*n <= v){
                count+=2;
                total+=2*n;
                n++;
            } else if(total+n <= v){
                count++;
                total+=n;
            } else break;
        }
        if(total < v) return count+1;
        return count;
    }
    // time out
    public static int execute1(int v){
        int n = 0;
        while(true){
            if((n)*(n+1) < v){
                n++;
            } else break;
        }
        if(n*n < v){
            return 2*n;
        } else {
            return 2*n-1;
        }
    }

    public static int execute2(int v){
        int val = (int) Math.sqrt(v-1);
        if(val*(val+1) < v) return 2*val+1;
        return 2*val;
    }
}
/**
  y지점에 도착하기 바로 직전의 이동거리는 반드시 1광년으로 하려 한다.
0  0
1  1 1
2  2 11
3  3 111
3  4 121
4  5 1211
4  6 1221
5  7 12211
5  8
5  9 12321
6  10
6 11
6 12 123321
7 13
7 14
7 15
7 16 1234321
  20 12344321
  ((n - 1) % 2 + 1)*((n + 1) / 2)
 */