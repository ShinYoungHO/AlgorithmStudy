function solution(stones, k) {
    let zeroMap = [];
    for(let i = 0; i < stones.length; i++){
        if(stones[i]) zeroMap.push(false);
        else zeroMap.push(true);
    }
    let result = 0;
    
    while(true){
        let [path, min] = findPath(stones, k);
        if(!path) break;
        result += min;
        for(let i = 0; i < path.length; i++){
            stones[path[i].idx] -= min;
        }
    }
    console.log(result)
    return result;
}

function findPath(stones, k){
    let path = [];
    let min = Number.MAX_SAFE_INTEGER;
    for(let i = 0; i < stones.length; i++){
        if(stones[i]) {
            path.push({v:stones[i], idx:i});
            if(stones[i] < min) min = stones[i];
        }
        else {
            let j = i;
            let isValid = true;
            while(!stones[j] && j < stones.length){
                j++;
                if(j - i >= k){
                    isValid = false;
                    break;
                }
            }
            if(!isValid) return [false];
            [i, j] = [j - 1, i] ;
        }
    }
    return [path, min]
}





function solution(stones, k) {
    let l = 1; 
    let r = 200000000;
    
    while(l < r - 1) {
        let m = parseInt((l + r) / 2);
        if (checkValid(stones, m, k))  l = m;
        else r = m;
        console.log(l, r)
    }
    
    return l;
}

function checkValid(stones, m, k) {
    let count = 0; 
    for(let i = 0; i < stones.length; i++) {
        if(stones[i] < m) count += 1;
        else count = 0;
        if(count >= k) return false;
    } 
    return true;
}

solution([2, 4, 5, 3, 2, 1, 4, 2, 5, 1], 3)