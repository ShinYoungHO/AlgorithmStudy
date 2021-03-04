function solution(nums) {
    
    function isDeci(num){
        for(let i = 2; i <= parseInt(num**(1/2)); i++){
            if(num%i === 0){
                return false;
            }
        }
        return true;
    }
    let result = 0;
    let len = nums.length;
    for(let i = 0; i < len-2; i++){
        for(let j = i+1; j < len-1; j++){
            for(let k = j+1;k<len;k++){
                if(isDeci(nums[i]+nums[j]+nums[k])) result++;
            }
        }
    }
    return result
}



///////////////////////////////////////////////
///////////////////////////////////////////////
function solution(nums) {
    
    function isDeci(num){
        for(let i = 2; i <= parseInt(num**(1/2)); i++){
            if(num%i === 0){
                return false;
            }
        }
        return true;
    }
    let result = 0;
    
    const comb = function(arr,count,val,len){
        if(count===0){
            if ( val%2!==0 && isDeci(val) ){
                result++
                return;
            }                       
        }
        if(count+len < 0) return;
        if(arr.length===0) return;
        let newArr = arr.slice(1);
        
        comb(newArr,count,val,len-1);
        val+=arr[0];
        comb(newArr,count-1,val,len-1); 
    }
    comb(nums,3,0,nums.length)
    return result
}


/////////////////////////////////////
///////////////////////////////////////





function solution(nums) {
    
    function isDeci(num){
        let max = parseInt(num**(1/2));
        let temp = Array(max+1).fill(true)
        
        for(let i = 2; i <= max; i++){
            if(temp[i]){
                if(num%i === 0){
                    return false;
                }else{
                    for(let j = 2*i; j <= max; j+=i){
                     temp[j] = false;
                    } 
                }
            }
        }
        return true;
    }
    let result = 0;
    
    const comb = function(arr,count,val,len){
        if(count===0){
            if (isDeci(val) ){
                result++
                return;
            }                       
        }
        if(count+len < 0) return;
        if(arr.length===0) return;
        let newArr = arr.slice(1);
        
        comb(newArr,count,val,len-1);
        val+=arr[0];
        comb(newArr,count-1,val,len-1); 
    }
    comb(nums,3,0,nums.length)
    return result
}

