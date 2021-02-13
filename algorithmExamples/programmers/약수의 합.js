// 정수 n을 입력받아 n의 약수를 모두 더한 값을 리턴하는 함수, solution을 완성해주세요.

// 제한 사항
// n은 0 이상 3000이하인 정수입니다.
function solution(n) {
    let answer = 0;
    let isPower = Math.sqrt(n)
    for(let i = 1; i <= Math.sqrt(n); i++){
        if(n%i === 0){
            
            answer += i + n/i;
        }
    }
    return (isPower%1 !== 0) ? answer : answer - isPower
}

// return n <= a/2 ? b : solution(n, a + 1, b += n % a ? 0 : a);
// return n == 0 ? 0 : Array(n).fill(1).map((x,y) => x+y ).filter(a => n % a != 0 ? 0 : a).reduce((c,d) => c + d)