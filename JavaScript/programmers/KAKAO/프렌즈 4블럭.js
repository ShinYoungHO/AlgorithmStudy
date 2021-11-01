// 시간초과 .................

function solution(m, n, board) {
  let visited = Array.from(Array(board.length), () =>
    Array(board[0].length).fill(false)
  );
  board = board.map((el) => el.split(""));
  let result = 0;
  while (step(board, visited, 0, 0)) {
    let tempres = faltBoard(board, visited);
    if (result !== tempres) {
      result = tempres;
    } else break;
  }
  return result;
}

function step(board, visited, _i, _j) {
  let changed = false;
  for (let i = _i; i < board.length - 1; i++) {
    for (let j = _j; j < board[0].length - 1; j++) {
      if (!visited[i][j]) {
        recur(board, visited, i, j);
        changed = true;
      }
    }
  }
  return changed;
}

function recur(board, visited, i, j) {
  let changed = false;
  if (check22(board, i, j)) {
    changed = true;
    visited22(visited, i, j);
    [
      [i + 1, j],
      [i, j + 1],
      [i + 1, j + 1],
    ].forEach((coord) => {
      let [x, y] = coord;
      if (x < board.length - 1 && y < board[0].length - 1) {
        recur(board, visited, x, y);
      }
    });
  }
  return changed;
}

function check22(board, i, j) {
  if (
    board[i][j] === board[i + 1][j] &&
    board[i][j] === board[i][j + 1] &&
    board[i][j] === board[i + 1][j + 1]
  ) {
    return true;
  } else {
    return false;
  }
}

function visited22(visited, i, j) {
  visited[i][j] = true;
  visited[i + 1][j] = true;
  visited[i][j + 1] = true;
  visited[i + 1][j + 1] = true;
}

function faltBoard(board, visited) {
  let result = 0;
  for (let j = 0; j < board[0].length; j++) {
    let count = 0;
    for (let i = board.length - 1; i >= 0; i--) {
      if (visited[i][j] === true) {
        count++;
        continue;
      } else if (count && i + count >= 0) {
        board[i + count][j] = board[i][j];
        visited[i + count][j] = false;
        visited[i][j] = true;
      }
    }
    result += count;
  }
  return result;
}

solution(4, 4, ["CCBDE", "AAADE", "AAABF", "CCBBF"]);
