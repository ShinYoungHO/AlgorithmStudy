// 런타임 에러.. 22번줄 예상

function solution(records) {
  const map = {};
  let result = [];
  records.forEach((record, idx) => {
    let [action, uid, nickname] = record.split(" ");
    if (action === "Enter") {
      if (!map[uid]) {
        map[uid] = { nickname, indexes: [idx] };
        result.push(`${nickname}님이 들어왔습니다.`);
      } else {
        if (map[uid].nickname !== nickname) {
          map[uid].indexes.forEach((idx) => {
            result[idx] = result[idx].replace(map[uid].nickname, nickname);
          });
        }
        result.push(`${nickname}님이 들어왔습니다.`);
        map[uid].indexes.push(result.length - 1);
      }
    } else if (action === "Leave") {
      result.push(`${map[uid].nickname}님이 나갔습니다.`);
      map[uid].indexes.push(result.length - 1);
    } else if (action === "Change") {
      map[uid].indexes.forEach((idx) => {
        result[idx] = result[idx].replace(map[uid].nickname, nickname);
      });
    }
  });
  return result;
}

// 배열 돌면서 replace하는 대신 참조타입으로 바꿔보면

function solution(records) {
  const map = {};
  const result = [];
  for (let i = 0; i < records.length; i++) {
    let [action, uid, nickname] = records[i].split(" ");
    if (action === "Enter") {
      if (!map[uid]) {
        const arr = [nickname];
        map[uid] = arr;
      } else {
        map[uid][0] = nickname;
      }
      result.push([map[uid], "님이 들어왔습니다."]);
    } else if (action === "Leave") {
      result.push([map[uid], "님이 나갔습니다."]);
    } else if (action === "Change") {
      console.log(map);
      map[uid][0] = nickname;
      console.log(map);
    }
  }
  return result.map((arr) => {
    return `${arr[0]}${arr[1]}`;
  });
}

solution([
  "Enter uid1234 Muzi",
  "Enter uid4567 Prodo",
  "Leave uid1234",
  "Enter uid1234 Prodo",
  "Change uid4567 Ryan",
]);
