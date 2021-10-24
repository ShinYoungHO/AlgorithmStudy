package category.etc;

import java.io.*;
import java.util.*;

public class DBJ {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int n,m,res = 0;
        Set<String> group = new HashSet<>();
        ArrayList<String> arr = new ArrayList<>();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++){
            group.add(br.readLine());
        }

        for(int j = 0; j < m; j++){
            String s = br.readLine();
            if(group.contains(s)) {
                arr.add(s);
                res++;
            }
        }
        Collections.sort(arr);

        for(int i = 0; i < arr.size(); i++){
            sb.append(arr.get(i)).append("\n");
        }
        System.out.println(res);
        System.out.println(sb);
    }
}
