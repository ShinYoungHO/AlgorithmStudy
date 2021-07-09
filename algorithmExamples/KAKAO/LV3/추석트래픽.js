function solution(lines) {
  let reqTime = [];
  let resTime = [];
  let eventTime = [];
  for (let i = 0; i < lines.length; i++) {
    let [_, time, sec] = lines[i].split(" ");
    let end = time.split(":").reduce((time, val, idx) => {
      time += (Number(val) * 3600000) / 60 ** idx;
      return time;
    }, 0);
    let start = end - Number(sec.replace("s", "")) * 1000 + 1;
    resTime.push(end);
    eventTime.push(end, start);
    reqTime.push(start);
  }
  eventTime.sort((a, b) => a - b);
  let max = 0;
  for (let i = 0; i < eventTime.length; i++) {
    let tMax = 0;
    for (let j = 0; j < reqTime.length; j++) {
      let [l, r] = [eventTime[i], eventTime[i] + 999];
      if (l <= resTime[j] && reqTime[j] <= r) {
        //if (reqTime[j] >= l && reqTime[j] < r) || (resTime[j] >= l && resTime[j] < r) || (reqTime[j] <= l && resTime[j] >= r)
        tMax++;
      }
    }
    if (max < tMax) max = tMax;
  }
  console.log(max);
  return max;
}

solution([
  "2016-09-15 20:59:57.421 0.351s",
  "2016-09-15 20:59:58.233 1.181s",
  "2016-09-15 20:59:58.299 0.8s",
  "2016-09-15 20:59:58.688 1.041s",
  "2016-09-15 20:59:59.591 1.412s",
  "2016-09-15 21:00:00.464 1.466s",
  "2016-09-15 21:00:00.741 1.581s",
  "2016-09-15 21:00:00.748 2.31s",
  "2016-09-15 21:00:00.966 0.381s",
  "2016-09-15 21:00:02.066 2.62s",
]);
