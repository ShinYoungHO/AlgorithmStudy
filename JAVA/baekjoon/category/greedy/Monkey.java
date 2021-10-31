package category.greedy;
// 2516
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// ns timeout & OOM - backtracking
public class Monkey {
    static Set<Integer> left;
    static Set<Integer> right;
    static ArrayList<Integer>[] enemy;

    static boolean solve(int st, int end){
        if(st == end) return true;
        if(search(st, end, left)) return true;
        if(search(st, end, right)) return true;
        return false;
    }

    static boolean search(int st, int end, Set<Integer> set){
        set.add(st);
        if (check(set) && solve(st+1, end)) return true;
        set.remove(st);
        return false;
    }

    static boolean check(Set<Integer> set){
        Iterator<Integer> it = set.iterator();

        while (it.hasNext()){
            int v = it.next();
            int cnt = 0;
            for(int i = 0; i < enemy[v].size(); i++){
                int k = enemy[v].get(i);
                if(set.contains(k)) {
                    cnt++;
                    if(cnt >= 2) break;
                }
            }
            if(cnt >= 2) return false;
        }
        return true;
    }

    static void sol(Set<Integer> set, StringBuilder sb){
        sb.append(set.size()).append(" ");
        set.forEach((t) -> sb.append(t).append(" "));
        sb.append("\n");
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        enemy = new ArrayList[n+1];
        left = new HashSet<>();
        right = new HashSet<>();

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int m = Integer.parseInt(st.nextToken());
            enemy[i] = new ArrayList<>();
            for(int j = 0; j < m; j++){
                int k = Integer.parseInt(st.nextToken());
                enemy[i].add(k);
            }
        }

        solve(1, n+1);
        StringBuilder sb = new StringBuilder();
        sol(left, sb);
        sol(right, sb);
        System.out.println(sb);
    }
}

// solved
class Monkey1 {
    static ArrayList<Integer>[] enemy;
    static boolean[] pos;

    static boolean solve(int n){
        boolean ret = false;
        for(int i=1; i<=n; i++){
            if(pos[i]) continue;
            int cnt = 0;
            for(int j=0; j<enemy[i].size(); j++) {
                cnt += pos[enemy[i].get(j)] ? 0 : 1;
            }
            if(cnt > 1) {
                pos[i] = true;
                ret = true;
            }
        }
        for(int i=1; i<=n; i++){
            if(!pos[i]) continue;
            int cnt = 0;
            for(int j=0; j<enemy[i].size(); j++) {
                cnt += pos[enemy[i].get(j)] ? 1 : 0;
            }
            if(cnt > 1) {
                pos[i] = false;
                ret = true;
            }
        }
        return ret;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        enemy = new ArrayList[n+1];
        pos = new boolean[n+1];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int m = Integer.parseInt(st.nextToken());
            enemy[i] = new ArrayList<>();
            for(int j = 0; j < m; j++){
                int k = Integer.parseInt(st.nextToken());
                enemy[i].add(k);
            }
        }

        while(true){
            if(!solve(n)) break;
        }


        int lc = 0, rc = 0;
        StringBuilder lsb = new StringBuilder();
        StringBuilder rsb = new StringBuilder();
        for(int i = 1; i <= n; i++){
            if(!pos[i]) {
                lsb.append(i).append(" ");
                lc++;
            }
            else {
                rc++;
                rsb.append(i).append(" ");
            }
        }
        System.out.println(lc+" "+lsb.append("\n").append(rc).append(" ").append(rsb));
    }
}
