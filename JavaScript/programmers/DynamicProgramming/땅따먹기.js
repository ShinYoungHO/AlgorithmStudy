function solution(land) {
  for (let i = 1; i < land.length; i++) {
    for (let j = 0; j < 4; j++) {
      let max = 0;
      land[i - 1].forEach((el, idx) => {
        if (idx !== j) {
          max = el > max ? el : max;
        }
      });
      land[i][j] += max;
    }
  }
  return Math.max(...land[land.length - 1]);
}
