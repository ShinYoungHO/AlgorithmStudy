function solution(n, computers) {
  let result = 0;
  const cache = Array(computers[0].length).fill(false);
  const DFS = (computers, idx) => {
    for (
      let computerIdx = 0;
      computerIdx < computers[idx].length;
      computerIdx++
    ) {
      if (!cache[computerIdx] && computers[idx][computerIdx] === 1) {
        cache[computerIdx] = true;
        DFS(computers, computerIdx);
      }
    }
  };
  for (let i = 0; i < cache.length; i++) {
    if (!cache[i]) {
      DFS(computers, i);
      result++;
    }
  }
  return result;
}
