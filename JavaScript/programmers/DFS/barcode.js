//바코드에서 인접한 두 개의 부분 수열이 동일하다면 제작할 수 없다고 할 때, 주어진 길이 len의 바코드 중 가장 작은 수를 반환해라
function barcode(len) {
  let queue = ['1'];
  const DFS = function(){
    let deque = queue.shift();
    if(deque.length === len){
      return deque;
    }
    for(let i = 1; i <= 3; i++){
      let temp = deque+i
      let toggle = true
      for(let j = 1; j < temp.length; j++ ){
        if( temp.slice(temp.length-2*j,temp.length-j) === temp.slice(-j) ){
          toggle = false;
          break;
        }
       
      }
      if(toggle){
        queue.push(temp)
        let isVariable =  DFS();
        if(isVariable) return isVariable;
      }
    }
  }  
  return DFS()
}