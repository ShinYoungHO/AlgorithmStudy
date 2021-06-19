// 11~19번줄 오류,
//
function solution(numbers) {
  return numbers
    .map((el) => el.toString().split(""))
    .sort((a, b) => sortCallback(a, b))
    .flat()
    .join("");
}
function sortCallback(arg1, arg2, n = 0) {
  if (!arg1[n] || !arg2[n]) {
    if (!arg1[n]) {
      return arg1[0] / 1 === arg2[n] / 1
        ? arg2[n] / 1 - arg1[0] / 1
        : arg1[0] / 1 - arg2[n] / 1;
    } else {
      return arg2[0] / 1 === arg1[n] / 1
        ? arg1[n] / 1 - arg2[0] / 1
        : arg2[0] / 1 - arg1[n] / 1;
    }
  } else if (arg1[n] / 1 > arg2[n] / 1) {
    return -1;
  } else if (arg1[n] / 1 < arg2[n] / 1) {
    return 1;
  } else if (arg1[n] / 1 === arg2[n] / 1) {
    return sortCallback(arg1, arg2, n + 1);
  }
}
