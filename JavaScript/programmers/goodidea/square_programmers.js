// 최대 공약수를 구해서
// 가장 작은 사각형 구한 후
// 해당 사각형의 겹치는 부분 갯수를 구하고 전체 갯수에서 빼는 방법.
function solution(w, h) {
  let gcf = gcfSolve(w, h);
  let xPart = h / gcf;
  let yPart = w / gcf;
  if (yPart > xPart) {
    [xPart, yPart] = [yPart, xPart];
  }
  const result = w * h - getPartRes(xPart, yPart) * gcf;
  return result;
}

function gcfSolve(n, m) {
  if (n < m) {
    return gcfSolve(m, n);
  }
  let rest = n % m;
  if (rest === 0) {
    return m;
  }
  return gcfSolve(m, rest);
}

function getPartRes(r, c) {
  let startY = 0;
  let count = 0;
  for (let i = 1; i <= c; i++) {
    // 부동소수점 오차...
    let endY = parseInt((r / c) * i * 1000000000, 10) / 1000000000;
    count += Math.ceil(endY) - Math.floor(startY);
    startY = endY;
  }
  return count;
}

// 다른 풀이

/**
 * 기울기를 이용한 방법
 *
 */

function solution(w, h) {
  const slope = h / w;
  let result = 0;

  for (let i = 1; i <= w; i++) {
    result += Math.ceil(slope * i);
  }

  return (h * w - result) * 2;
}

/**
 * 최대공약수와 다른관점
 *
 * 가로 관점에서 대각선과 만나는 갯수는 가로의 길이와 같다.
 * 세로의 경우도 마찬가지이다.
 * 첫 사각형에서 겹치므로 -1
 *
 */

function solution(w, h) {
  const gcd = (a, b) => {
    return b === 0 ? a : gcd(b, a % b);
  };

  return w * h - (w + h - gcd(w, h));
}

function solution(w, h) {
  const slope = h / w;
  return w * h - Math.ceil(slope * w) - Math.ceil(slope) + 1;
}
