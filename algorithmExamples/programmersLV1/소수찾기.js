/////////////에라토스테네스의 체.///////////////////
//제곱근까지의 수를 2배수를 거르고 3배수 부터 제곱근의 배수를 모두 거르면 걸러지지 않은 수들이 
//소수가 된다.


function solution(n) {
    let answer = Array(n+1).fill(1);
    answer[0] = 0;
    answer[1] = 0;
    for(let i = 2; i <=n; i++){
        let isItDeci = true;
        if(answer[i] !== 0){//1인 경우에만 검사를 하겠다.
            for(let k = 2; k <= Math.sqrt(answer[i]);k++){//answer[i]가 소수인지 확인
                if(answer[i] ===2 || answer[i] === 3){break} // 2나 3이면 무조건 소수
                if(answer[i] % k === 0){//나누어 떨어지는 경우가 나오면 소수가 아니므로 소수가 아니다라고 선언 후 break
                    isItDeci = false;
                    break;
                }
            }
            if(isItDeci === true){//소수가 나온 경우 소수를 제외한 배수들에 모두 0을 넣어서 검사 최소화
                 for(let j = 2*i; j <= n; j+=i){
                     answer[j] = 0;
                 } 
            }          
        }        
    }
    return answer.join('').match(/1/ig).length;    
}
//////////////////////////////////////////////////////////////////////////////???
//////////////////////////////////////////////////////////////////////////////???
//////////////////////////////////////////////////////////////////////////////???
//////////////////////////////////////////////////////////////////////////////???


function solution(n) {
    var answer = 0;
    for(let i = 2; i <=n; i++){
        let isItDecimal = true
        for(let j = parseInt(Math.sqrt(i)); j < i; j++){
            if(i === 2 || i === 3){
                continue;
            }else if(i%j === 0){ 
                isItDecimal = false;
                break;
            }
        }
        if(isItDecimal){answer++}
    }
    return answer;
}
///////////////////시간초과/////////////////
//1부터 n의 숫자i 들에 대해서 루트i부터 순차적으로 찾아갔지만 시간초과.

function solution(n) {
    var answer = 0;
    for(let i = 2; i <=n; i++){
        let isItDecimal = true
        for(let j = 2; j <= parseInt(Math.sqrt(i)); j++){
            if(i === 2 || i === 3){
                continue;
            }else if(i%j === 0){ 
                isItDecimal = false;
                break;
            }
        }
        if(isItDecimal){answer++}
    }
    return answer;
}
/////////////////짝수도 제외해봤지만, 짝수인지 판별하는 연산때문에 오히려 효울성은 저하되고,
/////////////////루트i부터 진행하는 대신 2부터 루트i까지 검사함을 통해 검사갯수를 줄였다.
/////////////////개선되긴 했지만 여전히 효율성에서 통과하지 못한다.



