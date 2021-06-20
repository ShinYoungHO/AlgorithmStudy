function solution(s) {
  return s
    .split(" ")
    .map((str) => {
      const Tojaden = str.toLowerCase().split("");
      Tojaden[0] = Tojaden[0] && Tojaden[0].toUpperCase();
      return Tojaden.join("");
    })
    .join(" ");
}
