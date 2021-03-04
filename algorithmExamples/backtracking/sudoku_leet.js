var solveSudoku = function(board) {
        
    function isSafe(r, c, num) {
        
        for(let i = 0; i < board.length; i++) {
            if(i === r) continue;
            if(board[i][c] === num) return false;
        }
        for(let i = 0; i < board[0].length; i++) {
            if(i === c) continue;
            if(board[r][i] === num) return false;
        }
        for(let i = Math.floor(r/3) * 3; i <=  Math.floor(r/3) * 3 + 2; i++) {
            for(let j = Math.floor(c/3) * 3; j <=  Math.floor(c/3) * 3 + 2; j++) {
                if(r === i && c === j) continue;
                if(board[i][j] === num) return false;
            }
        }
        return true;
    }
    
    function dfs(r, c) {
        
        if(c >= board[0].length) {
            return true;
        }
        if(r >= board.length) {
            r = 0;
            c += 1;
        }
        if(board[r][c] === 0) {
            for(let i = 1; i <= 9; i++) {
                if(isSafe(r, c, i)) {
                    board[r][c] = i;
                    if(dfs(r+1, c)) {//다음 노드 검사 진행. false가 되면 다시 돌아와서 재시작.
                        return true;
                    };
                    board[r][c] = 0;
                } 
            }
            return false;
        }
        return dfs(r+1, c);
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