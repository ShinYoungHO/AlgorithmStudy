function solution(msg) {
  let ans = [];
  const alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  const map = {};
  for (let i = 0; i < alphabet.length; i++) {
    map[alphabet[i]] = i + 1;
  }
  map.size = 26;
  let cur = "";
  for (let i = 0; i < msg.length; i++) {
    cur += msg[i];
    if (msg[i + 1] && !map[cur + msg[i + 1]]) {
      map.size++;
      map[cur + msg[i + 1]] = map.size;
      ans.push(map[cur]);
      cur = "";
    } else if (i + 1 === msg.length) {
      map.size++;
      map[cur + msg[i + 1]] = map.size;
      ans.push(map[cur]);
    }
  }
  return ans;
}
solution("KAKAO");
