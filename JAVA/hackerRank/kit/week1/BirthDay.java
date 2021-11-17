package hackerRank.kit.week1;

import java.util.List;

public class BirthDay {
    public static int birthday(List<Integer> s, int d, int m) {
        int[] pre = new int[s.size()+1];
        int ans = 0;

        for(int i = 0; i < s.size(); i++){
            int v = s.get(i);
            pre[i+1] = pre[i]+v;
        }

        for(int i = m; i < pre.length; i++){
            if(pre[i]-pre[i-m] == d) ans++;
        }

        return ans;
    }
}
