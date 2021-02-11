[mutate & no mutation](https://doesitmutate.xyz/)
===
>splice의 경우 arr.splice(0,2,4) : 0번째 열에서부터 2개의 요소를 지우고 4를 대신 넣는다. 정도의 의미가 되겠다.

> splice는 mutable 한 함수인데, arr.splice(0,2,4)를 하게되면 위 결과를 arr에 새로 할당한다.

```js
arr = [1,2,3,4,5,6,7,8]
arr.splice(0,2,4)
console.log(arr)//arr = [4, 3, 4, 5, 6, 7, 8];
```
***


