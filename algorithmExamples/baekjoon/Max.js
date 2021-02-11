// 9개의 서로 다른 자연수가 주어질 때, 이들 중 최댓값을 찾고 그 최댓값이 몇 번째 수인지를 구하는 프로그램을 작성하시오.

// 예를 들어, 서로 다른 9개의 자연수

// 3, 29, 38, 12, 57, 74, 40, 85, 61

// 이 주어지면, 이들 중 최댓값은 85이고, 이 값은 8번째 수이다.


//arr[i]의 값이 maxIdx에 해당하는 값보다 클 경우 그 index i를 maxIdx에 저장
//maxValue도 선언해서 그때의 값 저장
//값이 같을 경우는 맨 마지막 Max를 반환해보자.

let MaxIdxNum = function(arr){
    let MaxIdx = 0;
    let MaxNum = 0;//자연수이므로 0보다 무조건 크다
    for(let i = 0; i< arr.length; i++){
        if(arr[i] >= MaxNum){
            MaxNum = arr[i]
            MaxIdx = i ;
        }
    }
    return `${MaxNum}\n${MaxIdx}`;

}

