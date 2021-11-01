// 내 로직에 맞추기 위해 계속 데이터를 추가해 나가는 습관을 고치자.
// 다른 좋은 방법이 더 많을 거다
function solution(tickets) {
  const map = {};
  tickets.forEach((ticket, idx) => {
    const [from, to] = ticket;
    if (map[from]) {
      map[from].ports = [...map[from].ports, to].sort();
      map[from][to] = idx;
    } else {
      map[from] = {};
      map[from].ports = [to];
      map[from][to] = idx;
      map[from].set = new Set();
    }
  });
  const cache = Array(tickets.length).fill(false);
  const DFS = (count, from, resArr, cache, map) => {
    if (count === cache.length) {
      return resArr;
    }

    for (let i = 0; i < map[from].ports.length; i++) {
      if (map[from].set.has(map[from].ports[i])) {
        continue;
      }
      const nextIdx = map[from][map[from].ports[i]];
      let isDeep = false;
      if (
        (!cache[nextIdx] && map[tickets[nextIdx][1]]) ||
        count + 1 === cache.length
      ) {
        isDeep = true;
        cache[nextIdx] = true;
        map[from].set.add(map[from].ports[i]);

        resArr.push(tickets[nextIdx][1]);
        let tempResult = DFS(
          count + 1,
          tickets[nextIdx][1],
          resArr,
          cache,
          map
        );
        if (tempResult) {
          return tempResult;
        }
      }
      if (isDeep) {
        resArr.pop();
        map[from].set.delete(map[from].ports[i]);
        cache[nextIdx] = false;
      }
    }
  };
  return DFS(0, "ICN", ["ICN"], cache, map);
}

// 고친 코드

function solution(tickets) {
  const map = {};
  tickets.forEach((ticket, idx) => {
    const [from, to] = ticket;
    if (map[from]) {
      map[from] = [...map[from], [to, idx, false]].sort();
    } else {
      map[from] = [[to, idx, false]];
    }
  });
  const cache = Array(tickets.length).fill(false);
  const DFS = (count, from, resArr, cache, map) => {
    if (count === cache.length) {
      return resArr;
    }

    for (let i = 0; i < map[from].length; i++) {
      const [nextPort, nextIdx, visited] = map[from][i];
      if (visited) {
        continue;
      }
      let isDeep = false;
      if (
        (!cache[nextIdx] && map[tickets[nextIdx][1]]) ||
        count + 1 === cache.length
      ) {
        isDeep = true;
        cache[nextIdx] = true;
        map[from][i][2] = true;
        resArr.push(tickets[nextIdx][1]);
        let tempResult = DFS(
          count + 1,
          tickets[nextIdx][1],
          resArr,
          cache,
          map
        );
        if (tempResult) {
          return tempResult;
        }
      }
      if (isDeep) {
        resArr.pop();
        map[from][i][2] = false;
        cache[nextIdx] = false;
      }
    }
  };
  return DFS(0, "ICN", ["ICN"], cache, map);
}
