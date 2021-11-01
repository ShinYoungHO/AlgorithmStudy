// "OOXXOXXOOO"와 같은 OX퀴즈의 결과가 있다. O는 문제를 맞은 것이고, X는 문제를 틀린 것이다. 문제를 맞은 경우 그 문제의 점수는 그 문제까지 연속된 O의 개수가 된다. 예를 들어, 10번 문제의 점수는 3이 된다.

// "OOXXOXXOOO"의 점수는 1+2+0+0+1+0+0+1+2+3 = 10점이다.

// OX퀴즈의 결과가 주어졌을 때, 점수를 구하는 프로그램을 작성하시오.


// 첫째 줄에 테스트 케이스의 개수가 주어진다. 각 테스트 케이스는 한 줄로 이루어져 있고, 길이가 0보다 크고 80보다 작은 문자열이 주어진다. 문자열은 O와 X만으로 이루어져 있다.



//num만큼 for문 실행 or //?




//obj의 길이만큼 실행
//obj의 길이만큼 for문 실행 
//obj의 각 요소마다 for문 실행
//처음 O 문자열이 나오면 result에 1더함
//O 문자열의 다음 요소가 O일 경우 이전 값에 1을 더한 만큼 점수 할당
//X 문자열이 나오면 result에 0을 더함. 그 다음에 O가 나올 경우 이전값에 1을 더하면 1이 더해지게 된다.

//0번째 Idx에선 이전 값이 없으므로 초기값 설정
//길이만큼 실행되면 result를 최종 리턴할 값에 더함.
//필요 변수 : tempresult result 

//console.log로 출력만 할 경우 result만 필요할 것 같다.

//이전값이 O또는 X임을 판별하지 않고 변수를 새로 선언할 필요가 있겠다.
//

let totalOXscore = function(num, obj){//다음 값이
    let result = '';
    for(i = 0; i < num; i++){
        let tempresult = 0;
        let curValue = 0;
        if(obj[i][0] === 'O'){
            curValue = 1;
            tempresult = 1;
        }
        for(j = 0; j < obj[i].length-1; j++){
            if(obj[i][j+1] === 'O' ){
                curValue += 1;
                tempresult += curValue;
            }else{
                curValue = 0;                
            }
            // if(i===0 && j === obj[i].length-2){
            //     result = tempresult;
            // }
        }
        result = `${result}\n${tempresult}`;
        tempresult = 0;
        curValue = 0;
    }
    return result;
}
//0번째엔 현재값을  tempresult에 더한 뒤 시작
//0번째 다음 요소가 O이면 현재값에 1 더한 값을 tempresult에 더함 아닐 경우는 curValue초기화
//1번째엔 다음 요소가 O이면 현재값에 1 더한 값을 tempresult에 더함 아닐 경우는 curValue초기화....
//마지막열은 이전 요소가 이미 더해줬음.

// totalOXscore(5,['OOXXOXXOOO','OOXXOOXXOO','OXOXOXOXOXOXOX','OOOOOOOOOO','OOOOXOOOOXOOOOX'])
// "
// 10
// 9
// 7
// 55
// 30"



