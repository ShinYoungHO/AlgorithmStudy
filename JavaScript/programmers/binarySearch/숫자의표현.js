function solution(n) {
  let result = 0;
  for (let i = 1; i <= n; i++) {
    const small = (i * (i - 1)) / 2;
    let l = i;
    let r = n;
    while (l <= r) {
      const m = parseInt((l + r) / 2, 10);
      const large = (m * (m + 1)) / 2;
      // console.log('l m r', l, m, r)
      if (large - small > n) {
        r = m - 1;
      } else if (large - small < n) {
        l = m + 1;
      } else {
        result++;
        break;
      }
    }
  }
  return result;
}
