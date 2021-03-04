function solution(numbers, hand) {
    let [L,dl] = [0,3];
    let [R,dr] = [2,3];
    let temp = [1,2,3,4,5,6,7,8,9,'*',0,'#']
    let answer = "";
    for(let i = 0,len = numbers.length; i<len; i++){
        let idx = temp.indexOf(numbers[i]);
        let [x,y] = [idx%3,parseInt(idx/3)];
        if(x===0){
            [L,dl] = [x,y];
            answer+="L"
        }else if(x===2){
            [R,dr] = [x,y];
            answer+="R"
        }else{
            if(Math.abs(x-L)+Math.abs(y-dl) === Math.abs(x-R) + Math.abs(y-dr)){
                if(hand==='left'){
                    [L,dl] = [x,y];
                    answer+='L'
                }else{
                    [R,dr] = [x,y];
                    answer+="R"
                }
            }else{
                if( Math.abs(x-L)+Math.abs(y-dl) > Math.abs(x-R) + Math.abs(y-dr) ){
                    [R,dr] = [x,y];
                    answer+="R"
                }else{
                    [L,dl] = [x,y];
                    answer+='L'
                }
            }
        }        
    }
    return answer
}


