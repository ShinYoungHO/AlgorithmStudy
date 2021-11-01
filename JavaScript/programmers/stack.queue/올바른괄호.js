function solution(s) {
  const stack = [];
  for (let i = 0; i < s.length; i++) {
    if (stack[stack.length - 1] === "(") {
      if (s[i] === "(") {
        stack.push(s[i]);
      } else {
        stack.pop();
      }
    } else {
      stack.push(s[i]);
    }
  }
  return !stack.length;
}
