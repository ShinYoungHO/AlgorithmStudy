package category.pq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Team {
    static PriorityQueue<int[]> pq;
    static long solve(int n, int k){
        int max=0, atk=0, def=0;
        long v = 0;

        if(n%2==0 || k%2==0){
            max+=n/2;
            max+=k/2;
        } else {
            max = (n-k)/2+k;
        }
        int[] ad;
        while(!pq.isEmpty()){
            ad = pq.poll();
            if(ad[0] > ad[1]) {
                if(atk==max){
                    v += ad[1];
                    def++;
                } else {
                    v += ad[0];
                    atk++;
                }

            } else if(ad[0] < ad[1]){
                if(def==max){
                    v += ad[0];
                    atk++;
                } else {
                    v += ad[1];
                    def++;
                }
            } else {
                v += ad[0];
            }
        }


        return v;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st1,st2;

        int t = Integer.parseInt(br.readLine());
        int n,k;
        for(int i = 0; i < t; i++){
            st1 = new StringTokenizer(br.readLine()," ");
            n = Integer.parseInt(st1.nextToken());
            k = Integer.parseInt(st1.nextToken());
            st1 = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++){
            }
            st2 = new StringTokenizer(br.readLine(), " ");
            pq = new PriorityQueue<>((v1, v2) -> v2[2] - v1[2]);
            for(int j = 0; j < n; j++){
                int[] qv =  new int[3];
                qv[0] = Integer.parseInt(st1.nextToken());
                qv[1] = Integer.parseInt(st2.nextToken());
                qv[2] = Math.abs(qv[0]-qv[1]);
                pq.add(qv);
            }

            sb.append(solve(n, k)).append("\n");
        }
        System.out.println(sb);
    }
}
/**
 * 선수 한명의 공,수 중 큰 값을 넣으면, 해당 공/수 값이 감소하며, 다음 공/수 선택에 영향.
 * 절댓값 차이를 최대로 하는 순서로 공/수 중 한군데에 넣어 기댓값의 감소를 최소화
 */
