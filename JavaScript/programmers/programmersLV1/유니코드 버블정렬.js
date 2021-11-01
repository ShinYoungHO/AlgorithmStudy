function solution(strings, n) {
    var answer = [strings[0]];
    let strLen = strings.length
    
    function swapper(j){ 
        let swap = null;   
        swap = answer[j];  
        answer[j] = answer[j+1]
        answer[j+1] = swap;
    }
    
    for(let i = 1; i < strLen; i++){
        let ansLen = answer.length;
        answer.unshift(strings[i])
        for(let j = 0; j < ansLen; j++){
            if(answer[j][n] > answer[j+1][n]){
                swapper(answer[j],answer[j+1])
                // let swap = null;
                // swap = answer[j];
                // answer[j] = answer[j+1]
                // answer[j+1] = swap;              
            }else if(answer[j][n] === answer[j+1][n]){
                if(answer[j] > answer[j+1]){
                    swapper(answer[j],answer[j+1])
                    // let swap = null;
                    // swap = answer[j];
                    // answer[j] = answer[j+1]
                    // answer[j+1] = swap;
                }else{
                    break;
                }
                
            }else{
                break;
            }                
        }    
    }
    return answer;
}





