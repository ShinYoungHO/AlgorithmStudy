// bitmask
function solution(n, build_frame) {
  // ps pe bs be
  const map = Array.from(new Array(n + 1), () => new Array(n + 1).fill(16));
  const post = (map, x, y) => y === 0 || (map[x][y] & 7) === 4 || map[x][y] & 3;
  // 밑바닥이거나, 기둥의 꼭대기였거나, 보가 있는 곳이거나
  const beam = (map, x, y) =>
    map[x][y] & 4 || map[x + 1][y] & 4 || (map[x][y] & 1 && map[x + 1][y] & 2);
  // 기둥의 꼭대기에 있거나,
  // 설치했을 때 오른쪽 끝이 기둥의 꼭대기거나
  // 놓는점이 보의 시작점이고 우측으로 이동한곳이 보가 시작했던 곳이거나
  const isValid = (map) => {
    for (let x = 0; x <= n; x++) {
      for (let y = 0; y <= n; y++) {
        if (map[x][y] & 8) {
          if (!post(map, x, y)) return false;
        }
        if (map[x][y] & 2) {
          if (!beam(map, x, y)) return false;
        }
      }
    }
    return true;
  };

  for (let i = 0; i < build_frame.length; i++) {
    let [x, y, s, t] = build_frame[i];
    if (t === 1) {
      // 설치
      if (s === 0) {
        // 기둥
        if (post(map, x, y)) {
          map[x][y] |= 8;
          map[x][y + 1] |= 4;
        }
      } else {
        // 보
        if (beam(map, x, y)) {
          map[x][y] |= 2;
          map[x + 1][y] |= 1;
        }
      }
    } else {
      // 해체
      if (s === 0) {
        // 기둥
        map[x][y] &= 7; // 8을 없앤다
        map[x][y + 1] &= 11; // 4를 없앤다
        if (!isValid(map)) {
          map[x][y] |= 8; // 8을 더한다
          map[x][y + 1] |= 4; // 4를 더한다
        }
      } else {
        // 보
        map[x][y] &= 13;
        map[x + 1][y] &= 14;
        if (!isValid(map)) {
          map[x][y] |= 2;
          map[x + 1][y] |= 1;
        }
      }
    }
  }
  console.log(map.map((el) => el.map((el) => el.toString(2))));
  const ans = [];
  for (let x = 0; x <= n; x++) {
    for (let y = 0; y <= n; y++) {
      if (map[x][y] & 8) {
        ans.push([x, y, 0]);
      }
      if (map[x][y] & 2) {
        ans.push([x, y, 1]);
      }
    }
  }
  return ans;
}

console.log(
  solution(5, [
    [1, 0, 0, 1],
    [1, 1, 1, 1],
    [2, 1, 0, 1],
    [2, 2, 1, 1],
    [5, 0, 0, 1],
    [5, 1, 0, 1],
    [4, 2, 1, 1],
    [3, 2, 1, 1],
  ])
);
