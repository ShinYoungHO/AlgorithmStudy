// const gcd = (a, b) => (a % b === 0 ? b : gcd(b, a % b));
// console.log(getGCD(10, 5));

function solution(l, r) {
  let sum = 0;
  for (let i = l; i <= r; i++) {
    if (i ** (1 / 2) % 1 === 0) {
      sum -= i;
    } else {
      sum += i;
    }
  }
  return sum;
}
