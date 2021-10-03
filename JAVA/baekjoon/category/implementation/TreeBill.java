package category.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TreeBill {
    static int[][] adder;
    static Division[][] mtx;
    static int n,m,k;
    static int[] dx = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = new int[]{0, 1, 1, 1, 0, -1, -1, -1};

    static int solve(){
        Division d;
        PriorityQueue<Integer> pq;
        Stack<Integer> tmp = new Stack<>();
        int v, candi;
        int nx, ny;

        int result = 0;


        for(int year = 0; year < k; year++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    d = mtx[i][j];
                    if(d.isEmpty()) continue;
                    pq = d.trees;

                    while(!pq.isEmpty() && (v = pq.peek()) <= d.food){
                        d.food-=v;
                        tmp.push(pq.poll()+1);

                    }

                    while(!d.isEmpty()) d.food+=pq.poll()/2;
                    while(!tmp.isEmpty()) pq.add(tmp.pop());
                }
            }

            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    d = mtx[i][j];
                    if(d.isEmpty()) continue;
                    Iterator it = d.getIterator();
                    while(it.hasNext()){
                        candi = (int) it.next();
                        if(candi % 5 == 0){
                            for(int k = 0; k < 8; k++){
                                nx = i+dx[k];
                                ny = j+dy[k];
                                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                                mtx[nx][ny].trees.add(1);
                            }
                        }
                    }
                }
            }

            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    mtx[i][j].food += adder[i][j];
                }
            }
        }


        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                result+=mtx[i][j].trees.size();
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int x,y,z;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        adder = new int[n][n];
        mtx = new Division[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++){
                adder[i][j] = Integer.parseInt(st.nextToken());
                mtx[i][j] = new Division();
            }
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine()," ");
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());
            mtx[x-1][y-1].trees.add(z);
        }

        System.out.println(solve());
    }

    private static class Division{
        PriorityQueue<Integer> trees = new PriorityQueue<>();
        int food = 5;
        boolean isEmpty(){
            return trees.isEmpty();
        }
        Iterator getIterator(){
            return trees.iterator();
        }
    }
}
