function solution(jobs) {
  // 미완
  let result = 0;
  let endTime = 0;
  const len = jobs.length
  let pq = [jobs.shift()];
  while (pq.length > 0) {
    // console.log(pq);
    //작업이 요청되는 시점, 작업의 소요시간
    const [start, time] = pq.shift();
    endTime += time;
    result += endTime - start;
    
    while (jobs.length > 0 && jobs[0][0] <= endTime) {
      // 도착한 요청들 time 순으로 오름차순 정렬
      let v = jobs.shift();
      // v[1] 기준으로 정렬
      pq.push(v)
      if(pq.length === 1) continue;
      for (let i = pq.length - 1; i > 0; i--) {
        // 작은걸 맨 앞에
        if(pq[i][1] < pq[i-1][1]){
          let temp = pq[i-1];
          pq[i-1] = pq[i];
          pq[i] = temp
        }
      }
    }
  }

  return parseInt(result / len);
}