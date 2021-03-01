var solveSudoku = function(board) {
        
    function isSafe(row, col, num) {
        
        for(let i = 0; i < board.length; i++) {
            if(i === row) continue;
            if(board[i][col] === num) return false;
        }
        for(let i = 0; i < board[0].length; i++) {
            if(i === col) continue;
            if(board[row][i] === num) return false;
        }
        for(let i = Math.floor(row/3) * 3; i <=  Math.floor(row/3) * 3 + 2; i++) {
            for(let j = Math.floor(col/3) * 3; j <=  Math.floor(col/3) * 3 + 2; j++) {
                if(row === i && col === j) continue;
                if(board[i][j] === num) return false;
            }
        }
        return true;
    }
    
    function dfs(row, col) {
        if(col >= board[0].length) {
            return true;
        }
        if(row >= board.length) {
            row = 0;
            col += 1;
        }
        if(board[row][col] === 0) {
            for(let i = 1; i <= 9; i++) {
                if(isSafe(row, col, i)) {
                    board[row][col] = i;
                    if(dfs(row+1, col)) {
                        return true;
                    };
                    board[row][col] = 0;
                } 
            }
            return false;
        }
        return dfs(row+1, col);
    }
    dfs(0,0);
    return board;
};
let board = [
    [0, 3, 0, 2, 6, 0, 7, 0, 1],
    [6, 8, 0, 0, 7, 0, 0, 9, 0],
    [1, 9, 0, 0, 0, 4, 5, 0, 0],
    [8, 2, 0, 1, 0, 0, 0, 4, 0],
    [0, 0, 4, 6, 0, 2, 9, 0, 0],
    [0, 5, 0, 0, 0, 3, 0, 2, 8],
    [0, 0, 9, 3, 0, 0, 0, 7, 4],
    [0, 4, 0, 0, 5, 0, 0, 3, 6],
    [7, 0, 3, 0, 1, 8, 0, 0, 0],
  ];
  console.log(solveSudoku(board))