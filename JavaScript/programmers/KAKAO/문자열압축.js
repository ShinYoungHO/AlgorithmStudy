function solution(s) {
  let ans = s.length;
  for (let i = 1; i < s.length; i++) {
    const compressedStr = compress(splitByNumber(i, s));
    ans = ans > compressedStr.length ? compressedStr.length : ans;
  }
  return ans;
}

function compress(arr) {
  let result = "";
  let count = 0;
  for (let i = 0; i < arr.length; i++) {
    let curStr = arr[i];
    if (arr[i + 1] === curStr) {
      count++;
      continue;
    } else {
      result += `${count + 1 === 1 ? "" : count + 1}${arr[i]}`;
      count = 0;
    }
  }
  return result;
}

function splitByNumber(n, str) {
  let result = [];
  let substr = "";

  for (let i = 0; i < str.length; i++) {
    substr += str[i];
    if (i % n === n - 1) {
      result.push(substr);
      substr = "";
    }
    if (substr.length && i === str.length - 1) {
      result.push(substr);
    }
  }
  return result;
}
