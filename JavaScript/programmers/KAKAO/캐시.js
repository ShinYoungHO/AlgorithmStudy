function solution(cacheSize, cities) {
  if (cacheSize === 0) return 5 * cities.length;
  let q = [];
  const cache = {};
  let answer = 0;
  for (let i = 0; i < cities.length; i++) {
    let cached = false;
    const curCity = cities[i].toLowerCase();
    if (!cache[curCity]) {
      cache[curCity] = 1;
      answer += 5;
    } else {
      q = reArrange(q, curCity);
      cached = true;
      answer += 1;
    }
    if (!cached) {
      if (q.length && q.length === cacheSize) {
        cache[q.shift()] -= 1;
      }
      q.push(curCity);
    }
  }
  return answer;
}

function reArrange(arr, city) {
  let result = arr.filter((el) => el !== city);
  result.push(city);
  return result;
}
