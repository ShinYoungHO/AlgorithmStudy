function solution(orders, course) {
  const map = {};
  course.forEach((num) => {
    orders.forEach((order) => {
      const tempres = getCourses(order, num);
      tempres.length &&
        tempres.forEach((el) => {
          saveToMap(map, el);
        });
    });
  });
  let result = Array.from(
    Array(course[course.length - 1] + 1),
    () => new Array()
  );
  for (let key in map) {
    if (map[key] === 1) continue;
    if (!result[key.length].length) {
      result[key.length].push(map[key], key);
    } else {
      if (result[key.length][0] === map[key]) {
        result[key.length].push(key);
      } else if (result[key.length][0] < map[key]) {
        result[key.length] = [map[key], key];
      }
    }
  }
  console.log(map);
  return result
    .reduce((acc, cur) => {
      if (cur.length) {
        return [...acc, ...cur.slice(1, cur.length)];
      } else {
        return acc;
      }
    }, [])
    .sort();
}

function saveToMap(map, element) {
  if (map[element]) {
    map[element]++;
  } else {
    map[element] = 1;
  }
}

function getCourses(orderString, number) {
  const result = [];
  const inF = (orderString, num, strArr) => {
    if (strArr.length === number) {
      strArr.length && result.push(strArr.sort().join(""));
      return;
    } else if (num && num >= orderString.length) {
      return;
    }
    inF(orderString, num + 1, strArr);
    let slice = strArr.slice();
    slice.push(orderString[num]);
    inF(orderString, num + 1, slice);
  };
  inF(orderString, 0, []);
  return result;
}

// better
// better
// better

function solution(orders, course) {
  const ordered = {};
  const candidates = {};
  const maxNum = Array(10 + 1).fill(0);
  const createSet = (arr, start, len, foods) => {
    console.log(foods);
    if (len === 0) {
      ordered[foods] = (ordered[foods] || 0) + 1;
      if (ordered[foods] > 1) candidates[foods] = ordered[foods];
      maxNum[foods.length] = Math.max(maxNum[foods.length], ordered[foods]);
      return;
    }

    for (let i = start; i < arr.length; i++) {
      createSet(arr, i + 1, len - 1, foods + arr[i]);
    }
  };
  orders.forEach((od) => {
    // arr.sort는 기본적으로 사전식 배열
    const sorted = od.split("").sort();
    // 주문한 음식을 사전순으로 배열해서 문자열을 만든다.
    // course에 있는 길이만 만들면 된다.
    course.forEach((len) => {
      createSet(sorted, 0, len, "");
    });
  });

  const launched = Object.keys(candidates).filter(
    (food) => maxNum[food.length] === candidates[food]
  );

  return launched.sort();
}

console.log(solution(["XYZ", "XWY", "WXA"], [2, 3, 4], [2, 3, 4]));
