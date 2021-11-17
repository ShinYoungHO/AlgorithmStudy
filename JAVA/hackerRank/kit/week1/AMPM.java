package hackerRank.kit.week1;

import java.util.StringTokenizer;

public class AMPM {
    static String AM="AM";
    static String PM="PM";
    public static String timeConversion(String s) {
        StringTokenizer st;
        String type;
        if(s.contains(PM)){
            st = new StringTokenizer(s.replaceAll(PM,""), ":");
            type = PM;
        } else {
            st = new StringTokenizer(s.replaceAll(AM,""), ":");
            type = AM;
        }
        return solve(st, type);
    }

    static String solve(StringTokenizer st, String type){
        int hh = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        if(type.equals(AM)){
            hh %= 12;
        } else {
            if(hh != 12) hh+=12;
            hh %= 24;
        }
        sb.append(hh < 10 ? "0"+hh : hh).append(":")
                .append(st.nextToken()).append(":")
                .append(st.nextToken());

        return sb.toString();
    }
}
