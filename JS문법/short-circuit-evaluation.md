shortCircuitEvaluation : 단축평가값
===
keyword : 논리연산자, 옵셔널체인징 연산자, null병합 연산자 
***
```js
let A = "A"
let B = "B"
let U = ""

let AB = U || A  || B   
// console.log("AB") = "A"

```

* U가 falsy한 값이라서 or연산자에 들어가면 false로 취급된다.
* false이므로 A로 넘어가서 문자열이 담긴 A를 검사하는데 truthy한 값이라 결국 A가 출력된다. 
* **true를 리턴할 가능성이 높은 코드를 가장앞에 놓는다! // 비용이 높은 코드는 나중에 평가하는 것이 좋다고 한다.**

###단축평가 활용
```js
const except = function(val, b){
        return val + b
}
```
* 받아온 val에 대해 b를 더한 연산의 결과를 리턴하고 싶다고 하자.
* if문으로 처리할 수 있지만 다음과 같이 쓸 수도 있다.

```js
const except = function(val, b){
    val = val + b    
    return val || "wrong"
}
```

* b 가 입력되지 않거나, falsy한 값일 경우 블로킹 처리를 할 수 있다.
* 이 [블로그](https://curryyou.tistory.com/193) 에서 언급한 활용에 대한 예시는 다음과 같다.
null, undefined 체크
> 
> 함수 매개변수 기본값 설정
> 
> 조건부 변수값 할당
> 


### null, undefined 확인

```js
const arr = null;
const arrLength = arr.length
console.log(arrLength) /// Cannot read property 'length' of null..
```
* 위와같이 에러가 발생하지만, 연산자를 활용하면 다음과 같이 오류를 없에줄 수 있다. undefined의 경우도 다음과 같다. 

```js
const arr = null;
const arrLength = arr && arr.length
console.log(arr & arrLength) /// 0 

const arr = null;
const arrLength = arr && arr.length
console.log(arr) // null

```

###함수 매개변수 기본값 설정

```js
const identity = function(val){
    return val 
}

identity() // undefined

```
* 위와같이 짧은 코드에선 문제를 발견하기 쉽지만, 코드가 복잡해진다면? 일일이 디버거를 돌려보거나 console.log()를 코드 중간에 넣어가며 확인을 하는 불상사가 있을 수 있다. 
* 하지만 매개변수가 전달되지 않았을 경우 기본값을 지정해준다면 찾기 용이할 것 같다. 다음을 보자

```js
const identity  = function(val){
    return val || "identity"
}
identity()// "identity"
```
* 위와 같이 함수 이름을 튀어나오게 블락해준다면 변수가 할당되지 않은 문제를 발견하기 용이할 것 같다. 
* 외부에서 받은 값을 확인하는데도 사용한다고 한다. 
* 외에도 조건문을 대신하는데도 쓰일 수 있다.

***

###옵셔널 체인징 연산자(undefined, null)
* 옵셔널 체이닝 연산자 ?.는 좌항의 피연산자가 null 또는 undefined인 경우 undefined를 반환하고, 그렇지 않으면 우항의 프로퍼티 참조를 이어갑니다

```js
const identityLen  = function(val){
    return val && val.length
}
identityLen("") // "" : 문자열이 falsy한 값이라 좌항을 그대로 반환하지만;

const identityLen  = function(val){
    return val?.length
}
identityLen("") // 0 : null, undefined 가 아닌 이상 길이를 참조할 수 있게된다.

```

###null 병합 연산자
*  null 병합 연산자 ??는 좌항의 피연산자가 null 또는 undefined인 경우 우항의 피연산자를 반환하고, 그렇지 않으면 좌항의 피연산자를 반환합니다.

```js
const identity  = function(val){
    return val??"a"
}
identity('')  // ""
identity()    // "a"
identity(null)// "a"
```

* 유효한 기본값을 정할때 유용할 듯 하다. 예외처리에서 나중에 활용해 볼 필요가 있겠다.

