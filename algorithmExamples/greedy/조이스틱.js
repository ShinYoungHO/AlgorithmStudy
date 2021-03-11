function solution(name) {
    //4,7,11
    let strArr = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'.split('');//26
    let nameSplit = name.split('')
    const tempResult =  nameSplit.reduce((acc,cur)=>{
        const nIdx = strArr.indexOf(cur)
        if(nIdx<13){
            return acc += 1 + nIdx;
        }else{
            return acc += 1 + 26 - nIdx
        }
    },-1)
    
    let aLen = 0;
    let aLenMax = 0;
    let idx = 0;
    for(let i = 0; i < nameSplit.length-1; i++){
        if(nameSplit[i] === 'A'){
            aLen++
            if(nameSplit[i+1] !== 'A' && aLen > aLenMax){
                idx = i;
                aLenMax = aLen;
            }
        }
    }
    if(idx-aLenMax > aLenMax) return tempResult;
    else return tempResult-aLenMax
}