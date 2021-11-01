// 문제 설명
// 수포자는 수학을 포기한 사람의 준말입니다. 수포자 삼인방은 모의고사에 수학 문제를 전부 찍으려 합니다. 수포자는 1번 문제부터 마지막 문제까지 다음과 같이 찍습니다.

// 1번 수포자가 찍는 방식: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
// 2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
// 3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...

// 1번 문제부터 마지막 문제까지의 정답이 순서대로 들은 배열 answers가 주어졌을 때, 가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 return 하도록 solution 함수를 작성해주세요.

// 제한 조건
// 시험은 최대 10,000 문제로 구성되어있습니다.
// 문제의 정답은 1, 2, 3, 4, 5중 하나입니다.
// 가장 높은 점수를 받은 사람이 여럿일 경우, return하는 값을 오름차순 정렬해주세요.

function solution(answers) {
    let result = Array(3).fill(0);
    let len = answers.length;
    let su1 = [1,2,3,4,5]
    let su2 = [2,1,2,3,2,4,2,5]
    let su3 = [3,3,1,1,2,2,4,4,5,5]
    for(let i = 0; i < len; i++){
        if(answers[i] === su1[i%5]){result[0]+=1};
        if(answers[i] === su2[i%8]){result[1]+=1};
        if(answers[i] === su3[i%10]){result[2]+=1};
        //answers 의 i번째 항목마다 1,2,3번의 order를 고려하여 result에 값을 추가.
    }
    let finalResult = [];
    result.reduce((acc,cur,idx)=> {
        if(cur>acc){
            acc=cur;
            finalResult.pop()
            finalResult.push(idx+1);
        }else if(cur===acc){
            finalResult.push(idx+1);
        }
        return acc;
    },0)
    return finalResult;
}


