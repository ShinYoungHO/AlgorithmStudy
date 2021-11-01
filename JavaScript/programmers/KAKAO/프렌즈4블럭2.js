function solution(m, n, board) {
  board = board.map((str) => str.split(""));
  while (true) {
    let delCoord = [];
    for (let i = 1; i < m; i++) {
      for (let j = 1; j < n; j++) {
        let value = board[i][j];
        if (
          value &&
          board[i][j - 1] === board[i - 1][j - 1] &&
          board[i - 1][j - 1] === board[i - 1][j] &&
          value === board[i - 1][j]
        ) {
          delCoord.push([i, j]);
        }
      }
    }

    if (!delCoord.length) return board.toString().replace(/[^0]/g, "").length;

    for (let i = 0; i < delCoord.length; i++) {
      let [x, y] = delCoord[i];
      board[x][y] = 0;
      board[x - 1][y] = 0;
      board[x][y - 1] = 0;
      board[x - 1][y - 1] = 0;
    }

    for (let i = m - 1; i > 0; i--) {
      if (!board[i].some((block) => !block)) continue;
      for (let j = 0; j < n; j++) {
        for (let k = i - 1; k >= 0 && !board[i][j]; k--) {
          if (board[k][j]) {
            board[i][j] = board[k][j];
            board[k][j] = 0;
            break;
          }
        }
      }
    }
  }
}

console.log(solution(4, 5, ["CCBDE", "AAADE", "AAABF", "CCBBF"]));
