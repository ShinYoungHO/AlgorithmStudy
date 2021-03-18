var find132pattern = function(nums) {
  let stack = [],s3 = -Infinity
  for (let i = nums.length -1; i >= 0; i--) {
    if (nums[i] < s3) { // 3. 다음 값과 최근에 뺸 값은 내림차순으로 쌓이다가 잠깐 커졌을 때 재할당되며 이 값보다 작다는 것은 132 패턴을 만족함을 의미.
      return true;
    }
    while (stack[stack.length - 1] < nums[i]) { // 2. 내림차순으로 쌓을 수 있을 때까지 뻄. 뺏던 가장 작은 값 저장.
      s3 = stack.pop();
    }
    stack.push(nums[i]);// 1. 첫번째 요소 푸시, 내림차순으로 스택에 쌓음.
  }
  return false;
};

/*                //
 while 실행 ㄱ    //
            //   //
          // // //
        //    //  
       //       ^s3       
    nums[i]

*/