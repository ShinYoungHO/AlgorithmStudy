function solution(n) {
  let n2 = [];
  while (n >= 1) {
    n2.push(n % 2);
    n = parseInt(n / 2, 10);
  }
  n2.push(0);
  let met1 = false;
  let count = 0;
  for (let i = 0; i < n2.length; i++) {
    if (n2[i] === 1) {
      met1 = true;
    }
    if (met1) {
      if (n2[i] === 0) {
        n2[i] = 1;
        count--;
        for (let j = 0; j < i; j++) {
          if (count > 0) {
            n2[j] = 1;
            count--;
          } else {
            n2[j] = 0;
          }
        }
        break;
      } else {
        count++;
      }
    }
  }
  return n2.reduce((acc, cur, idx) => {
    if (cur === 1) {
      acc += 2 ** idx;
    }
    return acc;
  }, 0);
}

console.log(solution(78));
