function solution(dartResult) {
  const splitted = split(dartResult);
  const exp = [];
  const map = {
    S: 1,
    D: 2,
    T: 3,
  };
  for (let i = 0; i < splitted.length; i++) {
    if (splitted[i].match(/[\*#]/)) {
      if (splitted[i] === "*") {
        exp[exp.length - 1] = 2 * exp[exp.length - 1];
        if (exp.length - 2 >= 0) {
          exp[exp.length - 2] = 2 * exp[exp.length - 2];
        }
      } else if (splitted[i] === "#") {
        exp[exp.length - 1] = -1 * exp[exp.length - 1];
      }
    } else {
      const [score, option] = splitted[i].split(" ");
      exp.push(parseInt(score, 10) ** map[option]);
    }
  }
  return exp.reduce((sum, val) => (sum += val), 0);
}

function split(str) {
  let subStr = "";
  const result = [];

  for (let i = 0; i < str.length; i++) {
    if (str[i].match(/[A-Z]/)) {
      result.push(`${subStr} ${str[i]}`);
      subStr = "";
    } else if (str[i].match(/[0-9]/)) {
      subStr += str[i];
    } else {
      result.push(str[i]);
    }
  }
  return result;
}
