package category.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class RemoteController {

    static int solve(Set<Integer> broken, int target){
        int res = 0;
        if(broken.size() < 10){
            for(int i = 0; i <= 500000; i++){
                int m = target-i;
                int p = target+i;
                if(m >= 0 && isValid(m, broken)) {
                    res+=length(m)+Math.abs(target-m);
                    break;
                }

                if(isValid(p, broken)) {
                    res+=length(p)+Math.abs(target-p);
                    break;
                }
            }
            return Math.min(res, Math.abs(target-100));
        } else return Math.abs(target-100);
    }

    static int length(int v){
        if(v == 0) return 1;
        int cnt = 0;
        while(v > 0){
            v/=10;
            cnt++;
        }
        return cnt;
    }
    static boolean isValid(int k, Set<Integer> broken){
        if(k == 0) return !broken.contains(k);
        while(k > 0){
            int v = k % 10;
            if(broken.contains(v)) return false;
            k /= 10;
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int target = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        Set<Integer> broken = new HashSet<>();
        if(n > 0){
            String s = br.readLine();
            for(int i = 0; i < s.length(); i++){
                char c = s.charAt(i);
                if(c >= '0' && c <= '9') broken.add(c-'0');
            }
        }

        System.out.println(solve(broken, target));;
    }
}
