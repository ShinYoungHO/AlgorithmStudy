function fibo(num) {
    if (num <= 1){ 
        return 0;
    }else if (num == 2){ 
        return 1;
    }
    console.log(num-1+"가 필요합니다.")
    console.log(num-2+"가 필요합니다.")
    return fibo(num - 1) + fibo(num - 2);
  }


const memoize = function (func) {
    const cache = {};  
    return function (...args) {
      const problemName = JSON.stringify(args);
      console.log(problemName);
      if (!cache.hasOwnProperty(problemName)) {
        cache[problemName] = func(args);
        console.log(cache);
      }
      return cache[problemName];
    };
};

fibo = memoize(fibo);
//
debugger;fibo(5)
//10이 들어가고

//n번째 피보나치 수열의 값을 구하는 재귀함수가 있다.
//fibo(10) = fibo(9)+fibo(8) = fibo(8)+fibo(7)+ fibo(7)+fibo(6)
// = fibo(7)+fibo(6)+ fibo(6)+fibo(5)+fibo(6)+fibo(5)+fibo(5)+fibo(4) = ....
//결국 모든 값들이 1로 수렴되고 그 갯수는 피보나치 수열의 n번쨰 항이라고 할 수 있다.
//위와 같이 계산할 함수가 많아지며, 2^num order로 값이 증가한다.
//num의 숫자가 작으면 계산이 용이하지만 증가할 수록 계산할 값이 많아져 시간이 길어진다.
//위의 규칙에서 중복되는 값이 생기는데, 이를 메모한 값에서 참조한다면 계산이 더 수월해진다.

//fibo(n)은 fibo(n-1) + fibo(n-2)를 리턴하는데, fibo(n-1)을 바로 리턴해줄 수가 없어서 콜스택에 쌓인다.
//그리고 바로 fibo(n-1) 을 계산하러 함수를 호출하면 fibo(n-2) + fibo(n-3)을 리턴해야 되고, 또 그 값을 모르므로,
//fibo(n-2)를 콜스택에 쌓아두고 계산하러 떠난다. 
//이런식으로 n이 2또는 1에 다다를 떄 까지 콜스택에 쌓이고 n이 5라고 한다면 fibo(5) , fibo(4) , fibo(3) , fibo(2) , fibo(1) 순서로 담기게 된다.
//fibo(3)은 fibo(2), fibo(1) 을 계산한 결과이므로 fibo(3)의 값을 얻어낸다면 fibo(4)를 호출하기 위해 필요했던 fibo(2)를 다시 계산해야 한다.
//이런식으로 콜스택이 없어졌다 생겼다를 반복하면서 계산해 나가게 된다.
//여기서 문제점이 있는데, fibo(5) 를 계산하기 위해 fibo(4)를 계산 했는데, fibo(3)은 앞서 계산했지만 다시 재귀함수를 호출해서 값을 얻어야 한다.
//지금은 숫자가 작아서 괜찮지만, fibo(1000) 의 경우만 해도 어마어마한 콜스택이 쌓일 것이다. 


//메모이즈는 콜스택을 최소화하기 위한 하나의 수단이다. 이미 계산한 값을 어딘가에 저장해두고, 재귀함수를 실행하기 전 데이터가 있는지 확인하고 있으면 그냥 그 값을 리턴해주는 
//매우 효율적인 녀석이다.


 
