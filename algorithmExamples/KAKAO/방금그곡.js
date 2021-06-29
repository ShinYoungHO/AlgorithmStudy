function solution(m, musicinfos) {
  let result = [0, "(None)"];
  const myMel = melodySplitter(m);
  for (let i = 0; i < musicinfos.length; i++) {
    let sp = musicinfos[i].split(",");
    const time =
      (parseInt(sp[1].split(":")[0]) - parseInt(sp[0].split(":")[0])) * 60 +
      parseInt(sp[1].split(":")[1]) -
      parseInt(sp[0].split(":")[1]);
    const melody = melodySplitter(sp[3]);
    let count = 0;
    let isStart = false;
    let isValid = false;
    let j = 0;

    while (!isValid && j <= time) {
      let curMel = melody[j % melody.length];
      if (count > myMel.length - 1) {
        isValid = true;
        break;
      }
      if (curMel === myMel[count]) {
        isStart = true;
      }
      if (isStart) {
        if (myMel[count] === curMel) {
          count++;
        } else {
          j -= count;
          count = 0;
          isStart = false;
        }
      }
      j++;
    }
    if (isValid) {
      result = result[0] < time ? [time, sp[2]] : result;
    }
  }
  return result[1];
}
function melodySplitter(melody) {
  let result = [];
  for (let i = 0; i < melody.length; i++) {
    if (melody[i + 1] === "#") {
      result.push(melody[i] + "#");
      i++;
    } else {
      result.push(melody[i]);
    }
  }
  return result;
}
