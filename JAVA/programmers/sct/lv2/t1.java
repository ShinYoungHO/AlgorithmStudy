package test.lv2;

import java.util.ArrayList;
import java.util.Arrays;

public class t1 { }
class Solution {
    public String[] solution(int[][] line) {
        boolean[][] map = new boolean[1000][1000];
        ArrayList<int[]> coords = new ArrayList<>();
        int a,b,c,d,e,f,x,y;
        int mX = Integer.MAX_VALUE, mY = Integer.MAX_VALUE;
        int m=0,n=0;
        for(int i = 0; i < line.length-1; i++){
            for(int j = i+1; j < line.length; j++){
                int[] line1 = line[i];
                int[] line2 = line[j];
                a = line1[0];
                b = line1[1];
                e = line1[2];

                c = line2[0];
                d = line2[1];
                f = line2[2];

                int l = a*d-b*c;
                int r;
                if(l != 0){
                    r = b*f-e*d;
                    if(!div(l, r)) continue;
                    x = r/l;

                    r = e*c-a*f;
                    if(!div(l, r)) continue;
                    y = r/l;

                    coords.add(new int[]{x,y});
                }
            }
        }


        for(int i = 0; i < coords.size(); i++){
            int[] crd = coords.get(i);
            System.out.println(Arrays.toString(crd));
            x = crd[0];
            y = crd[1];
            if(x < mX) mX = x;
            if(y < mY) mY = y;
        }

        System.out.println(mX+":"+mY);


        for(int i = 0; i < coords.size(); i++){
            int[] crd = coords.get(i);

            x = crd[0]-mX;
            y = crd[1]-mY;
            System.out.println(x+":"+y);
            if(x > m)m=x;
            if(y > n)n=y;
            map[x][y] = true;
        }
        String[] ans = new String[m];

        for(int i = 0; i < m; i++){
            StringBuilder sb = new StringBuilder("");
            for(int j = 0; j < n; j++){
                if(map[i][j]) sb.append("*");
                else sb.append(".");
            }
            ans[i] = sb.toString();
        }

        return ans;
    }



    boolean div(int l, int r){
        if(Math.abs(r)%Math.abs(l) == 0) return true;
        return false;
    }
}