package programmers.kakao;
// fail 96.7 %
import java.util.*;


public class Cards {
    ArrayList<Card> cards;
    Map<Integer, Integer> pairMap;
    boolean[] visited;
    int[] order;
    int r,c;
    int[] dx = new int[]{0, 1, 0, -1};
    int[] dy = new int[]{1, 0, -1, 0};
    int[][] board;
    int answer = Integer.MAX_VALUE;

    public int solution(int[][] board, int r, int c) {
        cards = new ArrayList<>();
        pairMap = new HashMap<>();
        this.r = r;
        this.c = c;
        this.board = board;
        int cnt = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(board[i][j] != 0) {
                    cards.add(new Card(i,j,board[i][j]));
                    cnt++;
                }
            }
        }

        for(int i = 0; i < cards.size(); i++){
            Card c1 = cards.get(i);
            for(int j = 0; j < cards.size(); j++){
                Card c2 = cards.get(j);
                if(c1.v == c2.v && i != j){
                    pairMap.put(i, j);
                }
            }
        }

        visited = new boolean[cnt];
        order = new int[cnt];
        ret(cnt, 0, 0);
        return answer;
    }


    void ret(int cnt, int start, int idx){
        if(cnt == 0){
            solve();
            return;
        }
        for(int i = 0; i < cards.size(); i++){
            if(!visited[i]){
                visited[i] = true;
                int pairIdx = pairMap.get(i);
                visited[pairIdx] = true;
                order[idx] = i;
                order[idx+1] = pairIdx;
                ret(cnt-2, i, idx+2);
                visited[pairIdx] = false;
                visited[i] = false;
            }
        }
    }

    void solve(){
        int[][] tBoard = new int[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                tBoard[i][j] = board[i][j];
            }
        }

        int res = 0;
        Card from,to;
        int x = r, y = c;

        for(int i = 0; i < order.length; i+=2){
            from = cards.get(order[i]);
            to = cards.get(order[i+1]);
            res += BFS(x, y, from.x, from.y, tBoard) + BFS(from.x, from.y, to.x, to.y, tBoard)+2;
            tBoard[from.x][from.y] = 0;
            tBoard[to.x][to.y] = 0;
            x = to.x;
            y = to.y;

        }
        if(res == 27){
            for(int i = 0; i < order.length; i++){
                Card c = cards.get(order[i]);
                System.out.print(c.x+":"+c.y + "->");
            }
            System.out.println(res);
        }

        if(res < answer) answer = res;
    }

    int BFS(int x1, int y1, int x2, int y2, int[][] board){
        if(x1 == x2 && y1 == y2) return 0;
        int[] qv;
        int x,y,d,nx,ny;
        boolean[][] visited = new boolean[4][4];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x1, y1, 0});
        visited[x1][y1] = true;

        while(!q.isEmpty()){
            qv = q.poll();
            x = qv[0];
            y = qv[1];
            d = qv[2];

            // ctrl + ->
            for(int i = 0; i < 4; i++){
                nx = x;
                ny = y;
                boolean tmp = false;
                while(true){
                    nx+=dx[i];
                    ny+=dy[i];
                    if(nx < 0 || ny < 0 || nx >= 4 || ny >= 4) break;
                    // 카드 만난 경우
                    if(board[nx][ny] > 0){
                        if(nx == x2 && ny == y2) return d+1;
                        if(visited[nx][ny]) break;
                        q.add(new int[]{nx, ny, d+1});
                        visited[nx][ny] = true;
                        tmp = true;
                        break;
                    }
                }
                if(!tmp){
                    // 카드 만나지 않고 끝까지 간 경우
                    nx -= dx[i];
                    ny -= dy[i];
                    q.add(new int[]{nx, ny, d+1});
                    visited[nx][ny] = true;
                }

            }

            for(int i = 0; i < 4; i++){
                nx = x + dx[i];
                ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= 4 || ny >= 4) continue;
                if(visited[nx][ny]) continue;
                if(nx == x2 && ny == y2) return d+1;
                q.add(new int[]{nx, ny, d+1});
                visited[nx][ny] = true;
            }
        }
        return 0;
    }
}

class Card{
    int x,y,v;
    public Card(int x, int y, int v){
        this.x = x;
        this.y = y;
        this.v = v;
    }
}

