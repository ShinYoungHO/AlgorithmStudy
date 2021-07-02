function solution(N, stages) {
  // 스테이지 sort하고,
  stages.sort((a, b) => a - b);
  let result = Array(N + 2).fill(0);
  let users = stages.length;
  let count = 1;
  // 같은 stage의 갯수(count)를 센 뒤,
  // stage의 유저수는 해당 스테이지에 도달한 수이고,
  // count 는 못깬사람 수이다.
  // 다음 스테이지의 갯수를 세기 시작하면 못깬 유저는 이제 필요없으므로 users에 count만큼 빼줌.
  for (let idx = 0; idx < stages.length; idx++) {
    if (users === 0) {
      break;
    } else if (stages[idx + 1] === stages[idx]) {
      count++;
    } else if (stages[idx + 1] !== stages[idx]) {
      result[stages[idx]] = count / users;
      users -= count;
      count = 1;
    }
  }
  result[0] = 2;

  result.pop();
  let final = result
    .map((el, i) => {
      return { idx: i, val: el };
    })
    .sort((b, a) => {
      return a.val > b.val ? 1 : a.val < b.val ? -1 : b.idx - a.idx;
    })
    .map((el) => {
      return el.idx;
    });
  final.shift();
  return final;
}

console.log(solution(5, [2, 1, 2, 6, 2, 4, 3, 3]));
