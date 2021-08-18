package stepByStep.bruteforce;

import java.util.Scanner;

public class ChessBoard {
    static int min;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");

        int r = Integer.parseInt(input[0]);
        int c = Integer.parseInt(input[1]);
        String[][] board = new String[r][c];
        min = r*c;

        // init board
        for(int i = 0; i < r; i++){
            String[] column = sc.nextLine().split("");
            for(int j = 0; j < c; j++){
                board[i][j] = column[j];
            }
        }
        //
        for(int i = 0; i <= r-8; i++){
            for(int j = 0; j <= c-8; j++){
                checkBoard(i, j, board);
            }
        }
        System.out.println(min);
    }

    public static void checkBoard(int r, int c, String[][] board){
        int count = 0;
        String initialColor = board[r][c];
        for(int i = r; i < r+8; i++){
            String prev;
            if((i-r) % 2 == 0) {
                if(initialColor.equals("W")) prev = "B";
                else prev = "W";
            } else {
                if(initialColor.equals("B")) prev = "B";
                else prev = "W";
            }
            for(int j = c; j < c+8; j++){
                if(prev.equals(board[i][j])){
                    count++;
                    if(prev.equals("W")) prev = "B";
                    else prev = "W";
                } else {
                    prev = board[i][j];
                }
            }
        }

        if(min > count) min = count;
        if(min > 64-count) min = 64-count;
    }
}

/**
 8 8
 BBWBWBBB
 BWBWBWBW
 WBWBWBWB
 BWBWBWBW
 WBWBWBWB
 BWBWBWBW
 WBWBWBWB
 BWBWBWBW

 */