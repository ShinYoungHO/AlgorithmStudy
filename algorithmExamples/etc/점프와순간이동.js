function solution(n) {
  let count = 0;
  while (n >= 1) {
    if (n % 2 === 1) {
      count++;
      n--;
    } else {
      n /= 2;
    }
  }
  return count;
}
