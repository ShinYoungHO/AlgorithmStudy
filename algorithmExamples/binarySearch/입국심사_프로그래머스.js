function solution(n, time) {
    const times = time.sort((a, b)=>{a - b});
    let min = 1;
    let max = times[times.length-1]*n;
    let ans = max;
    while(max>=min){
        let mid = parseInt((min+max)/2)
        let tempN = 0;//해당시간에 심사한 사람 수
        for(let i = 0; i < times.length; i++){
            tempN += parseInt(mid/times[i])
            if(tempN>=n){
                ans = Math.min(mid,ans)
                break;
            }
        }
        if(tempN >= n){
            max = mid - 1
        }else{
            min = mid + 1
        }                      
    }
    return ans
}