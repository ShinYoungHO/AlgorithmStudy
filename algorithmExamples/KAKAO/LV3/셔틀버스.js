function solution(n, t, m, timetable) {
  const lastBusTime = 540 + (n - 1) * t;
  const arrivalTimes = timetable
    .reduce((r, v) => {
      const arrival = v
        .split(":")
        .reduce((time, str, idx) => time + parseInt(str) * 60 ** (1 - idx), 0);
      if (arrival <= lastBusTime) {
        r.push(arrival);
      }
      return r;
    }, [])
    .sort((a, b) => a - b);
  let result = 0;

  for (let i = 1; i <= n; i++) {
    let busTime = 540 + (i - 1) * t;
    if (busTime !== lastBusTime) {
      let shiftCount = 0;
      while (shiftCount < m) {
        if (arrivalTimes[0] <= busTime) {
          arrivalTimes.shift();
          shiftCount++;
        } else break;
      }
    } else {
      let max = 0;
      if (arrivalTimes.length < m) {
        result = busTime;
        break;
      }

      for (let i = 0; i < m; i++) {
        max = arrivalTimes[i] > max ? arrivalTimes[i] : max;
      }
      result = max - 1;
    }
  }

  return [parseInt(result / 60), result % 60]
    .map((v) => {
      let result = v.toString();
      while (result.length < 2) {
        result = "0" + result;
      }
      return result;
    })
    .join(":");
}
