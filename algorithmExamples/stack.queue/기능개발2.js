// 프로그래머스 팀에서는 기능 개선 작업을 수행 중입니다. 각 기능은 진도가 100%일 때 서비스에 반영할 수 있습니다.

// 또, 각 기능의 개발속도는 모두 다르기 때문에 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고, 이때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포됩니다.

// 먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses와 각 작업의 개발 속도가 적힌 정수 배열 speeds가 주어질 때 각 배포마다 몇 개의 기능이 배포되는지를 return 하도록 solution 함수를 완성하세요.

// 제한 사항
// 작업의 개수(progresses, speeds배열의 길이)는 100개 이하입니다.
// 작업 진도는 100 미만의 자연수입니다.
// 작업 속도는 100 이하의 자연수입니다.
// 배포는 하루에 한 번만 할 수 있으며, 하루의 끝에 이루어진다고 가정합니다. 예를 들어 진도율이 95%인 작업의 개발 속도가 하루에 4%라면 배포는 2일 뒤에 이루어집니다.

function solution(progresses, speeds) {//[5,10,1,1,20,1]
    let result = [0];
    let days = progresses.map((el,idx)=>{
        return Math.ceil((100-el)/speeds[idx]);
    })
    let max = days[0];
    console.log(days)
    for(let i = 0,j = 0; i < days.length; i++){
        if(days[i] <= max){
            result[j] += 1;
        }else{
            max = days[i];
            j++;
            result[j] = 1;
            
        }
    }
    return result;
}

//     let result = [];
//     while(progresses.length > 0){//progresses의 길이가 0이 될때까지 반복
//         let count = null;
//         for(let i = 0; i < progresses.length; i++){//100이 넘지 않는 이상 speeds만큼 더해줌
//             progresses[i]+=speeds[i]
//         }
//         while(progresses[0] >= 100){//progresses의 0번째 idx에 100이 넘는 값이 있는지 확인 후 
//             progresses.shift();//progress의 앞의 항목을 pop하면서 100이 넘는 값이 없을 때 까지 반복
//             speeds.shift();//for문에 idx쓰려고 speed도 같이 빼줬다.
//             count++;//몇번 pop했는지
//         }
//         if(count){//count 값이 존재하면 몇번 pop했는지 셋던 count를 result에 push해줌.
//             result.push(count);
//         }
//     }
//     return result;
// }