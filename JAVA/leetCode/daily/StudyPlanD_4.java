package category.ns.leetSolved;
import java.util.*;

public class StudyPlanD_4 {
    public boolean backspaceCompare(String s, String t) {
        return remove(s).equals(remove(t));
    }
    String remove(String s){
        StringBuilder sb = new StringBuilder();
        Deque<Character> li = new LinkedList<>();

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '#'){
                if(li.size() > 0) li.pollLast();
                continue;
            }
            li.addLast(c);
        }

        while(!li.isEmpty()){
            sb.append(li.pollFirst());
        }
        return sb.toString();
    }



    int fi,si;
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> ans = new ArrayList<>();
        fi = si = 0;
        if(oob(fi,firstList)||oob(si,secondList)) return new int[0][2];

        int[] fl = firstList[fi];
        int[] sl = secondList[si];
        while(true){

            if(fl[1] > sl[1] && fl[0] > sl[1]){
                if(oob(si+1, secondList)) break;
                sl = secondList[++si];
                continue;
            } else if(fl[1] < sl[1] && fl[1] < sl[0]) {
                if(oob(fi+1, firstList)) break;
                fl = firstList[++fi];
                continue;
            }
            int s = Math.max(fl[0], sl[0]);
            int t = Math.min(fl[1], sl[1]);

            ans.add(new int[]{s, t});
            if(fl[1] != sl[1]){
                if(t == sl[1]){
                    if(oob(si+1, secondList)) break;
                    sl = secondList[++si];
                } else {
                    if(oob(fi+1, firstList)) break;
                    fl = firstList[++fi];
                }
            } else {
                if(oob(fi+1, firstList)||oob(si+1, secondList)) break;
                fl = firstList[++fi];
                sl = secondList[++si];
            }
        }



        int[][] res = new int[ans.size()][2];
        for(int i = 0; i < ans.size(); i++){
            res[i] = ans.get(i);
        }
        return res;
    }

    boolean oob(int l, int[][] arr){
        return arr.length <= l;
    }















    //////////////////////////////////////////
    class Solution {
        public int[][] intervalIntersection_best(int[][] firstList, int[][] secondList) {
            int m = firstList.length, n = secondList.length;
            int i = 0, j = 0;
            List<int[]> res = new ArrayList<>();

            while (i < m && j < n) {
                getOverlappedInterval(firstList[i], secondList[j], res);
                if (firstList[i][1] < secondList[j][1]) i++;
                else j++;
            }

            return res.toArray(new int[res.size()][2]);
        }

        private void getOverlappedInterval(int[] arr1, int[] arr2, List<int[]> res) {
            int start1 = arr1[0], end1 = arr1[1], start2 = arr2[0], end2 = arr2[1];
            if (end1 < start2 || end2 < start1) return;

            int start = Math.max(start1, start2);
            int end = Math.min(end1, end2);
            res.add(new int[]{start, end});
        }
    }
}
