function solution(s) {
  if (!s.length) return -1;
  let answer = 0;
  for (let j = 0; j < s.length; j++) {
    let result = 0;
    s = s.slice(1, s.length) + s[0];
    const stack = [];
    for (let i = 0; i < s.length; i++) {
      if (stack[stack.length - 1] === map(s[i])) stack.pop();
      else stack.push(s[i]);
      if (!stack.length) result++;
      if (i === s.length - 1 && stack.length) result = 0;
    }
    answer = answer < result ? result : answer;
  }
  return answer;
}

function map(str) {
  switch (str) {
    case ")":
      return "(";
    case "}":
      return "{";
    case "]":
      return "[";
    default:
      return false;
  }
}
