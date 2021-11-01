function solution(arr) {
  let result = arr[0];
  for (let i = 1; i < arr.length; i++) {
    result = (result * arr[i]) / gcd(...[result, arr[i]].sort((a, b) => b - a));
  }
  return result;
}

function gcd(a, b) {
  return a % b === 0 ? b : gcd(b, a % b);
}
