function solution(absolutes, signs) {
  return signs.reduce(
    (sum, sign, idx) => (sum += sign ? absolutes[idx] : absolutes[idx] * -1),
    0
  );
}
