// 문제 설명
// 행렬의 덧셈은 행과 열의 크기가 같은 두 행렬의 같은 행, 같은 열의 값을 서로 더한 결과가 됩니다. 2개의 행렬 arr1과 arr2를 입력받아, 행렬 덧셈의 결과를 반환하는 함수, solution을 완성해주세요.

// 제한 조건
// 행렬 arr1, arr2의 행과 열의 길이는 500을 넘지 않습니다.

/////////////////////111111111//////////////////
//arr1의 i 번째 idx와  arr2 의 i 번째idx의 길이가 같을 경우 해당 요소들에 대해서 연산실행
//길이가 같은지 판별하는 for문 1
//해당 요소의 모든 요소에 대해서 연산해주는 for문 2

/////////////////////22222222222////////////////
//더 효울적인진 잘 모르겠지만, arr1의 i 번째 요소의 마지막 idx = a라고 한다면 arr2[i][a]===true이고 arr2[i][a+1] === false라면 길이가 같다고도 할 수 있겠다.

//arr1길이 셈
//arr1[i]의 길이 셈

//예외 케이스
//고려사항 : [[]] - 빈 배열은 더할 수 없으므로 제외하자.



//////////////////222222222222222////////////////
//더 빠를거라 생각했는데 실행시간 초과...

function solution(arr1, arr2) {
    var answer = [];
    let length1 = arr1.length
    let lastIdx1_i = 0
    let tempArr = [];
    for(let i = 0; i < length1; i++){
        lastIdx1_i = arr1[i].length-1;
        if( lastIdx1_i === -1 ){
            continue;
        }else if(arr2[i][lastIdx1_i] && !arr2[i][lastIdx1_i+1]){
            for(let j = 0; j <= lastIdx1_i; j++){
                tempArr[j]= arr1[i][j] + arr2[i][j]
            }
            answer.push(tempArr)
            tempArr = [];
        }
    }
    return answer;
}

///////////////1111111111111111////////////////
////////////////정답입니다!//////////////////////
function solution(arr1, arr2) {
    let length_1 = arr1.length;
    var answer = [];
    for(let i = 0; i < length_1; i++ ){
        let length_1_i = arr1[i].length
        let length_2_i = arr2[i].length
        let tempArr = [];

        for(let j = 0; j < length_1_i; j++){
            if(length_1_i === length_2_i){
                tempArr[j]= arr1[i][j] + arr2[i][j]            
            }
        }
        answer.push(tempArr)
        tempArr = [];
    }
    return answer;
}