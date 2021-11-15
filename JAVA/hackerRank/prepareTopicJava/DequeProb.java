package hackerRank.prepareTopicJava;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class DequeProb {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Deque<Integer> deque = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();

        int n = in.nextInt();
        int m = in.nextInt();
        int max = 0;

        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            if(deque.size() == m) {
                if(map.size() > max) max = map.size();
                removeFromMap(map, deque.pollFirst());
                addToMap(map, num);
                deque.addLast(num);
            } else {
                addToMap(map, num);
                deque.addLast(num);
            }
        }
        System.out.println(max == 0 ? n : max);
    }

    static void addToMap(Map<Integer,Integer> map, int a){
        if(!map.containsKey(a)) map.put(a, 0);
        map.put(a, map.get(a)+1);
    }

    static void removeFromMap(Map<Integer,Integer> map, int a){
        int v = map.get(a);
        if(v == 1) map.remove(a);
        else map.put(a, v-1);
    }
}
