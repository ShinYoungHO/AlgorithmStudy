package category.implementation2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TwoDimArrCalc {
    static int r,c,k;
    static int maxR,maxC;
    static int res;
    static int[][] mtx;

    static boolean flip;
    static void solve(){
        Map<Integer, Node> map;
        ArrayList<Node> arr;
        int len = 0;
        int[][] to = new int[100][100];
        Node n;
        if(maxR >= maxC){ // 행 연산
            for(int i = 0; i < 100; i++){
                arr = new ArrayList<>();
                map = new HashMap<>();
                for(int j = 0; j < 100; j++){
                    int v = mtx[i][j];
                    if(v == 0) continue;
                    if(!map.containsKey(v)) {
                        n = new Node(v);
                        arr.add(n);
                        map.put(v, n);
                    } else map.get(v).c++;
                }

                sort(arr);

                len = Math.min(arr.size() * 2, 100);
                if(maxC < len) maxC = len;

                int idx = 0;

                for (Node node : arr) {
                    n = node;
                    to[i][idx++] = n.v;
                    to[i][idx++] = n.c;
                    if (idx >= 100) break;
                }
            }
        } else { // 열 연산
            for(int i = 0; i < 100; i++){
                arr = new ArrayList<>();
                map = new HashMap<>();
                for(int j = 0; j < 100; j++){
                    int v = mtx[j][i];
                    if(v == 0) continue;
                    if(!map.containsKey(v)) {
                        n = new Node(v);
                        arr.add(n);
                        map.put(v, n);
                    } else map.get(v).c++;
                }

                sort(arr);

                len = Math.min(arr.size() * 2, 100);
                if(maxR < len) maxR = len;

                int idx = 0;
                for (Node node : arr) {
                    n = node;
                    to[idx++][i] = n.v;
                    to[idx++][i] = n.c;
                    if (idx >= 100) break;
                }
            }
        }

        mtx = to;
    }

    private static void sort(ArrayList<Node> arr){
        Collections.sort(arr,(Node n1, Node n2) -> n1.c != n2.c ? n1.c-n2.c : n1.v-n2.v);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        maxR = 3;
        maxC = 3;
        res = 0;
        flip = false;
        mtx = new int[100][100];
        for(int i = 0; i < 3; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < 3; j++){
                mtx[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while(mtx[r-1][c-1] != k){
            if(res == 100){
                System.out.println(-1);
                return;
            }
            solve();
            res++;
        }

        System.out.println(res);
    }

    private static class Node{
        int v,c=1;
        private Node(int v){
            this.v = v;
        }
    }

}
