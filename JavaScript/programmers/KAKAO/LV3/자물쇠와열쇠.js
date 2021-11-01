function solution(key, lock) {
  const lockLen = lock.length;
  const keyLen = key.length;
  const lockMapLen = lockLen + 2 * (key.length - 1);
  const lockMap = Array.from(new Array(lockMapLen), () =>
    new Array(lockMapLen).fill(0)
  );

  for (let i = 0; i < lockLen; i++) {
    for (let j = 0; j < lockLen; j++) {
      lockMap[i + keyLen - 1][j + keyLen - 1] = lock[i][j];
    }
  }
  for (let c = 0; c < 4; c++) {
    for (let i = 0; i < lockMapLen; i++) {
      for (let j = 0; j < lockMapLen; j++) {
        let result = true;
        for (let m = 0; m < lockLen; m++) {
          for (let n = 0; n < lockLen; n++) {
            let [r, c] = [m + keyLen - 1 - i, n + keyLen - 1 - j];
            if (r >= 0 && r < keyLen && c >= 0 && c < keyLen) {
              if (key[r][c] + lock[m][n] !== 1) {
                result = false;
                break;
              }
            } else if (lock[m][n] !== 1) {
              result = false;
              break;
            }
          }
          if (!result) break;
        }
        if (result) return true;
      }
    }
    key = rotate(key);
  }
  return false;
}

function rotate(key) {
  const len = key.length;
  let newKey = Array.from(new Array(len), () => new Array(len).fill(0));
  for (let i = 0; i < len; i++) {
    for (let j = 0; j < len; j++) {
      if (key[i][j] === 1) {
        newKey[j][len - i - 1] = key[i][j];
      }
    }
  }
  return newKey;
}
