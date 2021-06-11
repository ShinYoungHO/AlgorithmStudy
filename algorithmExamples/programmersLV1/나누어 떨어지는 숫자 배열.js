// array의 각 element 중 divisor로 나누어 떨어지는 값을 오름차순으로 정렬한 배열을 반환하는 함수, solution을 작성해주세요.
// divisor로 나누어 떨어지는 element가 하나도 없다면 배열에 -1을 담아 반환하세요.

// 제한사항
// arr은 자연수를 담은 배열입니다.
// 정수 i, j에 대해 i ≠ j 이면 arr[i] ≠ arr[j] 입니다.
// divisor는 자연수입니다.
// array는 길이 1 이상인 배열입니다.


// 입출력 예#1
// arr의 원소 중 5로 나누어 떨어지는 원소는 5와 10입니다. 따라서 [5, 10]을 리턴합니다.

// 입출력 예#2
// arr의 모든 원소는 1으로 나누어 떨어집니다. 원소를 오름차순으로 정렬해 [1, 2, 3, 36]을 리턴합니다.

// 입출력 예#3
// 3, 2, 6은 10으로 나누어 떨어지지 않습니다. 나누어 떨어지는 원소가 없으므로 [-1]을 리턴합니다.


//오름차순으로 먼저 정렬하되 그 과정에서 오름차순으로 배열해줘야 함
//push 시킬 값들은 divisor로 나눴을 때 나머지가 0인 숫자에 해당하므로,
//divisor로 나눴을 때 나머지가 0인 값들에 대해서만 오름차순으로 정렬하면 되겠다,
//divisor로 나눴을 때 0인 경우 정렬을 수행한다.
//배열앞에 넣어준 뒤 다음 idx의 값과 비교해서 클경우 스왑하고 작을 경우 for 문을 빠져나온다.
//그 다음값과 비교할 수 없는 경우가 있다. 1. 초기 길이가 1인 경우 ; 2. 마지막 idx에 도착하면 다음 값이 없다.
//


//버블 정렬
function solution(arr, divisor) {
    let answer = [];
    let forSwap = 0;
    for(let i = 0; i < arr.length; i++ ){
        if(arr[i] % divisor === 0){
            answer.unshift(arr[i]);
            if(answer.length === 1){//초기 길이가 1인 경우 내부 for문 실행하지 않음.
                continue;
            }
            for(let j = 0; j < answer.length-1; j++){//arr의 마지막에서 두번째 idx까지만 진행할 필요가 있음.
                if(answer[j] > answer[j+1]){
                    forSwap = answer[j];
                    answer[j] = answer[j+1];
                    answer[j+1] = forSwap;
                }else{
                    break;//더이상 정렬할 필요가 없으므로 for문 빠져나옴.
                }
            }
        }
    }
    return answer;
}

//나눠지지 않을 경우 [-1]을 반환해야되서 초기값을 -1로 잡고 arr의 맨 끝에서 수행. 
//앞에서 할 경우 -1을 무조건 한번 지나기 때문에 제외하고자 함.
function solution(arr, divisor) {
    let answer = [-1];
    let forSwap = 0;
    let length = arr.length;
    for(let i = 0; i < length; i++ ){
        if(arr[i] % divisor === 0){
            answer.push(arr[i]);
            for(let j = answer.length-1; j > 0; j--){//arr의 마지막에서 두번째 idx까지만 진행할 필요가 있음.
                if(answer[j-1] > answer[j]){
                    forSwap = answer[j-1];//j-1을 잠깐 저장하고
                    answer[j-1] = answer[j];//j-1을 j로 대체한 다음 j , j 의 두번째 j에 저장한 값 할당
                    answer[j] = forSwap;
                }else{
                    break;//더이상 정렬할 필요가 없으므로 for문 빠져나옴.
                }
            }
        }
    }
    if(answer[1]){
        answer.shift();
        return answer
    }else{
        return answer;
    }
}








// function aaa(){
//     let answer = [];
//     for(let i = 0; i < 10; i++){
//         if(i === 5){
//             continue;
//         }    
//         for(let j = 0; j < 3; j++){
//             answer.push(i+j)
//         }    
//     }
//     return answer;
// }
