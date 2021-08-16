package stepByStep.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Average {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        for(int i = 0; i < n; i++){
            String[] lineArr = bf.readLine().split(" ");
            int studentCount = Integer.parseInt(lineArr[0]);
            double[] scores = new double[studentCount];
            double total = 0;
            for(int j = 1; j < lineArr.length; j++){
                int score = Integer.parseInt(lineArr[j]);
                total+= score;
                scores[j-1] = score;
            }

            double avg = total/(double)studentCount;
            double accept = 0;
            for(int j = 0; j < scores.length; j++){
                if(scores[j] > avg){
                    accept++;
                }
            }
            if(accept == 0.0) {
                System.out.println("0.000%");
                continue;
            }
            System.out.println(String.format("%.3f%%", accept/(double)studentCount*100));
        }
        bf.close();
    }
}


