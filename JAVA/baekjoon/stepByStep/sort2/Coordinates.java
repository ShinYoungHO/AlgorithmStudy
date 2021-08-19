package stepByStep.sort2;

import java.io.*;
import java.util.Arrays;

public class Coordinates {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Coordinate[] result = new Coordinate[n];

        for(int i = 0; i < n; i++){
            String[] coordinate = br.readLine().split(" ");
            Coordinate cord = new Coordinate();
            cord.x = Integer.parseInt(coordinate[0]);
            cord.y = Integer.parseInt(coordinate[1]);
            result[i] = cord;
        }

        Arrays.sort(result);
        for(int i = 0; i < n; i++){
            bw.write(result[i].toString()+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static class Coordinate implements Comparable<Coordinate>{
        int x;
        int y;
        @Override
        public int compareTo(Coordinate o) {
            if(x-o.x > 0) return 1;
            else if(x-o.x < 0) return -1;
            else {
                if(y-o.y > 0) return 1;
                else return -1;
            }
        }
        @Override
        public String toString(){
            return x+" "+y;
        }
    }
}
