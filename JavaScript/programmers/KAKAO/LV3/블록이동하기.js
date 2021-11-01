function solution(board) {
  let len = board.length;
  const visited = new Set();
  visited.add(
    [
      [0, 0],
      [0, 1],
    ]
      .sort()
      .toString()
  );
  const q = [[0, 0, 0, 1, 0]];
  const dx = [-1, 0, 1, 0];
  const dy = [0, 1, 0, -1];
  let result = Number.MAX_SAFE_INTEGER;

  while (q.length) {
    const [x1, y1, x2, y2, c] = q.shift();
    const nextCoords = [];
    // 상하좌우 이동 다음좌표
    for (let i = 0; i < 4; i++) {
      nextCoords.push([x1 + dx[i], y1 + dy[i], x2 + dx[i], y2 + dy[i]]);
    }
    // 회전 다음좌표 (1 배제)
    let [dnx, dny] = [x2 - x1, y2 - y1];

    if (dny) {
      // 가로
      if (
        board[x1 - 1] &&
        board[x2 - 1] &&
        board[x1 - 1][y1] === 0 &&
        board[x2 - 1][y2] === 0
      ) {
        nextCoords.push([x1 - 1, y1, x1, y1], [x2 - 1, y2, x2, y2]);
      }
      if (
        board[x1 + 1] &&
        board[x2 + 1] &&
        board[x1 + 1][y1] === 0 &&
        board[x2 + 1][y2] === 0
      ) {
        nextCoords.push([x1 + 1, y1, x1, y1], [x2 + 1, y2, x2, y2]);
      }
    } else {
      // 세로
      if (board[x1][y1 - 1] === 0 && board[x2][y2 - 1] === 0) {
        nextCoords.push([x1, y1 - 1, x1, y1], [x2, y2 - 1, x2, y2]);
      }
      if (board[x1][y1 + 1] === 0 && board[x2][y2 + 1] === 0) {
        nextCoords.push([x1, y1 + 1, x1, y1], [x2, y2 + 1, x2, y2]);
      }
    }
    for (let i = 0; i < nextCoords.length; i++) {
      let [nx1, ny1, nx2, ny2] = nextCoords[i];

      if (
        nx1 >= 0 &&
        nx2 >= 0 &&
        ny1 >= 0 &&
        ny2 >= 0 &&
        nx1 < len &&
        nx2 < len &&
        ny1 < len &&
        ny2 < len
      ) {
        if (!board[nx1][ny1] && !board[nx2][ny2]) {
          if (
            (nx1 === len - 1 && ny1 === len - 1) ||
            (nx2 === len - 1 && ny2 === len - 1)
          ) {
            if (c + 1 < result) {
              result = c + 1;
              continue;
            }
          }
          let key = [
            [nx1, ny1],
            [nx2, ny2],
          ]
            .sort()
            .toString();
          if (!visited.has(key)) {
            visited.add(key);
            q.push([nx1, ny1, nx2, ny2, c + 1]);
          }
        }
      }
    }
  }
  return result;
}

solution([
  [0, 0],
  [1, 0],
]);
