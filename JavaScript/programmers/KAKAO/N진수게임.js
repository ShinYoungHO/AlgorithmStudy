function solution(n, t, m, p) {
  let dictionary = {
    0: "0",
    1: "1",
    2: "2",
    3: "3",
    4: "4",
    5: "5",
    6: "6",
    7: "7",
    8: "8",
    9: "9",
    10: "A",
    11: "B",
    12: "C",
    13: "D",
    14: "E",
    15: "F",
  };
  let tubeOrder = "";
  let curNum = 0;
  let order = "";
  while (order.length < t * m) {
    let curOrder = tenToN(n, curNum, dictionary);
    order += curOrder;
    curNum++;
  }
  for (let i = 0; i < t; i++) {
    tubeOrder += order[m * i + p - 1];
  }
  return tubeOrder;
}

function tenToN(n, num, dictionary) {
  if (num === 0) return "0";
  let result = "";
  while (num >= 1) {
    result = dictionary[num % n] + result;
    num = parseInt(num / n, 10);
  }
  return result;
}
