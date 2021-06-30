function solution(s) {
  let [ans1, ans2] = [0, 0];
  while (s !== "1") {
    ans1++;
    let s1 = s.replace(/0/g, "");
    ans2 += s.length - s1.length;
    s = lengthToBinary(s1.length);
  }
  return [ans1, ans2];
}

function lengthToBinary(num) {
  let result = "";
  while (num >= 1) {
    result += num % 2;
    num = parseInt(num / 2, 10);
  }
  return result;
}
