function solution(n, a, b) {
  let count = 0;
  let match = 0;
  let finished = false;
  let [x1, x2] = [1, n];
  while (n >= 1) {
    const mid = (x1 + x2) / 2;
    count++;
    if (!finished) match++;
    if (a < mid && b > mid) {
      finished = true;
    } else if (a > mid && b < mid) {
      finished = true;
    } else if (a < mid) {
      x2 = Math.floor(mid);
    } else {
      x1 = Math.ceil(mid);
    }
    n /= 2;
  }
  return count - match;
}
