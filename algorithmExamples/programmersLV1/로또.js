function solution(lottos, win_nums) {
  let zeroC = 0;
  let matchC = 0;

  for (let i = 0; i < lottos.length; i++) {
    const val = lottos[i];
    if (val === 0) {
      zeroC += 1;
    } else {
      if (win_nums.indexOf(val) !== -1) {
        matchC += 1;
      }
    }
  }
  const [max, min] = [7 - matchC - zeroC, 7 - matchC];
  return [max === 7 ? 6 : max, min === 7 ? 6 : min];
}
