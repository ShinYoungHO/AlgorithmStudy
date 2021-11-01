function solution(board) {
  const xLen = board[0].length;
  const yLen = board.length;
  let answer = 0;

  if (xLen < 2 || yLen < 2) return board[0][0];

  for (let x = 1; x < yLen; x++) {
    for (let y = 1; y < xLen; y++) {
      if (board[x][y] > 0) {
        const min = Math.min(
          board[x - 1][y - 1],
          board[x][y - 1],
          board[x - 1][y]
        );
        board[x][y] = min + 1;
      }
      if (answer < board[x][y]) {
        answer = board[x][y];
      }
    }
  }
  return answer ** 2;
}
