function solution(numbers) {
  const numArr = findNumber(numbers);
  let count = 0;
  for (let i = 0; i < numArr.length; i++) {
    if (numArr[i]) {
      if (numArr[i === 2 || numArr[i] === 3]) {
        count++;
        continue;
      }
      let isDeci = true;
      for (let j = 2; j <= numArr[i] ** (1 / 2); j++) {
        if (numArr[i] % j === 0) {
          isDeci = false;
          break;
        }
      }
      if (isDeci) {
        count++;
      }
    }
  }
  return count;
}

function findNumber(numbers) {
  const arr = numbers.split("");
  let deciCount = 0;
  let numset = new Set();
  const inF = (arr, count, substr, cache) => {
    if (count >= arr.length) {
      if (!substr || substr === "1") return;
      return numset.add(substr);
    }
    arr.forEach((number, idx) => {
      if (cache[idx] === false) {
        const newCache = cache.slice();
        newCache[idx] = true;
        inF(arr, count + 1, substr, newCache);
        if (substr !== "" || number !== "0") {
          inF(arr, count + 1, substr + number, newCache);
        }
      }
    });
  };

  inF(arr, 0, "", Array(numbers.length).fill(false));
  return [...numset].map(Number);
}
