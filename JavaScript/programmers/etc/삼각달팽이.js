// fail
function solution(n) {
  const tri = [];
  for (let i = 1; i <= n; i++) {
    tri.push(Array(i).fill(0));
  }
  let cur = 1;
  const repeat = Math.ceil(n / 3);
  for (let j = 0; j < repeat; j++) {
    // 앞
    for (let k = j * 2; k < n - j; k++) {
      tri[k][j] = cur;
      console.dir("first", tri);
      cur++;
    }
    // 밑
    for (let k = j + 1; k <= n - 3 * j; k++) {
      tri[n - j - 1][k] = cur;
      console.dir("second", tri);
      cur++;
    }
    // 오른쪽
    for (let k = n - j - 2; k >= j + 1; k--) {
      tri[k][k - j] = cur;
      console.dir("third", tri);
      cur++;
    }
  }
  return tri.flat();
}

// 성공!!
function solution(n) {
  const tri = [];
  for (let i = 1; i <= n; i++) {
    tri.push(Array(i).fill(0));
  }
  let cur = 2;
  tri[0][0] = 1;
  const repeat = Math.ceil(n / 3);
  let [x, y] = [0, 0];
  for (let j = 0; j < repeat; j++) {
    while (tri[x + 1] && tri[x + 1][y] === 0) {
      [x, y] = [x + 1, y];
      tri[x][y] = cur;
      console.dir(tri);
      cur++;
    }
    while (tri[x][y + 1] === 0) {
      [x, y] = [x, y + 1];
      tri[x][y] = cur;
      console.dir(tri);
      cur++;
    }
    while (tri[x - 1] && tri[x - 1][tri[x - 1].length - 1 - j] === 0) {
      [x, y] = [x - 1, tri[x - 1].length - 1 - j];
      tri[x][y] = cur;
      console.dir(tri);
      cur++;
    }
  }
  return tri.flat();
}

// better
function solution(n) {
  let a = Array(n)
    .fill()
    .map((_, i) => Array(i + 1).fill());
  let row = -1;
  let col = 0;
  let fill = 0;
  for (let i = n; i > 0; i -= 3) {
    a[++row][col] = ++fill;
    for (let j = 0; j < i - 1; j++) a[++row][col] = ++fill;
    for (let j = 0; j < i - 1; j++) a[row][++col] = ++fill;
    for (let j = 0; j < i - 2; j++) a[--row][--col] = ++fill;
  }
  return a.flat();
}

solution(6);
