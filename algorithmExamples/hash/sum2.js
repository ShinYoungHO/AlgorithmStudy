// sum2
const twoSum = function(nums, target) {
  const map = {};    
  nums.forEach((num,idx) => map[num] = idx);//키에 값, 밸류에 인덱스 할당
  for(let i = 0; i < nums.length; i++){
      if(map[target-nums[i]] && map[target-nums[i]] !== i){// target-nums[i] 가 num의 키로 존재하면서 같은걸 고르는 경우가 아닌 경우 
          return [map[target-nums[i]],i];
      }
  }   
};

// sum3
