function solution(people, limit) {
  people.sort((a, b) => a - b);
  let ans = 0;
  let leftIdx = 0;
  let rightIdx = people.length - 1;

  while (leftIdx < rightIdx) {
    if (people[leftIdx] + people[rightIdx] <= limit) {
      leftIdx++;
      rightIdx--;
      ans++;
    } else {
      rightIdx--;
    }
  }

  return people.length - ans;
}
