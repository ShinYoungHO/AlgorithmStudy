function solution(s) {
  const sArr = s.split(" ");
  let [max, min] = [parseInt(sArr[0], 10), parseInt(sArr[0], 10)];

  for (let i = 1; i < sArr.length; i++) {
    const num = parseInt(sArr[i], 10);
    if (num > max) {
      max = num;
    } else {
      min = num < min ? num : min;
    }
  }
  return `${min} ${max}`;
}
