function solution(routes) {
  routes.sort((a, b) => a[0] - b[0]);

  let ans = 1;
  let standard = routes[0][1];

  for (let i = 1; i < routes.length; i++) {
    const route = routes[i];
    if (route[0] <= standard) {
      standard = Math.min(route[1], standard);
    } else {
      standard = route[1];
      ans++;
    }
  }
  return ans;
}

console.log(
  solution([
    [-14, -5],
    [-18, -13],
    [-20, 15],
    [-5, -3],
  ])
);
