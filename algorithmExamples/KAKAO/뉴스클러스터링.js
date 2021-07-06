function solution(str1, str2) {
  const [split1, split2] = [
    splitWord(str1.toLowerCase()),
    splitWord(str2.toLowerCase()),
  ];
  const countMap = {};
  let total = 0;
  let overlap = 0;
  split1.forEach((el) => {
    if (!countMap[el]) {
      countMap[el] = { split1: 1, split2: 0 };
    } else {
      countMap[el].split1++;
    }
    total++;
  });
  split2.forEach((el) => {
    if (!countMap[el]) {
      total++;
    } else {
      if (countMap[el].split2 + 1 > countMap[el].split1) {
        countMap[el].split2++;
        total++;
      } else {
        countMap[el].split2++;
        overlap++;
      }
    }
  });
  return total === 0 ? 65536 : parseInt((overlap / total) * 65536, 10);
}

function splitWord(str) {
  const result = [];
  let prev = "";
  for (let i = 0; i < str.length; i++) {
    if (str[i].match(/[a-z]/g)) {
      if (!prev) {
        prev = str[i];
        continue;
      }
      result.push(prev + str[i]);
      prev = str[i];
    } else {
      prev = "";
    }
  }
  return result;
}
