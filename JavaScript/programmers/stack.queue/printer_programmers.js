function solution(priorities, location) {
  const order = [...priorities].sort((a, b) => b - a);
  let newPrior = priorities.map((el, idx) => {
    return {
      pr: el,
      idx,
    };
  });
  let count = 0;
  while (true) {
    const nextPrior = order.shift();
    let nextIdx = 0;
    while (newPrior[nextIdx].pr !== nextPrior) {
      nextIdx++;
    }
    count++;
    if (newPrior[nextIdx].idx === location) {
      return count;
    }
    newPrior = [
      ...newPrior.slice(nextIdx + 1, newPrior.length),
      ...newPrior.slice(0, nextIdx),
    ];
  }
}
