package hackerRank.kit.week1;

public class FlippingKit {
    public static long flippingBits(long n) {
        n = ~n;
        long ans = 0l;
        long bit = 1l;
        for(int i = 0; i < 32; i++){
            long target = bit << i;
            if((n & target) == target) ans+=Math.pow(2, i);
        }
        return ans;
    }
}
