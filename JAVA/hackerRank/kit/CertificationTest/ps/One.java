package hackerRank.kit.CertificationTest.ps;


import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;


public class One {
}

class Result {
    public static List<Long> findSum(List<Integer> numbers, List<List<Integer>> queries) {
        // Write your code here

        List<Long> answer = new ArrayList<>();
        int n = 1;
        while(n*2 <=numbers.size()) n*=2;
        int[] seg = new int[4*numbers.size()];
        long[] pre = new long[numbers.size()+1];
        getSeg(numbers,seg, 1, n, 1);

        for(int i = 0; i < numbers.size(); i++){
            pre[i+1] = pre[i] + numbers.get(i);
        }

        System.out.println(Arrays.toString(seg));
        int from, to, add;
        long v;
        for(int i = 0; i < queries.size(); i++){
            List<Integer> query = queries.get(i);
            v = 0;
            from = query.get(0);
            to = query.get(1);
            add = query.get(2);
            v = pre[to]-pre[from-1];
            boolean hasZero = searchZero(seg, 1, n, from, to, 1);

            answer.add(hasZero ? v+add : v);
        }
        return answer;
    }

    static boolean searchZero(int[] seg, int l, int r, int f, int t, int idx){
        int mid = (l+r)/2;
        if((l >= f && r <= t) || l == r) {
            return seg[idx] == 1;
        }
        if(f > mid) return searchZero(seg, mid+1, r, f, t, idx*2+1);
        else if(t <= mid) return searchZero(seg, l, mid, f, t, idx*2);
        else {
            boolean lv,rv;
            lv = searchZero(seg, l, mid, f, t, idx*2);
            rv = searchZero(seg, mid+1, r, f, t, idx*2+1);
            if(lv || rv) return true;
        }
        return false;
    }

    static int getSeg(List<Integer> numbers, int[] seg, int f, int t, int i){
        System.out.println("           "+f+":"+t+":"+i);
        if(f == t) {
            if(f > numbers.size()) return 0;
            int v = numbers.get(f-1);
            if(v == 0) seg[i] = 1;

            return seg[i];
        }
        int mid = (f+t)/2;
        int left = getSeg(numbers, seg,f, mid, i*2);
        int right = getSeg(numbers, seg, mid+1, t, i*2+1);

        if(left == 1 || right == 1){
            seg[i] = 1;
        }

        return seg[i];
    }

}

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int numbersCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> numbers = IntStream.range(0, numbersCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int queriesRows = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, queriesRows).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Long> result = Result.findSum(numbers, queries);
        System.out.println(result);
        bufferedReader.close();
    }
}
/*
2
-5
0
2
3
2 2 20
1 2 10
 */