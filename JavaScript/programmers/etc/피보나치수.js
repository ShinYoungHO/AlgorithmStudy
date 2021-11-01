function solution(n) {
  let [p, q] = [0, 1];
  if (n <= 0) return p;
  else if (n <= 1) return q;
  else {
    while (n > 1) {
      [p, q] = [q, (p + q) % 1234567];
      n--;
    }
  }
  return q;
}
