삼항  연산자 : Conditional (ternary) operator]
===
* 가끔 스택오버플로우나 MDN을 보면 이해할 수 없는 코드가 나온다.
* 이를 위해 정리해보고자 함.

```js
let age = 29;
let canDrinkAlcohol = (age > 19) ? "True, over 19" : "False, under 19";

console.log(canDrinkAlcohol); 
// "True, over 19"
```
***
* ?를 보니 if의 조건이 true인지 false인지 나에게 물어보는 것 만 같다. 이때 true이면 왼쪽을 false이면 오른쪽을 나타내는 것 같은데 계속 확인해보자.

```js
access = firstCheck ? "Access denied" : secondCheck ? "Access denied" : "Access granted";
```
* 위와 같이 다중 삼항평가도 가능하며 괄호를 사용 가능하지만, 결과에 영항을 주지 않는다고 한다.또한 우측부터 그룹핑된다고 한다.
* 연산자 우선순위는 괄호가 높은데, 더 확인해볼 필요가 있겠다.<br>
ex)
```js
let condition1 = true,
    condition2 = false,
    access1 = condition1 ? (condition2 ? "true true": "true false") : (condition2 ? "false true" : "false false");
    access2 = condition1 ? condition2 ? "true true": "true false" : condition2 ? "false true" : "false false";

console.log(access1); // logs "true false"
console.log(access2); // logs "true false"
```

* if/else문에 적합하며, 코드양이 줄어들긴하는데 아직은 직관적으로 받아들이기가 힘들다.

```js
let func1 = function( .. ) {
  if (condition1) { return value1 }
  else if (condition2) { return value2 }
  else if (condition3) { return value3 }
  else { return value4 }
}

var func2 = function( .. ) {
  return condition1 ? value1
       : condition2 ? value2
       : condition3 ? value3
       :              value4
}

  //return (condition1 ? value1)  : (condition2 ? value2)   : (condition3 ? value3)   :   ( value4 )
}
```
* 우측부터 : 을 기준으로 그룹핑되며, 위의 함수1,2를 비교해보니 왼쪽에서 오른쪽으로 읽어 나가는것 같다.
* 단순한 내용을 짧게 표현해서 긴 코드를 읽을때 용이하긴 하겠다. 앞으로 이런 형식의 코드를 그냥 넘어가지 않을 수 있게됐다.
* 이후 이해가 안돼는 부분이 나오면 추가할 것.
***
>>***
>>>>***
>>>>>>***

[화살표함수 : arrow function](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Functions/%EC%95%A0%EB%A1%9C%EC%9A%B0_%ED%8E%91%EC%85%98)
===
* 화살표 함수 표현(arrow function expression)은 function 표현에 비해 구문이 짧고  자신의 this, arguments, super 또는 new.target을 바인딩 하지 않습니다. - MDN
* 첫문장부터 새로운 내용이라 this를 배운 후 추가해야 될 듯 싶다.
* this에선 또 새로운 객체지향프로그래밍이 나오는데, 찾아보니 모르는 단어들이 꽤 많다. 아주많이... 다른 방식의 공부도 필요할 듯 싶다.
* 우선 남는 시간에 C언어 책을 한번 봐야겠다. 오늘은 여기까지.





















































