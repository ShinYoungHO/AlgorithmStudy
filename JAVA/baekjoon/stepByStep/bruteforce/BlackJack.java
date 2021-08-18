package stepByStep.bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BlackJack {
    static int max = -1;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] state = bf.readLine().split(" ");
        int[] input = getInput(bf.readLine());

        int n = Integer.parseInt(state[0]);
        int target = Integer.parseInt(state[1]);

        for(int i = 0; i < n-2; i++){
            for(int j = i+1; j < n-1; j++){
                for(int k = j+1; k < n; k++){
                    int current = input[i] + input[j] + input[k];
                    if(current <= target && max < current) max = current;
                }
            }
        }

        System.out.println(max);
        bf.close();
    }

    public static int[] getInput(String input){
        String[] temp = input.split(" ");
        int[] result = new int[temp.length];

        for(int i = 0; i < temp.length; i++){
            result[i] = Integer.parseInt(temp[i]);
        }
        return result;
    }
}
