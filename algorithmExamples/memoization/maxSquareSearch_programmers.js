// 정답률 100%
// 효율성 0%
// 메모이제이션 추가해보기

function solution(board) {
  const xLen = board.length;
  const yLen = board[0].length;
  const startLen = Math.min(xLen, yLen);

  for (let i = startLen; i > 0; i--) {
    for (let x = 0; x <= xLen - i; x++) {
      for (let y = 0; y <= yLen - i; y++) {
        const searchResult = search(x, y, i, board);
        if (searchResult) {
          return i ** 2;
        }
      }
    }
  }
  return 0;
}

function search(xstart, ystart, recLen, board) {
  let isValid = true;
  for (let x = xstart; x < xstart + recLen; x++) {
    for (let y = ystart; y < ystart + recLen; y++) {
      if (board[x][y] === 0) {
        isValid = false;
        break;
      }
    }
  }
  return isValid;
}
