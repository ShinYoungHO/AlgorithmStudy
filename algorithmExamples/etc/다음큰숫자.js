function solution(n) {
  let n2 = [];
  while (n >= 1) {
    n2.push(n % 2);
    n = parseInt(n / 2, 10);
  }
  n2.push(0);
  console.log(n2);
  let isMet1 = false;

  for (let i = 0; i < n2.length; i++) {
    if (n2[i] === 1) {
      isMet1 = true;
    }
    if (isMet1) {
      if (n2[i] === 0) {
        n2[i] = 1;
        if (n2[i - 1]) {
          n2[i - 1] = 0;
        }
        break;
      }
    }
  }
  console.log(n2);
  return n2.reduce((acc, cur, idx) => {
    if (cur === 1) {
      acc += 2 ** idx;
    }
    return acc;
  }, 0);
}

console.log(solution(78));
