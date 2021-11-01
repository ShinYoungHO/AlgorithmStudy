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

  while (pq.array.length) {
    let a = pq.dequeue();
    let { value, idx } = a;
    map[idx].forEach((val, mapIdx) => {
      if (val && !visited[mapIdx] && val + value <= K) {
        pq.enqueue({ value: val + value, idx: mapIdx });
        result.push(mapIdx);
        visited[mapIdx] = true;
      }
    });
  }
  return result.length;
}

class PriorityQueue2 {
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
      this.size--;
      return this.array.pop();
    }
    const result = this.array[0];
    this.array[0] = this.array.pop();

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
    this.size--;
    return result;
  }
}

////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////

class PriorityQueue {
  constructor() {
    this.memory = [];
    this.length = 0;
  }

  push(newItem) {
    this.length++;

    let isAdded = false;

    for (let i = 0; i < this.memory.length; ++i) {
      if (this.memory[i].weight > newItem.weight) {
        this.memory.splice(i, 0, newItem);
        isAdded = true;
        break;
      }
    }

    if (!isAdded) this.memory.push(newItem);
  }

  pop() {
    this.length--;
    return this.memory.shift();
  }
}

function solution3(N, road, K) {
  const pq = new PriorityQueue();
  const adj = Array.from(Array(N + 1), () => new Array());
  const dist = [0, 0];

  for (let i = 0; i < N - 1; ++i) dist.push(Number.MAX_VALUE);
  road.map((info) => {
    let [from, to, weight] = info;
    adj[from].push({ to, weight });
    adj[to].push({ to: from, weight });
  });
  pq.push({
    to: 1,
    weight: 0,
  });
  // visited에 밸류 저장하는 방식
  // 중간에 K평가 안하고 저장만 해놓고 나중에 for문 돌면서 평가 :::

  while (pq.length) {
    let edge = pq.pop();
    adj[edge.to].map((next) => {
      if (dist[next.to] > dist[edge.to] + next.weight) {
        // 다음 밸류와 무게를 더해서
        // 더 적은 비용으로 갈 수 있는지 확인 후 적은 비용이면 치환
        dist[next.to] = dist[edge.to] + next.weight;
        pq.push(next);
      }
    });
  }
  // 각 마을별로 가는데 필요한 최소비용을 dist가 담고있음. K랑 비교해서 확인
  let answer = 0;
  for (let i = 1; i < N + 1; ++i) {
    if (dist[i] <= K) answer++;
  }

  return answer;
}

console.log(
  solution3(
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
