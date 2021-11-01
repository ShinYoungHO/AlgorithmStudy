function solution(gems) {
  const gemsLength = gems.length;
  const gemSet = new Set(gems);
  const resultCartSize = gemSet.size;

  const gemCart = new Map();
  gemCart.set(gems[0], 1);

  let [s, e] = [0, 0];
  let [l, r] = [0, gems.length - 1];

  // 모든 보석을 담고있을 때 까지 e++;
  // 모든 보석을 가지고 있는 경우,
  //    현재 s, e 가 최소범위인지 확인하고 할당
  //    이후 해당 범위는 소모했으므로 필요없고, 다음 경우를 위해 s++
  // 구간별 보석종류 및 갯수를 Map에 저장

  while (e < gemsLength && s <= e) {
    if (gemCart.size < resultCartSize) {
      e++;
      gemCart.set(gems[e], 1 + (gemCart.get(gems[e]) || 0));
    } else {
      if (r - l > e - s) {
        l = s;
        r = e;
      }
      let curGem = gemCart.get(gems[s]);
      if (curGem >= 2) {
        gemCart.set(gems[s], curGem - 1);
      } else if (curGem) {
        gemCart.delete(gems[s]);
      }
      s++;
    }
  }
  return [l + 1, r + 1];
}
