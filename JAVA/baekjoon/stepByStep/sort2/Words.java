package stepByStep.sort2;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Words {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Set<String> bucket = new HashSet<>();

        for(int i = 0; i < n; i++) bucket.add(br.readLine());

        int len = bucket.size();
        Word[] words = new Word[len];
        int idx = 0;
        for(String value:bucket){
            Word w = new Word();
            w.v = value;
            words[idx] = w;
            idx++;
        }

        Arrays.sort(words);

        for(int i = 0; i < len; i++){
            bw.write(words[i].v+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static class Word implements Comparable<Word>{
        String v;

        @Override
        public int compareTo(Word o) {
            if(o.v.length() > v.length()) return -1;
            else if(o.v.length() < v.length()) return 1;
            else return -1*o.v.compareTo(v);
        }
        @Override
        public String toString(){
            return v;
        }
    }
}


class Main1{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        String[] arr = new String[N];
        for(int i = 0 ; i < N ; i++){
            arr[i] = br.readLine();
        }

        Arrays.sort(arr, (e1, e2) -> {
            if(e1.length() == e2.length()) {
                return e1.compareTo(e2);
            } else {
                return e1.length() - e2.length();
            }
        });
        sb.append(arr[0]).append("\n");
        for(int i = 1 ; i < N ; i++){
            if(! arr[i].equals(arr[i-1])) sb.append(arr[i]).append("\n");
        }
        System.out.println(sb);
    }
}
