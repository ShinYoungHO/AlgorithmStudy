// 새로 만들어서 사용.
// coords 재할당에서 문제발생

function solution(board, r, c) {
  let coords = [];
  for (let i = 0; i < board.length; i++) {
    for (let j = 0; j < board.length; j++) {
      if (board[i][j]) {
        coords.push([i, j, board[i][j]]);
      }
    }
  }
  let coordSlice = coords.map((el) => el.slice()).slice();
  let min = Number.MAX_SAFE_INTEGER;
  const DFS = (r, c, count) => {
    let [r1, c1, v] = coords.find((el) => {
      let [rp, cp, v] = el;
      return v === board[r][c] && rp !== r && cp !== c;
    });
    let oldCoords = coords;
    coords = coords.filter((el) => el[2] !== v);
    count += 2 + getMinDist(r, c, r1, c1);
    if (!coords.length) {
      if (count < min) min = count;
      return oldCoords;
    }
    for (let i = 0; i < coords.length; i++) {
      let [nr, nc, _] = coords[i];
      coords = DFS(nr, nc, count + getMinDist(r1, c1, nr, nc));
    }
    return oldCoords;
  };
  if (board[r][c]) {
    DFS(r, c, 0);
  } else {
    for (let i = 0; i < coordSlice.length; i++) {
      let [nr, nc] = coordSlice[i];
      DFS(nr, nc, getMinDist(r, c, nr, nc));
      coords = coordSlice;
    }
  }
  return min;
}

function getMinDist(r, c, r1, c1) {
  return Math.min(Math.abs(r - r1), Math.abs(c - c1)) + 1;
}

////////////////////////////////////
////////////////////////////////////
////////////////////////////////////

function solution(board, r, c) {
  let coords = [];
  for (let i = 0; i < board.length; i++) {
    for (let j = 0; j < board.length; j++) {
      if (board[i][j]) {
        coords.push([i, j, board[i][j]]);
      }
    }
  }

  const visited = Array(coords.length).fill(false);
  let min = Number.MAX_SAFE_INTEGER;

  const DFS = (r, c, count, idx, deleted) => {
    // 같은 짝 찾기
    let [r1, c1, v, nidx] = [0, 0, 0, 0];
    for (let i = 0; i < coords.length; i++) {
      [r1, c1, v] = coords[i];
      if (v === board[r][c] && idx !== i) {
        nidx = i;
        break;
      }
    }
    visited[idx] = true;
    visited[nidx] = true;
    count += 2 + getMinDist(r, c, r1, c1);
    if (deleted + 2 === coords.length) {
      if (count < min) min = count;
      visited[idx] = false;
      visited[nidx] = false;
      return;
    }

    for (let i = 0; i < coords.length; i++) {
      if (!visited[i]) {
        let [nr, nc, _] = coords[i];
        DFS(nr, nc, count + getMinDist(r1, c1, nr, nc), i, deleted + 2);
      }
    }
    visited[idx] = false;
    visited[nidx] = false;
  };

  if (board[r][c]) {
    let idx = null;
    for (let i = 0; i < coords.length; i++) {
      let [nr, nc, v] = coords[i];
      if (r === nr && c === nc && v === board[r][c]) {
        idx = i;
      }
    }
    DFS(r, c, 0, idx, 0);
  } else {
    for (let i = 0; i < coords.length; i++) {
      let [nr, nc] = coords[i];
      DFS(nr, nc, getMinDist(r, c, nr, nc), i, 0);
    }
  }
  return min;
}

function getMinDist(r, c, r1, c1) {
  if (r === r1 || c === c1) {
    if (r1 % 3 === 0 || c1 % 3 === 0) {
      return 1;
    }
  }
  if (r1 % 3 === 0 && c1 % 3 === 0) return 2;

  if (r1 % 3 === 0) {
    return Math.abs(c1 - c) + 1;
  }
  if (c1 % 3 === 0) {
    return Math.abs(r1 - r) + 1;
  }
  return Math.abs(c1 - c) + Math.abs(r1 - r);
}
// console.log(getMinDist(1, 3, 1, 1));
console.log(
  solution(
    [
      [3, 0, 0, 2],
      [0, 0, 1, 0],
      [0, 1, 0, 0],
      [2, 0, 0, 3],
    ],
    0,
    1
  )
);
