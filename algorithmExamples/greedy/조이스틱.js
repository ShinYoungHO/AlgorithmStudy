function solution(name) {
  //4,7,11
  let strArr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split(""); //26
  let nameSplit = name.split("");
  const tempResult = nameSplit.reduce((acc, cur) => {
    const nIdx = strArr.indexOf(cur);
    if (nIdx < 13) {
      return (acc += 1 + nIdx);
    } else {
      return (acc += 1 + 26 - nIdx);
    }
  }, -1);

  let aLen = 0;
  let aLenMax = 0;
  let idx = 0;
  for (let i = 0; i < nameSplit.length - 1; i++) {
    if (nameSplit[i] === "A") {
      aLen++;
      if (nameSplit[i + 1] !== "A" && aLen > aLenMax) {
        idx = i;
        aLenMax = aLen;
      }
    }
  }
  if (idx - aLenMax > aLenMax) return tempResult;
  else return tempResult - aLenMax;
}
// 2nd Try
// 반례 :: "BBAAABAAAAAAAAAAAABA"

function solution(name) {
  let strArr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split(""); //26
  let nameSplit = name.split("");
  const nameTemp = nameSplit.map((el) => {
    const aIdx = strArr.indexOf(el);
    if (aIdx >= 13) {
      return 26 - aIdx;
    }
    return aIdx;
  });
  let result = 0;
  for (let i = 0; i < nameTemp.length; i++) {
    result += nameTemp[i];
    let deltaAIdx = 1;

    while (nameTemp[i + deltaAIdx] === 0 && i + deltaAIdx < nameTemp.length) {
      deltaAIdx++;
    }
    if (i + deltaAIdx >= nameTemp.length) break;
    if (i + deltaAIdx < nameTemp.length && deltaAIdx === 1) {
      result += deltaAIdx;
    } else if (i < deltaAIdx) {
      result += i + 1;
      i += deltaAIdx - 1;
    }
  }
  return result;
}

// 3rd try ㅠㅠ

function solution(name) {
  let strArr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split(""); //26
  let nameSplit = name.split("");
  let ans = 0;
  const nameTemp = nameSplit.map((el) => {
    const aIdx = strArr.indexOf(el);
    if (aIdx >= 13) {
      ans += 26 - aIdx;
      return 26 - aIdx;
    } else {
      ans += aIdx;
      return aIdx;
    }
  });
  let move = nameTemp.length - 1;

  for (let i = 1; i < nameTemp.length; i++) {
    if (nameTemp[i] === 0) {
      let nextIdx;
      for (nextIdx = i + 1; nextIdx < nameTemp.length; nextIdx++) {
        if (nameTemp[nextIdx] !== 0) break;
      }
      const left = i - 1;
      const right = nameTemp.length - nextIdx;
      move = Math.min(move, left * 2 + right);
      i = nextIdx;
    }
  }
  return ans + move;
}

console.log(solution("BABAAAAAB"));
