function solution(numbers) {
  let tempRes = numbers.sort((a, b) => sortCallback(a, b));

  while (tempRes[0] === 0 && tempRes.length > 1) {
    tempRes.shift();
  }
  return tempRes.join("");
}
function sortCallback(arg1, arg2) {
  let [case1, case2] = [`${arg1}${arg2}`, `${arg2}${arg1}`];
  if (case1 < case2) {
    return 1;
  } else {
    return -1;
  }
}
