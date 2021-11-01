function solution(s) {
    let sLen = s.length;
    if(sLen !== 4 && sLen !== 6){return false}
    let answer = s.match(/[A-Z]/gi)
    let ansLen = answer && s.match(/[A-Z]/gi).length 
    return answer ? false : (answer & ansLen) === 0 ? true : false;
}

