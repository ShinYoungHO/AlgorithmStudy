package stepByStep.backtracking;


import java.io.BufferedReader;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Sudoku {
    static int[][] board = new int[9][9];
    static int[] rowDat = new int[9]; // |
    static int[] colDat = new int[9]; // ㅡ
    static int[] x3Dat = new int[9]; // ㅁ
    static Map<Integer, Integer> map = new HashMap<>();
    static int[][] x3 = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int zeroCount = 0;

        for(int i = 1; i <= 9; i++) map.put(i, (int)Math.pow(2, i));
        for(int i = 0; i < 9; i++){
            String[] lineArr = br.readLine().split(" ");
            for(int j = 0; j < 9; j++){
                int v = Integer.parseInt(lineArr[j]);
                // initialize board
                board[i][j] = v;

                x3[i][j] = 3*(i/3) + j/3;
                if(v == 0) {
                    zeroCount++;
                    continue;
                }
                int mapV = map.get(v);
                // initialize Dat
                rowDat[i] |= mapV;
                colDat[j] |= mapV;
                x3Dat[x3[i][j]] |= mapV;
            }
        }


        int[][] targets = new int[zeroCount][2];
        int idx = 0;
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++){
                if(board[i][j] == 0){
                    targets[idx] = new int[]{i, j};
                    idx++;
                }

            }
        }



//        DFS2(0, targets);
        DFS(0,0);

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                bw.write(board[i][j]+" ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean DFS(int sr, int sc){
        if(sr == 8 && sc == 8) return true;
        for(int i = sr; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] == 0){
                    for(int k = 1; k <= 9; k++){
                        boolean isValid = checkValid(i, j, k);
                        if(isValid){
                            save(i, j, k);
                            if(DFS(i, j)) return true;
                            extract(i, j, k);
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean DFS2(int idx, int[][] targets){
        if(idx == targets.length) return true;
        int r = targets[idx][0];
        int c = targets[idx][1];
        if(board[r][c] == 0){
            for(int k = 1; k <= 9; k++){
                boolean isValid = checkValid(r, c, k);
                if(isValid){
                    save(r, c, k);
                    if(DFS2(idx+1, targets)) return true;
                    extract(r, c, k);
                }
            }
            return false;
        }
        return true;
    }

    public static void save(int r, int c, int target){
        int mapV = map.get(target);
        board[r][c] = target;
        rowDat[r] |= mapV;
        colDat[c] |= mapV;
        x3Dat[x3[r][c]] |= mapV;
    }

    public static void extract(int r, int c, int target){
        int mapV = map.get(target);
        board[r][c] = 0;
        rowDat[r] &= ~mapV;
        colDat[c] &= ~mapV;
        x3Dat[x3[r][c]] &= ~mapV;
    }

    public static boolean checkValid(int r, int c, int target){
        int mapV = map.get(target);
        if((rowDat[r] & mapV ) != 0) return false;
        else if((colDat[c] & mapV) != 0) return false;
        else if((x3Dat[x3[r][c]] & mapV) != 0) return false;
        return true;
    }
}
