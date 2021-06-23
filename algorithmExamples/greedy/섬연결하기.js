function solution(n, costs) {
  costs.sort((a, b) => a[2] - b[2]);
  let cycleTable = Array(n)
    .fill(0)
    .map((undefined, idx) => idx);

  const union = (el, arr) => {
    if (arr[el] === el) return el;
    return (arr[el] = union(arr[el], arr));
  };

  let result = 0;
  costs.forEach((el) => {
    let [x, y, val] = el;
    let xParent = union(x, cycleTable);
    let yParent = union(y, cycleTable);
    if (xParent !== yParent) {
      cycleTable = cycleTable.map((el) => {
        if (el === cycleTable[y]) return xParent;
        return el;
      });
      result += val;
    }
  });
  return result;
}
