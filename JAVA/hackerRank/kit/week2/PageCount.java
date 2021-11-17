package hackerRank.kit.week2;

public class PageCount {
    public static int pageCount(int n, int p) {
        p /= 2;
        int min = n;

        int k = solve(n/2, p, -1);
        if(k < min) min = k;

        k = solve(0, p, 1);
        if(k < min) min = k;

        return min;
    }

    static int solve(int k, int p, int add){
        int cnt = 0;
        while(k != p){
            k+=add;
            cnt++;
        }
        return cnt;
    }
}
