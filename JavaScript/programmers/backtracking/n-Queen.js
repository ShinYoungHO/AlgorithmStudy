// // 시간초과 :: 백트래킹

// function solution(n) {
//   let board = Array.from(new Array(n), () => new Array(n).fill(false));
//   let ans = 0;
//   const DFS = (r, c, q) => {
//     if (q === n) {
//       ans++;
//       return;
//     }
//     for (let i = r; i < n; i++) {
//       for (let j = c; j < n; j++) {
//         if (board[i][j]) continue;
//         if (checkValid(board, i, j, n)) {
//           board[i][j] = true;
//           DFS(i + 1, 0, q + 1);
//           board[i][j] = false;
//         }
//       }
//       return;
//     }
//   };
//   DFS(0, 0, 0);
//   console.log(ans);
//   return ans;
// }

// function checkValid(board, r, c, len) {
//   for (let i = 0; i < len; i++) {
//     if (board[i][c]) return false;
//   }

//   for (let j = 0; j < len; j++) {
//     if (board[r][j]) return false;
//   }

//   let dc = [-1, -1, 1, 1];
//   let dr = [-1, 1, 1, -1];

//   for (let i = 0; i < 4; i++) {
//     let nr = dr[i] + r;
//     let nc = dc[i] + c;
//     while (nr < len && nr >= 0 && nc < len && nc >= 0) {
//       if (board[nr][nc]) return false;
//       nr += dr[i];
//       nc += dc[i];
//     }
//   }
//   return true;
// }
// solution(12);

function solution(n) {
  let map = {
    r: new Array(n).fill(false),
    c: new Array(n).fill(false),
    add: new Array(2 * n).fill(false),
  };
  for (let i = 1 - n; i <= 1 + n; i++) {
    map[i] = false;
  }

  let ans = 0;
  const DFS = (q) => {
    if (q === n) {
      ans++;
      return;
    }
    for (let i = q; i < n; i++) {
      for (let j = 0; j < n; j++) {
        if (map.r[i]) continue;
        if (checkValid(i, j, map)) {
          changeMap(i, j, map, true);
          DFS(i + 1, 0, q + 1);
          changeMap(i, j, map, false);
        }
      }
      return;
    }
  };
  DFS(0);
  return ans;
}

function checkValid(r, c, map) {
  if (map.r[r]) return false;
  if (map.c[c]) return false;
  if (map.add[r + c]) return false;
  if (map[r - c]) return false;
  return true;
}

function changeMap(r, c, map, bool) {
  map.r[r] = bool;
  map.c[c] = bool;
  map.add[r + c] = bool;
  map[r - c] = bool;
}

solution(12);

/////////// map 단일배열화
// idx: r, value : c
// 대각선 확인도 저장하는 대신 비교로만
//
// function solution(n) {
//   let map = new Array(n).fill(0);
//   let ans = 0;
//   for (let i = 0; i < n; i++) {
//     map[0] = i;
//     backtc(1);
//   }
//   function backtc(c) {
//     if (c === n) {
//       ans++;
//       return;
//     }

//     for (let i = 0; i < n; i++) {
//       let check = true;
//       for (let j = 0; j < c; j++) {
//         map[c] = i;
//         if (
//           map[j] === map[c] ||
//           j - c === map[j] - map[c] ||
//           j - c === map[c] - map[j]
//         ) {
//           check = false;
//           break;
//         }
//       }
//       if (check) {
//         console.log(map);
//         backtc(c + 1);
//       }
//     }
//   }
//   return ans;
// }
