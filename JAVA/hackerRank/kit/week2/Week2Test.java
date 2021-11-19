package hackerRank.kit.week2;
import java.util.*;

public class Week2Test {
}

class W2_1 {

    /*
     * Complete the 'palindromeIndex' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static int palindromeIndex(String s) {
        char[] arr = s.toCharArray();
        System.out.println("length--------------------------" + s.length());
        int k = solve(0, arr.length-1, arr, false);
        return k <= -1?-1:k;

    }

    static int solve(int l, int r, char[] arr, boolean deleted){


        while(arr[l] == arr[r]){
            if(l+1 == r || l == r) return -2;
            l++;
            r--;
        }
        // 한번 지웠는데 지울거 또 발견
        if(deleted) return -1;
        // 지우지 않은 상태, 왼쪽 혹은 오른쪽 지우고 체크
        int left = solve(l+1, r, arr, true);
        int right = solve(l, r-1, arr, true);

        if(left == -2) return l;
        else if(right == -2) return r;
        else if(left == -1 && right == -1) return -1;
        else {
            // left != -2,  right != -2,
            if(left == -1) return right;
            else if(right == -1) return left;
            return -1;
        }
    }

}

class W2_2 {
    public static int getTotalX(List<Integer> a, List<Integer> b) {
        Collections.sort(b);
        int k = b.get(0);
        System.out.println(k);
        Set<Integer> set = new HashSet<>();

        for(int j = 1; j <= k; j++){
            boolean isValid = true;
            for(int i = 0; i < a.size(); i++){
                int v = a.get(i);
                if(j%v != 0) {
                    isValid = false;
                    break;
                }
            }
            if(isValid) set.add(j);
        }
        int res = set.size();
        Iterator<Integer> it = set.iterator();
        while(it.hasNext()){
            int v = it.next();
            System.out.println(v);
            boolean isValid = true;
            for(int i = 0; i < b.size(); i++){
                if(b.get(i)%v != 0) {
                    isValid = false;
                    break;
                }
            }
            if(!isValid) res--;
        }
        return res;
    }

}


class W2_3 {


    public static int anagram(String s) {

        if(s.length() % 2 != 0) return -1;
        Map<Character,Integer> map = new HashMap<>();

        int mid = s.length()/2;


        for(int i = 0; i < s.length(); i++){
            if(i < mid){
                map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0)+1);
            } else {
                map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0)-1);
            }
        }

        int res = 0;

        Iterator<Character> it = map.keySet().iterator();
        while(it.hasNext()){
            res+=Math.abs(map.get(it.next()));
        }
        return res/2;
    }


}