function solution(dirs) {
  const map = Array.from(Array(11), () =>
    Array.from(Array(11), () => Array(4).fill(false))
  );
  const convert = {
    U: [0, -1, 0, "D"],
    D: [1, 1, 0, "U"],
    R: [2, 0, 1, "L"],
    L: [3, 0, -1, "R"],
  };
  let result = 0;
  let [r, c] = [5, 5];
  for (let i = 0; i < dirs.length; i++) {
    const [idx, dr, dc, reverse] = convert[dirs[i]];
    const [nr, nc] = [r + dr, c + dc];
    if (nr >= 0 && nr <= 10 && nc >= 0 && nc <= 10) {
      if (!map[r][c][idx]) {
        result++;
        map[r][c][idx] = true;
        map[nr][nc][convert[reverse][0]] = true;
        [r, c] = [nr, nc];
      } else {
        [r, c] = [nr, nc];
      }
    }
  }
  return result;
}
