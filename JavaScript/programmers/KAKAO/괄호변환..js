function solution(p) {
  // 균형잡힌 괄호문자열 : ( , ) 갯수 같음
  const recur = (p) => {
    if (p === "") return "";
    let [u, v] = splitUV(p);
    if (checkCorrect(u)) {
      return u + recur(v);
    } else {
      return `(${recur(v)})${convert(u.slice(1, u.length - 1))}`;
    }
  };
  return recur(p);
}
function splitUV(p) {
  if (p === "") return "";
  let count = 0;
  let idx = null;
  for (let i = 0; i < p.length; i++) {
    if (p[i] === "(") {
      count++;
    } else {
      count--;
    }
    if (count === 0) {
      idx = i;
      break;
    }
  }
  return [p.slice(0, idx + 1), p.slice(idx + 1, p.length)];
}

function checkCorrect(p) {
  const stack = [];
  for (let i = 0; i < p.length; i++) {
    if (p[i] === ")") {
      if (stack[stack.length - 1] === "(") {
        stack.pop();
      } else {
        stack.push(p[i]);
      }
    } else {
      stack.push(p[i]);
    }
  }
  return stack.length ? false : true;
}

function convert(p) {
  let result = "";
  for (let i = 0; i < p.length; i++) {
    if (p[i] === "(") {
      result += ")";
    } else if (p[i] === ")") {
      result += "(";
    }
  }
  return result;
}
