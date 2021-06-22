function solution(number, k) {
  const stack = [Number(number[0])];
  for (let i = 1; i < number.length; i++) {
    const nextVal = Number(number[i]);
    while (k > 0 && stack[stack.length - 1] < Number(number[i])) {
      stack.pop();
      k--;
    }
    stack.push(nextVal);
  }
  return stack.reduce((acc, cur) => {
    if (acc.length >= number.length - k) return acc;
    return acc + cur.toString();
  }, "");
}
