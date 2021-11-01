연산자 우선순위 
===
>(1+2*3) 과 마찬가지로 A || B && C 에도 순서가 있다.<br>
```js

console.log((12>2) || (1<3) && "");

```

1. || 와 && 중에 우선순위는 && 이다. 이 외에도 다양한 기호들의 우선순위가 있다. [MDN참조](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/Operator_Precedence)
2. 그렇다면 1<3 과 "" 는?
3. 1<3은 true임은 확실하지만 ""은 와닿지가 않는다.
4. Boolean("")은 콘솔창에 표시하면 false 값을 가지는데, 이는 falsy한 값을 가지기 때문이다. falsy한 값 = [false, 0, -0, 0n, "", null, undefined와 NaN]
5. true && "" 을 비교하는 셈인데 ""가 나오는 이유는 MDN에선 다음과 같이 설명한다. 
>>expr1 && expr2 의 경우 expr1을 true로 변환할 수 있는 경우 expr2을 반환, 그렇지 않으면 expr1을 반환<br>
expr1 || expr2의 경우 expr1을 true로 변환할 수 있으면 expr1을 반환, 그렇지 않으면 expr2를 반환<br>
!expr 의 경우 단일 피연산자를 true로 변환할 수 있으면 false를 반환, 그렇지 않으면 true를 반환<br>
6. 위의 과정에 의해 true && "" 는 첫 요소가 true이기 때문에 두번째 요소인 ""를 반환하게 된다.
7. 이후 12>2 || "" 는 12>2가 true이기 때문에, 결국 12>2를 의미하는 true가 나온다.


###활용 예시

입력을 주면 3이상 10이하이거나 3의 배수인지 판별해주는 함수를 구해보자.

1
```js

function a310(num){
    return (num%3===0)||(num>=3)&&(num<=10);
}
```
2
```js
function b310(num){
    if(num>3 && num<=10){
        return true;
    }else if(num%3===0){
        return true;
    }else{
        return false;
    }
}

```
코드 줄의 수 부터 차이가 나게된다... 우선순위만 파악하고 있으면 위의 코드를 같은 의미로 받아 들이면서도 코드를 간소화할 수 있게 되어 나중에 코드의 수정사할이 생길 때 헷갈림이 덜 할 것 같다. 

---
#truthy, falsy
>truthy한 값, falsy한 값이 어디에 쓰일까?

>예외처리할때 ? 다음을 보자.
```js
function age(person){
    return person.age;
}

const person = {
age:21
}

age(person); // 21

```
>age함수를 돌렸을 때, 저장된 배열이 없는 경우 "데이터가 없습니다."를 출력하는 기능을 구현하고자 한다면 다음과 같다.
```js
function age(person){
    if (person ===='' ||person===undefined||person ===null){
        return "데이터가 없습니다."
    }else{
        return person.age;
    }
}
```
>truthy,falsy를 이용하면 조건을 다음과 같이 줄일 수 있게 된다.
```js
function age(person){
    if (!person){
        return "데이터가 없습니다."
    }else{
        return person.age;
    }
}
```

---
#재귀함수
>함수안에서 같은 함수를 재사용한다고 생각하면 되겠다.
```js
//   반복문을 사용하지 않고 표현해보자.
// 2의 n승 구하기
function n2(num){
    if(num<0){//종료조건
        return; 
    }
    if(num===0){
        return 1;//기반조건
    }
    return 2*n2(num-1);//재귀
}


```
>재귀함수 반복문 차이

* 재귀함수는 스택메모리를 사용해서 스택 오버플로우가 발생할 수 있다. 
* 반면 반복문은 메모리힙을 이용한다고 한다.
* 코드 길이 또한 줄일 수 있다.
***




