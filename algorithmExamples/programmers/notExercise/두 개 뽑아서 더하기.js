// 정수 배열 numbers가 주어집니다. numbers에서 서로 다른 인덱스에 있는 두 개의 수를 뽑아 더해서 만들 수 있는 모든 수를 배열에 오름차순으로 담아 return 하도록 solution 함수를 완성해주세요.

// 제한사항
// numbers의 길이는 2 이상 100 이하입니다.
// numbers의 모든 수는 0 이상 100 이하입니다.



////intital code 0.1 ~ 2.5ms
function solution(numbers) {
    let result = [];
    for(let i = 0; i < numbers.length-1; i++){
        for(let j = i+1;j<numbers.length; j++){
            result.push(numbers[i]+numbers[j])
        }
    }
    return result.filter((el,idx)=>{
        return result.indexOf(el)===idx
    }).sort((a,b)=>a-b)
}

///modified code 0.07 ~ 1.4ms
function solution(numbers) {
    let result = [];
    for(let i = 0; i < numbers.length-1; i++){
        for(let j = i+1;j<numbers.length; j++){
            let val = numbers[i]+numbers[j];
            if(result.indexOf(val)===-1){
                result.push(val);
            }
        }
    }
    return result.sort((a,b)=>a-b)
}

//for문을 돌때마다 포함되는지 확인하는 것이 시간이 오래 걸릴거라 생각했지만 그렇지 않다.
//길이가 길어진 상태에서 index를 확인하는 거랑 길이가 점점 길어지면서 index를 찾는 것 중에는 값을 찾아가며 
//중복값을 제거해주는게 더 빨라 필터를 나중에 쓰는 코드가 느린 것 같다.


//다른 코드들 0.09 ~ 0.8 ms
function solution(numbers) {
    const temp = []

    for (let i = 0; i < numbers.length; i++) {
        for (let j = i + 1; j < numbers.length; j++) {
            temp.push(numbers[i] + numbers[j])
        }
    }

    const answer = [...new Set(temp)]

    return answer.sort((a, b) => a - b)
}

//index를 확인하며 중복값을 제거하는 대신 Set이라는 내장 함수를 이용한 경우
