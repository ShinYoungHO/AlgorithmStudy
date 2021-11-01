function solution(n, words) {
  const cache = { [words[0]]: true };
  let round = 1;
  let prev = words[0];
  for (let i = 1; i < words.length; i++) {
    if (i % n === 0) {
      round++;
    }
    if (cache[words[i]] || words[i][0] !== prev[prev.length - 1]) {
      return [(i % n) + 1, round];
    } else {
      cache[words[i]] = true;
      prev = words[i];
    }
  }
  return [0, 0];
}
