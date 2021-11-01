function solution(board, moves) {
  const stack = [];
  let result = 0;
  for (let j = 0; j < moves.length; j++) {
    const move = moves[j] - 1;
    for (let i = 0; i < board.length; i++) {
      if (board[i][move] !== 0) {
        const val = board[i][move];
        board[i][move] = 0;
        if (stack[stack.length - 1] === val) {
          stack.pop();
          result += 2;
        } else {
          stack.push(val);
        }
        break;
      }
    }
  }
  return result;
}
