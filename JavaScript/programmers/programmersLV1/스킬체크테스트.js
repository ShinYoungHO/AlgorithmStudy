
// ###1

// * 문제 설명
// * 단어 s의 가운데 글자를 반환하는 함수, solution을 만들어 보세요. 단어의 길이가 짝수라면 가운데 두글자를 반환하면 됩니다.

// **제한사항**<br>
// 1. s는 길이가 1 이상, 100이하인 스트링입니다.


function solution(s) {
    var answer = '';
    if(s.length % 2 === 0){
        answer = s[s.length/2-1] + s[s.length/2]
    }else{
        answer = s[Math.floor(s.length/2)]
    }
    return answer;
}


// ###2

// * 문제 설명
// * 길이가 같은 두 1차원 정수 배열 a, b가 매개변수로 주어집니다. a와 b의 내적을 return 하도록 solution 함수를 완성해주세요.<br>

// 이때, a와 b의 내적은 a[0]*b[0] + a[1]*b[1] + ... + a[n-1]*b[n-1] 입니다. (n은 a, b의 길이)

// **제한사항**

// 1. a, b의 길이는 1 이상 1,000 이하입니다.
// 2. a, b의 모든 수는 -1,000 이상 1,000 이하입니다.

function solution(a, b) {
    var answer = 1234567890;
    
    if(a.length === b.length){
        let result = 0;
        for(let i = 0; i < a.length; i++){
            result += a[i] * b[i];
        }
        return result;
    }else{
        return answer;
    }
}
