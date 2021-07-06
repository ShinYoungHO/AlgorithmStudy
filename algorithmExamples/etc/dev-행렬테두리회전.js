function solution(rows, columns, queries) {
  const map = [];
  const result = [];
  for (let i = 0; i <= rows; i++) {
    map.push(
      Array(columns)
        .fill(0)
        .map((n, idx) => n + idx + 1 + i * columns)
    );
  }
  for (let i = 0; i < queries.length; i++) {
    result.push(
      searchAndReplace(
        queries[i].map((el) => el - 1),
        map
      )
    );
  }
  return result;
}

function searchAndReplace(query, map) {
  const [x1, y1, x2, y2] = query;
  let min = map[x1][y1];
  let prev = min;
  for (let i = y1 + 1; i <= y2; i++) {
    let tempNum = map[x1][i];
    map[x1][i] = prev;
    prev = tempNum;
    min = min > tempNum ? tempNum : min;
  }
  for (let i = x1 + 1; i <= x2; i++) {
    let tempNum = map[i][y2];
    map[i][y2] = prev;
    prev = tempNum;
    min = min > tempNum ? tempNum : min;
  }
  for (let i = y2 - 1; i >= y1; i--) {
    let tempNum = map[x2][i];
    map[x2][i] = prev;
    prev = tempNum;
    min = min > tempNum ? tempNum : min;
  }
  for (let i = x2 - 1; i >= x1; i--) {
    let tempNum = map[i][y1];
    map[i][y1] = prev;
    prev = tempNum;
    min = min > tempNum ? tempNum : min;
  }
  return min;
}
