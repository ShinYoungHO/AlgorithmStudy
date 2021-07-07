// 1st try

function solution(info, query) {
  const infoMap = {
    cpp: [],
    java: [],
    python: [],
    frontend: [],
    backend: [],
    junior: [],
    senior: [],
    chicken: [],
    pizza: [],
  };

  const result = [];

  const infos = info.map((el, idx) => {
    const [lang, role, carrer, food, score] = el.split(" ");
    [lang, role, carrer, food].forEach((el) => {
      if (infoMap[el]) infoMap[el].push(idx);
    });
    return [lang, role, carrer, food, score];
  });
  for (let i = 0; i < query.length; i++) {
    const subQueries = query[i].replace(/ and/g, "").split(" ");
    let candidates =
      subQueries[0] !== "-"
        ? infoMap[subQueries[0]]
        : new Array(info.length).fill(0).map((el, idx) => el + idx);
    for (let i = 1; i < subQueries.length - 1; i++) {
      if (subQueries[i] === "-") continue;
      candidates = candidates.filter(
        (el) => infoMap[subQueries[i]].indexOf(el) !== -1
      );
    }
    if (subQueries[4] !== "-") {
      candidates = candidates.reduce((acc, cur) => {
        if (parseInt(infos[cur][4]) >= parseInt(subQueries[4], 10)) {
          acc.push(1);
        }
        return acc;
      }, []);
      result.push(candidates.length);
    } else {
      result.push(candidates.length);
    }
  }
  return result;
}

// 2nd 런타임에러 절반 + 시간초과

function solution(info, query) {
  const options = [
    ["cpp", "java", "python"],
    ["frontend", "backend"],
    ["junior", "senior"],
    ["chicken", "pizza"],
  ];
  const infoMap = makeInfoMap(options, 0);
  const result = [];

  const infos = info.map((el, idx) => {
    const [lang, role, carrer, food, score] = el.split(" ");
    infoMap[lang][role][carrer][food].push(idx);
    return [lang, role, carrer, food, score];
  });
  for (let i = 0; i < query.length; i++) {
    const subQueries = query[i].replace(/ and/g, "").split(" ");
    const candidates = searchMap(options, subQueries, infoMap, 0);
    let value = candidates.filter(
      (el) => parseInt(infos[el][4], 10) >= parseInt(subQueries[4], 10)
    ).length;
    result.push(value);
  }
  return result;
}

function searchMap(options, subQueries, map, idx) {
  if (idx >= 4) {
    if (map.length) {
      return map;
    } else {
      return;
    }
  }
  if (subQueries[idx] !== "-") {
    let temp = searchMap(options, subQueries, map[subQueries[idx]], idx + 1);
    if (temp && temp.length) {
      return [...temp];
    }
  } else {
    let result = [];
    for (let i = 0; i < options[idx].length; i++) {
      let temp = searchMap(options, subQueries, map[options[idx][i]], idx + 1);
      if (temp && temp.length) {
        result.push(...temp);
      }
    }
    return result;
  }
}

function makeInfoMap(options, idx) {
  if (idx >= 4) return [];
  const result = {};
  for (let i = 0; i < options[idx].length; i++) {
    result[options[idx][i]] = makeInfoMap(options, idx + 1);
  }
  return result;
}

//////////////////////////////333333333333333333

// 첫번째 풀이 대비 효율성 증가
// 하지만 여전히 안풀림...
function solution(info, query) {
  const options = [
    ["cpp", "java", "python"],
    ["frontend", "backend"],
    ["junior", "senior"],
    ["chicken", "pizza"],
  ];
  const infoMap = makeInfoMap(options, 0);
  const result = [];

  const infos = info.map((el, idx) => {
    const [lang, role, carrer, food, score] = el.split(" ");
    infoMap[lang][role][carrer][food].push(idx);
    return [lang, role, carrer, food, score];
  });
  for (let i = 0; i < query.length; i++) {
    const subQueries = query[i].replace(/ and/g, "").split(" ");
    const acceptQueries = getQueries(subQueries, options);

    let count = 0;
    for (let j = 0; j < acceptQueries.length; j++) {
      const candidates = searchMap(options, acceptQueries[j], infoMap, 0);
      let value = candidates.filter(
        (el) => parseInt(infos[el][4], 10) >= parseInt(subQueries[4], 10)
      ).length;
      count += value;
    }
    result.push(count);
  }
  return result;
}

function getQueries(subQueries, options) {
  const inF = (idx, arr2) => {
    if (idx >= 4) {
      return arr2;
    }
    if (subQueries[idx] !== "-") {
      arr2.forEach((arr) => arr.push(subQueries[idx]));
      return inF(idx + 1, arr2);
    } else {
      arr2 = arr2.reduce((arr2, arr) => {
        for (let i = 0; i < options[idx].length; i++) {
          const temp = arr.slice();
          temp.push(options[idx][i]);
          arr2.push(temp);
        }
        return arr2;
      }, []);
      return inF(idx + 1, arr2);
    }
  };
  const result = inF(0, [[]]);
  return result;
}

function searchMap(options, subQueries, map, idx) {
  if (idx >= 4) return map;
  return [...searchMap(options, subQueries, map[subQueries[idx]], idx + 1)];
}

function makeInfoMap(options, idx) {
  if (idx >= 4) return [];
  const result = {};
  for (let i = 0, len = options[idx].length; i < len; i++) {
    result[options[idx][i]] = makeInfoMap(options, idx + 1);
  }
  return result;
}

//// 4th

function solution(info, query) {
  const result = [];
  const options = [
    ["cpp", "java", "python", "-"],
    ["frontend", "backend", "-"],
    ["junior", "senior", "-"],
    ["chicken", "pizza", "-"],
  ];
  const map = makeInfoMap(options);

  for (let i = 0; i < info.length; i++) {
    const infoArr = info[i].split(" ");
    const [lang, role, carrer, food, score] = infoArr;
    let str = `${lang} and ${role} and ${carrer} and ${food}`;
    let numScore = Number(score);

    saveScore(str, numScore, infoArr, map);

    for (let k in map) {
      map[k].sort((a, b) => a - b);
    }
  }
  for (let i = 0; i < query.length; i++) {
    const matchIdx = query[i].match(/ [0-9]/).index;
    const scores = map[query[i].slice(0, matchIdx)];
    const score = Number(query[i].slice(matchIdx + 1));
    let left = 0,
      right = scores.length;
    console.log(scores, score);
    while (left < right) {
      let mid = Math.floor((left + right) / 2);

      if (scores[mid] >= score) {
        right = mid;
      } else if (scores[mid] < score) {
        left = mid + 1;
      }
    }
    result.push(scores.length - left);
  }
  return result;
}

function saveScore(str, score, infoArr, map) {
  const inF = (n, str) => {
    if (n >= 4) {
      map[str].push(score);
      return;
    }
    inF(n + 1, str);
    inF(n + 1, str.replace(infoArr[n], "-"));
  };
  inF(0, str);
}

function makeInfoMap(options) {
  const map = {};
  const inF = (idx, subStr) => {
    if (idx === 4) {
      map[subStr] = [];
      return;
    }
    for (let i = 0; i < options[idx].length; i++) {
      inF(idx + 1, subStr + " and " + options[idx][i]);
    }
  };
  for (let i = 0; i < options[0].length; i++) {
    inF(1, options[0][i]);
  }
  return map;
}

///////////////////////////////////////////////////
///////////////////////////////////////////////////
///////////////////////////////////////////////////
///////////////////////////////////////////////////
///////////////////////////////////////////////////
///////////////////////////////////////////////////
///////////////////////////////////////////////////

function solution(info, query) {
  let map = {};
  let result = [];
  for (let i = 0; i < info.length; i++) {
    let infos = info[i].split(" ");
    combination(infos, 0, map, Number(infos.pop()));
  }
  for (let k in map) {
    map[k].sort((a, b) => a - b);
  }
  for (let j = 0; j < query.length; j++) {
    let queries = query[j].replace(/ and/g, "").split(" ");
    let score = Number(queries.pop());
    let n = binarySearch(score, queries, map);
    result.push(n);
  }
  return result;
}

function combination(infos, idx, map, score) {
  let val = infos.join("");

  if (map[val]) {
    map[val].push(score);
  } else {
    map[val] = [score];
  }

  for (let i = idx; i < infos.length; i++) {
    let duplicate = infos.slice();
    duplicate[i] = "-";
    combination(duplicate, i + 1, map, score);
  }
}

function binarySearch(score, queries, map) {
  let scoreArr = map[queries.join("")];
  if (scoreArr && scoreArr.length) {
    let l = 0;
    let r = scoreArr.length;

    while (l < r) {
      let m = parseInt((l + r) / 2, 10);
      if (scoreArr[m] >= score) {
        r = m;
      } else if (scoreArr[m] < score) {
        l = m + 1;
      }
    }
    return scoreArr.length - l;
  } else {
    return 0;
  }
}
