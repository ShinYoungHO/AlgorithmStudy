고차함수
===
## forEach
* 입력받은 배열의 각 요소를 순서대로 불러와서 실행
* forEach(function(el){ ~ });
* 배열의 요소마다 수행하기 떄문에 반복문 대체 가능
* , , 와 같이 빈 항목이 있을 경우 건너 뛴다. 
* this와 add 및 forEach를 같이 이용하는 경우가 MDN에 있다. this 키워드를 공부한 후 돌아오자..


##every
* 배열원소 모두가 조건을 만족할 경우 true, 그중 하나라도 성립하지 않으면 false반환

## some
* 배열원소 일부가 조건을 만족하면 true. 


## find
* 배열의 특정값을 찾는 것
* filter랑 매우 비슷하지만 다르다.
* find의 경우 true값이 나온 순간 종료되지만, filter의 경우 true값으로 구성된 배열을 리턴한다.
*filter의 경우 콜백함수는 각 요소들을 시험할 함수, find의 경우 배열의 각 값에 대해 실행할 함수.

##sort
* arr.sort() : 유니코드 코드포인트 값에 따라 정렬
```js
let arr = [3,21,5,1,4,8,];
console.log(arr.sort()) /// [1, 21, 3, 4, 5, 8]
```
* 정렬 순서를 바꾸기 위해 클로저를 사용하는 경우도 있다.
```js
arr.sort(function(a,b){
    return a-b;
})
///[1, 3, 4, 5, 8, 21]
```
* 각 객체의 속성값을 순서로 해서 sort도 가능하다.
```js
let items = [
  { name: 'Edward', value: 21 },
  { name: 'Sharpe', value: 37 },
  { name: 'And', value: 45 },
  { name: 'The', value: -12 },
  { name: 'Magnetic', value: 13 },
  { name: 'Zeros', value: 37 }
];

// value 기준으로 정렬
items.sort(function (a, b) {
  if (a.value > b.value) {
    return 1;
  }
  if (a.value < b.value) {
    return -1;
  }
  // a must be equal to b
  return 0;
});
```



