// 1차시도
// 53%
// 가중치가 달려있어 돌아서 가는 경우가 더 비용이 적은 경우가 생기므로 틀렸다.
function solution(N, road, K) {
  let map = [];
  let result = [];
  for (let i = 0; i <= N; i++) {
    map.push(Array(N + 1).fill(false));
  }
  let visited = Array(N + 1).fill(false);
  road.forEach((el) => {
    const [from, to, hour] = el;
    if (!map[from][to]) {
      map[from][to] = hour;
      map[to][from] = hour;
    } else if (hour < map[from][to]) {
      map[from][to] = hour;
      map[to][from] = hour;
    }
  });
  const q = [];
  map[1].forEach((el, idx) => {
    if (el !== false && el <= K) {
      q.push([el, idx]);
      result.push(idx);
      visited[idx] = true;
    }
  });
  while (q.length) {
    const [tot, cur] = q.shift();
    map[cur].forEach((el, idx) => {
      if (el && !visited[idx] && tot + el <= K) {
        q.push([tot + el, idx]);
        visited[idx] = true;
        result.push(idx);
      }
    });
  }
  return result;
}
// ----------------------------------------------------- //
// ------------------Priority Queue--------------------- //
// ----------------------------------------------------- //

// pq : 최소 queue
// enqueue :::
/**
 * pq 맨끝에 숫자 삽입.
 * 부모노드 숫자 확인 후 크먼 밑으로 내려줌.계속 반복
 * index도 줄여줌
 * 끝나면 넣어줄 item보다 작은 경우이므로 item을 해당 인덱스에 삽입해줌,
 */

function newSolution(N, road, K) {
  const pq = new PriorityQueue();
  let map = Array.from(Array(N + 1), () => Array(N + 1).fill(false));
  let visited = Array(N + 1).fill(false);
  let result = [];
  // road 이중배열에 간선 길이 할당
  road.forEach((el) => {
    const [from, to, hour] = el;
    if (!map[from][to]) {
      map[from][to] = hour;
      map[to][from] = hour;
    } else if (hour < map[from][to]) {
      map[from][to] = hour;
      map[to][from] = hour;
    }
  });
  pq.enqueue({ value: 0, idx: 1 });
  // visited[1] = true;
  // map[1].forEach((el, idx) => {
  //   if (el !== false && el <= K) {
  //     pq.enqueue({ value: el, idx });
  //     result.push(idx);
  //     visited[idx] = true;
  //   }
  // });
  while (pq.array.length) {
    let a = pq.dequeue();
    let { value, idx } = a;
    console.log(a);
    map[idx].forEach((val, mapIdx) => {
      if (val && !visited[mapIdx] && val + value <= K) {
        console.log({ value: val + value, idx: mapIdx });
        pq.enqueue({ value: val + value, idx: mapIdx });
        result.push(mapIdx);
        visited[mapIdx] = true;
      }
    });
    console.log("pqarr", pq.array);
  }
  return result;
}

class PriorityQueue {
  constructor() {
    this.array = [];
    this.size = 0;
  }
  enqueue(item) {
    let i = this.size;
    if (i === 0) {
      this.array.push(item);
      this.size += 1;
      return this.array;
    }
    while (this.array[Math.floor(i / 2)].value >= item.value && i != 1) {
      this.array[i] = this.array[Math.floor(i / 2)];
      i = Math.floor(i / 2);
    }
    this.array[i] = item;
    this.size += 1;
    return this.array;
  }
  dequeue() {
    if (this.size === 1) {
      return this.array.pop();
    }
    console.log(this.array);
    const result = this.array[0];
    this.array[0] = this.array.pop();
    console.log(this.array);

    let i = 0;
    while (true) {
      const [c1, c2] = [2 * i + 1, 2 * i + 2];
      if (c1 > this.size && c2 > this.size) {
        break;
      }
      if (this.array[c1]?.value < this.array[i]?.value) {
        [this.array[i], this.array[c1]] = [this.array[c1], this.array[i]];
        i = c1;
      } else if (this.array[c2]?.value < this.array[i]?.value) {
        [this.array[i], this.array[c2]] = [this.array[c2], this.array[i]];
        i = c2;
      } else {
        break;
      }
    }
    console.log("result", result);
    return result;
  }
}

console.log(
  newSolution(
    5,
    [
      [1, 2, 1],
      [2, 3, 3],
      [5, 2, 2],
      [1, 4, 2],
      [5, 3, 1],
      [5, 4, 2],
    ],
    3
  )
);
