// 1st
function solution(jobs) {
  jobs.sort((a, b) => {
    if (a[0] > b[0]) return 1;
    else if (a[0] < b[0]) return -1;
    else return b[1] - a[1];
  });
  let result = 0;
  let time = 0;
  let pq = [];

  while (true) {
    while (jobs[0] && jobs[0][0] <= time) {
      enqueue(pq, jobs.shift());
    }
    let job = dequeue(pq);
    time += job[1];
    result += time - job[0];
    if (!pq.length && !jobs.length) break;
  }
  console.log(result);
  return result / jobs.length;
}

function enqueue(pq, el) {
  if (!pq.length) {
    pq.push(el);
  } else {
    pq.push(el);
    let cIdx = pq.length - 1;
    let pIdx = null;
    while (pIdx !== 0) {
      pIdx = parseInt((cIdx - 1) / 2);
      if (pq[pIdx][1] - pq[pIdx][0] < el[1] - el[0]) break;
      else {
        [pq[cIdx], pq[pIdx]] = [pq[pIdx], pq[cIdx]];
        cIdx = pIdx;
      }
    }
  }
}
function dequeue(pq) {
  if (pq.length === 1) return pq.pop();
  let result = pq[0];
  pq[0] = pq.pop();
  let pIdx = 0;
  while (true) {
    let leftIdx = pIdx * 2 + 1;
    if (
      pq[leftIdx] &&
      pq[pIdx][1] - pq[pIdx][0] < pq[leftIdx][1] - pq[leftIdx][0]
    ) {
      [pq[leftIdx], pq[pIdx]] = [pq[pIdx], pq[leftIdx]];
      pIdx = leftIdx;
      continue;
    }
    let rightIdx = pIdx * 2 + 2;
    if (
      pq[rightIdx] &&
      pq[pIdx][1] - pq[pIdx][0] < pq[rightIdx][1] - pq[rightIdx][0]
    ) {
      [pq[rightIdx], pq[pIdx]] = [pq[pIdx], pq[rightIdx]];
      pIdx = rightIdx;
      continue;
    }
    break;
  }
  return result;
}

////////////////////////////////////////////////
////////////////////////////////////////////////
////////////////////////////////////////////////
////////////////////////////////////////////////
////////////////////////////////////////////////
////////////////////////////////////////////////
////////////////////////////////////////////////

function solution(jobs) {
  let len = jobs.length;
  let ans = 0;
  let time = 0;
  let q = [];

  while (true) {
    let i = 0;
    while (true) {
      if (i >= jobs.length) break;
      if (jobs[i][0] <= time) q.push(jobs.splice(i, 1)[0]);
      else i += 1;
    }

    if (!q.length) {
      if (jobs.length === 0) break;
      else time += 1;
    } else {
      let minIndex = 0;
      for (let i = 1; i < q.length; i++) {
        if (q[minIndex][1] > q[i][1]) {
          minIndex = i;
        }
      }
      time += q[minIndex][1];
      ans += time - q[minIndex][0];
      q.splice(minIndex, 1);
    }
  }
  return parseInt(ans / len, 10);
}
