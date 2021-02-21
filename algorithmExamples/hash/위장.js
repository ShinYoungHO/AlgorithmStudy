function solution(clothes) {
    let temp = {};
    for(let i = 0, len = clothes.length; i < len; i++){
        if( temp[ clothes[i][1] ] ){
            temp[ clothes[i][1] ].push( clothes[i][0] )
        }
        else{
            temp[ clothes[i][1] ] = [ clothes[i][0] ]
            
        }
    }
    let result = 1;
    for(let keys in temp){
        result *= temp[keys].length+1;
    }
        
    return result - 1;
    
}