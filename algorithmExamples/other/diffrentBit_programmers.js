function solution(numbers) {
  return numbers.map((numArr) => {
    let bit = dif2(numArr);
    let result = 0;
    let count = 0;
    let changed = false;
    for (let i = bit.length - 1; i >= 0; i--, count++) {
      if (!changed) {
        if (bit[i] === 0) {
          if (bit[i + 1] === 1) {
            result -= 2 ** (count - 1);
          }
          bit[i] = 1;
          changed = true;
        }
      }
      result += 2 ** count * bit[i];
    }
    return result;
  });
}

function dif2(num) {
  let ans = [];
  while (num >= 1) {
    ans.unshift(num % 2);
    num = parseInt(num / 2, 10);
  }
  ans.unshift(0);
  return ans;
}

// better

function solution(numbers) {
  return numbers.map((number) => {
    let t = "0" + number.toString(2);
    for (let j = t.length - 1; j >= 0; j--) {
      if (t[j] === "0") {
        if (j !== t.length - 1) {
          t = t.substring(0, j) + "10" + t.substring(j + 2, t.length);
        } else {
          t = t.substring(0, j) + "1" + t.substring(j + 1, t.length);
        }
        break;
      }
    }
    return parseInt(t, 2);
  });
}
