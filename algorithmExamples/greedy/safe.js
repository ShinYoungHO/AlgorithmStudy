// target 금액과 금고에 있는 돈의 종류 type 을 입력받아, 조지가 target 을 훔칠 수 있는 방법의 수를 리턴
function ocean(target, type) {
    // TODO: 여기에 코드를 작성합니다.
    let count = 0;
    
    const counter = (temp,k) =>{
      if(temp===target) return true;
      if(temp > target) return false;
      let toggle = false;
      for(let i = k; i < type.length; i++){
        let sumVal = temp+type[i];
        if(sumVal<=target){
          toggle = counter(sumVal,i)
          if(toggle){
            count++
          }
        }
      }
    }
    if(target>2000){
      return null
    }
    counter(0,0)
    return count;
  }

  //greedy로 한계가 있음 -> DP 필요 ~ 점화식