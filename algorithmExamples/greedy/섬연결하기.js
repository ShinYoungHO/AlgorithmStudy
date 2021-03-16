function solution(n, costs){
  costs.sort((a,b)=>{
      if(a[2]>b[2]) return 1
      else if(a[2]<b[2]) return -1
      else return 0
  })

  let cycleTable=[];
  for(let i = 0; i < n; i++){
    cycleTable[i]=i;
  }
  console.log(cycleTable)
  const union = (el, arr, other) => { // el의 부모는?
    if(!other){
      if(arr[el] === el) return el;
      return arr[el] = union(arr[el], arr)
    }  
    else{//other가 있을 경우 순회하면서 

    }

  }
  let result = 0;
  costs.forEach(el=>{
    let [x,y,val] = el;
    let xParent = union(x,cycleTable);//x의 부모확인. 확인하면서 들렀던 모든 곳 부모노드로 대체.
    let yParent = union(y,cycleTable);
    if(xParent !== yParent){//부모가 다를경우에
        cycleTable[y] = xParent;
        result += val;
    }
    console.log(JSON.stringify(cycleTable),x,y)
  })
  return result;
}
console.log(solution(	8, [
  [5,6,1],
  [6,7,1],
  [4,5,1],
  [5,6,1],
  [0,1,1],
  [2,3,1],
  [1,6,1],
  [0,7,1] // : 반례
]))