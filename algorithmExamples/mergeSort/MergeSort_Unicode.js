// 문자열 s에 나타나는 문자를 큰것부터 작은 순으로 정렬해 새로운 문자열을 리턴하는 함수, solution을 완성해주세요.
// s는 영문 대소문자로만 구성되어 있으며, 대문자는 소문자보다 작은 것으로 간주합니다.

// 제한 사항
// str은 길이 1 이상인 문자열입니다.

function solution(s) {
    if(s.length < 2) return s;
    let halfIdx = parseInt(s.length/2);
    let left = s.slice(0,halfIdx);
    let right = s.slice(halfIdx,s.length);    
    return merge(solution(left) , solution(right));    
}
function merge(lStr,rStr){
    let result = "";
    while(lStr.length&&rStr.length){
      if(lStr[0] <= rStr[0]){
          result += rStr[0];
          rStr = rStr.slice(1,rStr.length);
      }else if(lStr[0] > rStr[0]){
          result += lStr[0];
          lStr = lStr.slice(1,lStr.length);
      }  
    }
    if(lStr.length){result+=lStr}
    else if(rStr.length){result+=rStr}
    return result;    
}