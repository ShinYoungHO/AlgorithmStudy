function solution(numbers, target) {
  let result = 0;
  const inF = (sum, idx) => {
    if (idx === numbers.length && sum === target) {
      result++;
      return;
    } else if (idx >= numbers.length) return;
    inF(sum + numbers[idx], idx + 1);
    inF(sum - numbers[idx], idx + 1);
  };
  inF(0, 0);
  return result;
}

solution([1, 1, 1, 1, 1], 3);
