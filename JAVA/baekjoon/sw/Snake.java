package sw;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Snake {
    static Set<Position> applePos;
    static Set<Position> snakePos;
    static LinkedList<Position> snakeOrder;
    static Queue<String[]> commands;
    static int size;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        size = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        applePos = new HashSet<>();
        snakePos = new HashSet<>();
        snakeOrder = new LinkedList<>();
        commands = new LinkedList<>();

        for(int i = 0; i < k; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            applePos.add(new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int l = Integer.parseInt(br.readLine());
        for(int i = 0; i < l; i++){
            commands.add(br.readLine().split(" "));
        }
        Position init = new Position(1, 1);
        snakePos.add(init);
        snakeOrder.add(init);
        int v = solve();
        System.out.println(v);
    }

    static int solve(){
        int dir = 1;
        int time = 0;
        while(true){
            Position Head = snakeOrder.peekFirst();
            Position nextPos = getNext(Head, dir);
            if(!nextPos.isInMap(size)) return time+1;
            if(applePos.contains(nextPos)){
                applePos.remove(nextPos);
                snakeOrder.addFirst(nextPos);
                snakePos.add(nextPos);

            } else {
                if(snakeOrder.contains(nextPos)) return time+1;
                Position tail = snakeOrder.pollLast();
                snakePos.remove(tail);
                snakeOrder.addFirst(nextPos);
                snakePos.add(nextPos);
            }
            time++;
            if(!commands.isEmpty()){
                String[] qv = commands.peek();
                if(time == Integer.parseInt(qv[0])){
                    commands.poll();
                    if(qv[1].equals("L")){
                        dir = dir == 0 ? 3 : dir-1;
                    } else if(qv[1].equals("D")){
                        dir = dir == 3 ? 0 : dir+1;
                    }
                }
            }
        }
    }

    static Position getNext(Position prev, int dir){
        int nx;
        int ny;
        switch (dir){
            case 0:
                nx = prev.x-1;
                ny = prev.y;
                break;
            case 1:
                nx = prev.x;
                ny = prev.y+1;
                break;
            case 2:
                nx = prev.x+1;
                ny = prev.y;
                break;
            default:
                nx = prev.x;
                ny = prev.y-1;
                break;
        }
        return new Position(nx, ny);

    }
}

class Position{
    int x;
    int y;
    int code;
    public Position(int x, int y){
        this.x = x;
        this.y = y;
        String a = x+"9"+y;
        this.code = Integer.parseInt(a);
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Position){
            Position v = (Position) o;
            if(this.x == v.x && this.y == v.y){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode(){
        return code;
    }
    boolean isInMap(int n){
        if(x > n || y > n || x <= 0 || y <= 0) return false;
        return true;
    }
}
