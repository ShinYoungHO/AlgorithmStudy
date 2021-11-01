function solution(citations) {
  citations.sort((a, b) => b - a);
  let result = 0;
  while (result + 1 <= citations[result]) {
    result++;
  }
  return result;
}
