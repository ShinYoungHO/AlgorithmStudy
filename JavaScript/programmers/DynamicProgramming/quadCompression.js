//
//  dp 적용 전
//
function solution(matrix) {
  const inf = (matrixArr) => {
    let count = 0;

    const reduced = matrixArr.reduce((mtxarr, matrix) => {
      if (matrix.length === 1) {
        count++;
        return [...mtxarr, matrix];
      }
      const findResult = findAll(matrix);
      if (findResult !== false) {
        return [...mtxarr, [[findResult]]];
      } else {
        return [...mtxarr, ...divide4(matrix)];
      }
    }, []);
    if (count !== reduced.length) {
      return inf(reduced);
    } else {
      return matrixArr;
    }
  };
  return inf([matrix]).reduce(
    (acc, cur) => {
      return cur[0][0] === 0 ? [acc[0] + 1, acc[1]] : [acc[0], acc[1] + 1];
    },
    [0, 0]
  );
}

function findAll(matrix) {
  if (matrix.length === 1) return matrix;
  const result = [...new Set(matrix.flat())];
  if (result.length >= 2) return false;
  else return matrix[0][0];
}

function divide4(matrix) {
  const result = [];
  for (let i = 0; i < 4; i++) {
    const tempResult = [];
    const div = Math.floor(i / 2);
    for (
      let j = (matrix.length / 2) * div;
      j < (matrix.length / 2) * (div + 1);
      j++
    ) {
      const sliceArr = matrix[j].slice(
        ((i % 2) * matrix.length) / 2,
        (((i % 2) + 1) * matrix.length) / 2
      );
      tempResult.push(sliceArr);
    }
    result.push(tempResult);
  }
  return result;
}

// 두개 시간초과

//-----------------------------DP---------------------------//

/**
 * 각 쿼드맵 사이즈 별 배열 선언
 * matrix 돌면서 해당 사이즈의 올바른 위치에 숫자 count
 * 큰사이즈부터 depth를 내려가며 count된 숫자를 확인하고 0이거나 length와 같으면 result []배열에 count+1
 * 아닌 경우 해당 요소에 대응하는 네개의 그 다음 depth 확인
 *
 * memo 하기
 * 중앙 정하고 중앙보다 크고 작은지 확인
 */

function solution2(matrix) {
  const memos = makeMemos(matrix);
  const len = matrix.length;
  const allLen = matrix.flat().length;
  const n = memos.length;
  // memosing
  for (let i = 0; i < len; i++) {
    for (let j = 0; j < len; j++) {
      // i,j 의 숫자를 각 쿼드 메모 사이즈별 위치에 저장
      // 각 쿼드메모 사이즈별 위치찾는 함수 :: 길이, n에 따른 4사분면 위치
      const quadCoord = coord44(i, j, len, n);
      memos[0][0] += matrix[i][j];
      let idx = 0;
      quadCoord.forEach((quad, qidx) => {
        memos[qidx + 1][idx + quad] += matrix[i][j];
        idx = (idx + quad) * 4;
      });
    }
  }

  // memos탐색
  /**
   * 0번 인덱스 allLen 인지 확인
   * 아니면 다음 depth 배열을 확인
   * 다음 depth 배열의 각 인덱스 요소가 0 또는 allLen / 4 인지 확인
   * 맞으면 0또는 1로 치환 아니면 재귀로 들어가서 그다음 depth 확인
   * ... 지속
   * 마지막에 도착하면 모두 리턴
   */
  console.log(memos);
  const a = searchMemo(memos, 0, 0, allLen);
  console.log(a.toString().replaceAll(",", ""));
  return a.reduce(
    (acc, cur) => {
      return cur === 0 ? [acc[0] + 1, acc[1]] : [acc[0], acc[1] + 1];
    },
    [0, 0]
  );
}

function searchMemo(memos, memoIdx, startIdx, allLen) {
  if (memos[memoIdx].length === allLen) {
    return memos[memoIdx].slice(startIdx, startIdx + 4);
  }
  return memos[memoIdx]
    .slice(startIdx, startIdx + 4)
    .map((el, idx) => {
      if (el === 0) {
        return 0;
      } else if (el === memos[memos.length - 1].length / 4 ** memoIdx) {
        return 1;
      } else {
        return searchMemo(memos, memoIdx + 1, startIdx * 4 + idx * 4, allLen);
      }
    })
    .flat();
}

function makeMemos(matrix) {
  const memo = [];
  let len = matrix.length;
  while (len >= 1) {
    memo.unshift(Array(len ** 2).fill(0));
    len /= 2;
  }
  return memo;
}

function coord44(x, y, len, n) {
  const result = [];

  let [xs, xe] = [0, len - 1]; // idx
  let [ys, ye] = [0, len - 1]; // idx

  for (let i = 0; i < n; i++) {
    const x2 = (xe - xs + 1) / 2;
    const y2 = (ye - ys + 1) / 2;
    if (x2 === 1 && y2 === 1) {
      const res = x === xs ? (y === ys ? 0 : 1) : y === ys ? 2 : 3;
      result.push(res);
      return result;
    }
    if (x < xs + x2) {
      if (y < ys + y2) {
        // 1사
        xe -= x2;
        ye -= y2;
        result.push(0);
      } else {
        // 2사
        xe -= x2;
        ys += y2;
        result.push(1);
      }
    } else {
      if (y < ys + y2) {
        // 3사
        xs += x2;
        ye -= y2;
        result.push(2);
      } else {
        // 4사
        xs += x2;
        ys += y2;
        result.push(3);
      }
    }
  }
}

const a = [
  [1, 1, 1, 1, 1, 1, 1, 1],
  [0, 1, 1, 1, 1, 1, 1, 1],
  [0, 0, 0, 0, 1, 1, 1, 1],
  [0, 1, 0, 0, 1, 1, 1, 1],
  [0, 0, 0, 0, 0, 0, 1, 1],
  [0, 0, 0, 0, 0, 0, 0, 1],
  [0, 0, 0, 0, 1, 0, 0, 1],
  [0, 0, 0, 0, 1, 1, 1, 1],
];
solution2(a);
