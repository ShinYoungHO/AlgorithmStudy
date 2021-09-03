import java.util.TreeMap;
import java.util.StringTokenizer;

class Solution {
    public TreeMap<Integer, Integer> tree;
    public int[] solution(String[] operations) {
        int[] answer;
        tree = new TreeMap<>();
        StringTokenizer st;
        for(int i = 0; i < operations.length; i++) {
            st = new StringTokenizer(operations[i]);
            execute(st.nextToken(), Integer.parseInt(st.nextToken()));
        }
        if(tree.isEmpty()) answer = new int[]{0, 0};
        else {
            answer = new int[]{tree.lastKey(), tree.firstKey()};
        }
        return answer;
    }
    
    public void execute(String command, Integer v){
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