function solution(begin, target, words) {
  // DFS
  const MAX = Number.MAX_SAFE_INTEGER;
  let minResult = MAX;
  const DFS = (str, count, cache, words) => {
    if (str === target) {
      minResult = count < minResult ? count : minResult;
      return true;
    }
    for (let idx = 0; idx < words.length; idx++) {
      if (!cache[idx]) {
        if (checkValid(str, words[idx])) {
          cache[idx] = true;
          DFS(words[idx], count + 1, cache, words);
          cache[idx] = false;
        }
      }
    }
    return false;
  };
  DFS(begin, 0, Array(words.length).fill(false), words);
  return minResult === MAX ? 0 : minResult;
}

function checkValid(str, word) {
  let notMatchCount = 0;
  for (let i = 0; i < str.length; i++) {
    if (str[i] !== word[i]) notMatchCount++;
  }
  return notMatchCount === 1 ? true : false;
}
solution("hit", "cog", ["hot", "dot", "dog", "lot", "log", "cog"]);
