package category.implementation6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BeltRobot {
    static int n,k;
    static int cnt;
    static void solve(Belt[] belt){
        int res = 1;
        while(cnt < k){
            moveBelt(belt);
            for(int i = n-1; i > 0; i--){
                if(belt[i-1].robot && belt[i].v > 0 && !belt[i].robot){
                    belt[i].v--;
                    if(belt[i].v == 0) cnt++;
                    belt[i-1].robot = false;
                    if(i == n-1) continue;
                    belt[i].robot = true;
                }
            }
            if(belt[0].v > 0 && !belt[0].robot) {
                belt[0].robot = true;
                belt[0].v--;
                if(belt[0].v == 0) cnt++;
            }
            if(cnt >= k) break;
            res++;
        }

        System.out.println(res);
    }
    static void moveBelt(Belt[] belt){
        Belt tmp = belt[2*n-1];
        for(int i = 2*n-1; i > 0; i--){
            belt[i] = belt[i-1];
        }
        belt[0] = tmp;
        if(belt[n-1].robot) belt[n-1].robot = false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        cnt = 0;

        Belt[] belt = new Belt[2*n];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < belt.length; i++){
            belt[i] = new Belt(Integer.parseInt(st.nextToken()));
            if(belt[i].v == 0) cnt++;
        }
        solve(belt);
    }
    static class Belt{
        int v;
        boolean robot = false;
        public Belt(int v) {
            this.v = v;
        }
    }
}
