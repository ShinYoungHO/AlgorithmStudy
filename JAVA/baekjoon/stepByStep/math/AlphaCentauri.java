package stepByStep.math;

import java.util.*;

public class AlphaCentauri {
    public static void main(String[] args) {
        // input
        Scanner sc = new Scanner(System.in);
        int testCases = Integer.parseInt(sc.nextLine());

        for(int i = 1; i <= testCases; i++){
            String[] test = sc.nextLine().split(" ");
            BFS(Integer.parseInt(test[0]), Integer.parseInt(test[1]));
        }

        sc.close();
    }
    // time out
    public static void BFS(int x, int y){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, 0, 0});
        while(queue.size() > 0){
            int[] values = queue.poll();
            int curPos = values[0];
            int count = values[1];
            int prevMove = values[2];

            if(prevMove == 0){
                queue.add(new int[]{curPos+1, count+1, 1});
                continue;
            }
            if(prevMove == 1 && curPos == y){
                System.out.println(count);
                return;
            }
            for(int i = prevMove-1; i <= prevMove+1; i++){
                queue.add(new int[]{curPos+i, count+1, i});
            }
        }
    }
}


// y지점에 도착하기 바로 직전의 이동거리는 반드시 1광년으로 하려 한다.
// 0
// 1 1
// 2 11
// 4 121
// 6 1221
// 9 12321
// 12 123321
// 16 1234321
// 20 12344321
// ((n - 1) % 2 + 1)*((n + 1) / 2)