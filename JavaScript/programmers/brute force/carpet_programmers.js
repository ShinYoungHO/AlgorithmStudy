function solution(brown, yellow) {
  const div = [];
  let result = null;
  for (let i = 1; i <= yellow ** (1 / 2); i++) {
    if (yellow % i === 0) {
      if (i === yellow) {
        div.push(i);
        continue;
      }
      div.push(i, yellow / i);
    }
  }
  div.forEach((el1) => {
    div.forEach((el2) => {
      if (2 * el1 + 2 * el2 + 4 === brown) {
        result = [el1 + 2, el2 + 2].sort((a, b) => b - a);
      }
    });
  });
  return result;
}
