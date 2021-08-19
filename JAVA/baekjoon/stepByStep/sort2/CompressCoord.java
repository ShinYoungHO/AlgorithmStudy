package stepByStep.sort2;



import java.io.*;
import java.util.Arrays;

public class CompressCoord {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] input = getInput(br.readLine(), n);
        int[] result = new int[n];

        Arrays.sort(input, (e1, e2) -> {
            if(e1[0] > e2[0]) return 1;
            else return -1;
        });

        result[input[0][1]] = 0;
        for(int i = 1; i < n;i++){
            if(input[i][0] != input[i-1][0]){
                result[input[i][1]] = result[input[i-1][1]]+1;
            } else {
                result[input[i][1]] = result[input[i-1][1]];
            }
        }

        for(int i = 0; i < n; i++){
            bw.write(result[i]+" ");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static int[][] getInput(String input, int len){
        String[] inputArr = input.split(" ");
        int[][] result = new int[len][3];
        // [value, idx]
        for(int i = 0; i < len; i++){
            int value = Integer.parseInt(inputArr[i]);
            result[i][0] = value;
            result[i][1] = i;
        }
        return result;
    }
}
