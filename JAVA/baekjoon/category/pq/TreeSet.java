import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class TreeSet {
    static TreeMap<Integer,Integer> tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            int k = Integer.parseInt(br.readLine());
            tree = new TreeMap<>();
            StringTokenizer st;
            for(int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                execute(st.nextToken(), Integer.parseInt(st.nextToken()));
            }
            if(tree.isEmpty()) sb.append("EMPTY");
            else sb.append(tree.lastKey()+" "+ tree.firstKey());
            sb.append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    public static void execute(String command, Integer v){
        if (command.equals("I")) {
            tree.put(v, tree.getOrDefault(v, 0) + 1);
        } else if (command.equals("D")) {
            if(tree.isEmpty()) return;
            int key = v == 1 ? tree.lastKey() : tree.firstKey();
            if (tree.put(key, tree.get(key) - 1) == 1){
                tree.remove(key);
            }
        }
    }
}