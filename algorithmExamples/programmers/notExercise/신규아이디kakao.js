// 아이디의 길이는 3자 이상 15자 이하여야 합니다.
// 아이디는 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.) 문자만 사용할 수 있습니다.
// 단, 마침표(.)는 처음과 끝에 사용할 수 없으며 또한 연속으로 사용할 수 없습니다.

function solution(new_id) {
    //1단계 : 소문자로 바꾸기
    let step1 = new_id.toLowerCase();
    
    //2단계 : 조건에 맞지 않는 특수문자 제거하기
    let step2 = step1.split("").filter( el => {
        if(el.match( /[A-Z]/ig ) || el.match(/[._-]/ig) || el.match(/[0-9]/ig)){ return true } 
    })
    
    //3단계 : 연속된 '.' 하나로 바꾸기 
    let lenFor3 = step2.length;
    let step3 = []
    step3.push(step2[0]);
    for(let idx = 1; idx < lenFor3; idx++){
        if(step2[idx] === '.' && step2[idx-1] === '.'){ continue }
        else{step3.push(step2[idx])}
    }
    
    //4단계 : 처음이나 마지막 '.' 제거
    let lenFor4 = step3.length;
    if(step3[lenFor4-1]==='.'){step3.pop()}
    if(step3[0]==='.'){step3.shift()}
    let step4 = step3;
    //5단계 : 빈문자열인 경우 'a'로 치환
    if(step4.length === 0){ step4.push('a') }
    let step5 = step4;
    
    //6단계 : 16자 이상이면 15자 까지만 남기고 모두 삭제
    step5.splice(15);
    if(step5[step5.length-1]==='.'){step5.pop()}
    let step6 = step5;
    
    //7단계 : id의 길이가 2자 이하라면 마지막 문자를 반복해서 붙여줌.
    if(step6.length <= 2){
        while(step6.length <= 2){ step6.push(step6[step6.length-1]) }
    }
    return step6.join('');
    
}


// function solution(new_id) {
//     const answer = new_id
//         .toLowerCase() // 1
//         .replace(/[^\w-_.]/g, '') // 2
//         .replace(/\.+/g, '.') // 3
//         .replace(/^\.|\.$/g, '') // 4
//         .replace(/^$/, 'a') // 5
//         .slice(0, 15).replace(/\.$/, ''); // 6
//     const len = answer.length;
//     return len > 2 ? answer : answer + answer.charAt(len - 1).repeat(3 - len);
// }