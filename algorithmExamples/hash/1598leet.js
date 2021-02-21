var minOperations = function(logs) {
    let count = 0;
    for(let i = 0; i < logs.length; i++){
        
        if(logs[i].split('/')[0] === '..'){
            if(count !== 0) count--
        }
        
        else if(logs[i].split('/')[0] === '.') continue;
        
        else count++ 
    }
    return count
};