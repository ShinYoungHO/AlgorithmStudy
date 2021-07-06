function solution(s) {
  const tupArr = getSets(s).sort((a, b) => a.size - b.size);
  let set = new Set();

  tupArr.forEach((tup) => {
    set = new Set([...set, ...tup]);
  });

  return [...set];
}
function getSets(str) {
  // const splitTupels = str.split("},{");
  // return splitTupels.map(
  //   (el) =>
  //     new Set(
  //       el
  //         .replace(/[^0-9\,]/g, "")
  //         .split(",")
  //         .map((subStr) => Number(subStr))
  //     )
  // );

  const sets = [];
  let subNums = [];
  let subNumStr = "";
  let isOpen = false;

  for (let i = 0; i < str.length - 1; i++) {
    const subStr = str[i];
    if (subStr === "{") {
      isOpen = true;
    } else if (subStr === "}") {
      if (subNumStr) {
        subNums.push(parseInt(subNumStr, 10));
        subNumStr = "";
      }
      isOpen = false;
      sets.push(new Set(subNums));
      subNums = [];
      subNumStr = "";
    }
    if (isOpen) {
      if (subStr.match(/[0-9]/)) {
        subNumStr += subStr;
      } else if (subStr === ",") {
        subNums.push(parseInt(subNumStr, 10));
        subNumStr = "";
      }
    } else {
      continue;
    }
  }
  return sets;
}

const solution = (s) =>
  s
    .match(/(\d+,)*\d+/g)
    .map((s) => s.split(",").map((n) => +n))
    .sort((a, b) => a.length - b.length)
    .reduce((a, s) => [...a, ...s.filter((n) => a.indexOf(n) === -1)], []);
