function solution(files) {
  return files
    .map((el, idx) => {
      return {
        idx,
        val: el,
      };
    })
    .sort((a, b) => {
      const result = mySort(split(a.val), split(b.val));
      return result === 0 ? a.idx - b.idx : result;
    })
    .map((el) => el.val);
}

function mySort(A, B) {
  // head : 사전순, 대소문자 X
  // nubmer : 번호순
  if (A[0] > B[0]) {
    return 1;
  } else if (A[0] < B[0]) {
    return -1;
  } else {
    return parseInt(A[1], 10) - parseInt(B[1], 10);
  }
}

function split(str) {
  let l, r;
  for (let i = 0; i < str.length; i++) {
    if (!l && str[i].match(/[0-9]/g)) {
      l = i;
      continue;
    }
    if ((l && str[i].match(/[^0-9]/g)) || i >= l + 5) {
      r = i;
      break;
    }
  }
  return [
    str.slice(0, l).toLowerCase(),
    str.slice(l, r),
    str.slice(r, str.length),
  ];
}
