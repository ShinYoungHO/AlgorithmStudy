function solution(seoul) {
    let answer = null;
    for(let i = 0; i < seoul.length; i++){
        if(seoul[i]==='Kim'){
            answer = i
            break;
        }
    }
    return `김서방은 ${answer}에 있다`;
    // return `김서방은 ${seoul.indexOf("Kim")}에 있다`
}