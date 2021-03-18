var threeSum = function(nums) {
  nums.sort((a, b) => a - b);
  const r = [];
  
  for (let i = 0; i < nums.length - 2; i++) {
    if (nums[i] > 0) break;//sort 했으므로 오른쪽은 모두 0보다 커져서 
    if (i > 0 && nums[i] === nums[i - 1]) continue;//중복제거
    let left = i + 1;
    let right = nums.length - 1;
    
    while (left < right) {
      const s = nums[i] + nums[left] + nums[right];
      if (s < 0) left += 1;
      else if (s > 0) right -= 1;
      else {
        r.push([nums[i], nums[left], nums[right]]);
        while (left < right && nums[left] === nums[left + 1]) left += 1;//중복 제거
        while (left < right && nums[right] === nums[right - 1]) right -= 1;//중복 제거
        left += 1;
        right -= 1;
      }
    }
  }
  
  return r;
};