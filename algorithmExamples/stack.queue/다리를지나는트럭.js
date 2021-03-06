// 문제 설명
// 트럭 여러 대가 강을 가로지르는 일 차선 다리를 정해진 순으로 건너려 합니다. 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 합니다. 트럭은 1초에 1만큼 움직이며, 다리 길이는 bridge_length이고 다리는 무게 weight까지 견딥니다.
// ※ 트럭이 다리에 완전히 오르지 않은 경우, 이 트럭의 무게는 고려하지 않습니다.

// 예를 들어, 길이가 2이고 10kg 무게를 견디는 다리가 있습니다. 무게가 [7, 4, 5, 6]kg인 트럭이 순서대로 최단 시간 안에 다리를 건너려면 다음과 같이 건너야 합니다.
function solution(bridge_length, weight, truck_weights) {
    let count = 1;
    let curWeight = truck_weights.shift();
    const queue = Array(bridge_length-1).fill(false);
    queue.push(curWeight);
    while(curWeight > 0 || truck_weights.length > 0){
        const que = queue.shift();//항상 빠짐
        const val = truck_weights[0];//트럭무게 항상확인
        if(!!que){
            curWeight-=que;
        }
        if(curWeight + val <= weight){
            queue.push(truck_weights.shift());
            curWeight+=val
        }else{
            queue.push(false);
        }
        count++        
    }
    return count;
}


// solution 2
function solution(bridge_length, weight, truck_weights) {
    // 초기값 세팅
    // bridge_length만큼의 q 완성, 가장 오른쪽에는 첫번째 트럭이 들어간 상태
    let q = Array(bridge_length - 1).fill(false);
    let count = 1;
    let curWeight = truck_weights[0];
    q.push(truck_weights.shift());
    // 트럭이 다리에 모두 들어가면 while문이 끝나고,
    // 그 순간부터 트럭이 빠져나오는데 걸리는 시간은 다리의 길이만큼이다.
    while (truck_weights.length > 0) {
      let que = q[0];
      let nTruckWeight = truck_weights[0];
      if (que) {
        // 트럭이 빠져나오는 경우이면
        curWeight -= q.shift();
        console.log(curWeight);
        if (curWeight + nTruckWeight <= weight){
          curWeight += truck_weights[0]
          q.push(truck_weights.shift());
        }
        else q.push(false);
        count++;
      } else {
        //트럭이 아닌 경우
        if (curWeight + nTruckWeight > weight) {
          // false갯수만큼 count++, false갯수만큼 false를 q에 붙여준다.
          let i = 0;
          while (q[i] === false) i++;
          q.splice(0, i);
          q = [...q, ...Array(i).fill(false)];
          count += i;
          continue;
        } else if (curWeight + nTruckWeight <= weight) {
          // 트럭이 들어갈 수 있는 경우
          q.shift();
          q.push(truck_weights.shift());
          curWeight += nTruckWeight;
          count++;
        }
      }
    }
    return count + bridge_length;
  }