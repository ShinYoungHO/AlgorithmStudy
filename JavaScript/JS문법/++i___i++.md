전위 후위 연산자
===

```js
for(let i = 0; i < 5;){console.log(++i)}// 1,2,3,4,5

for(let i = 0; i < 5;){console.log(i++)}//0,1,2,3,4
```

* 전위의 경우 리턴하고 증감을 하지만,
* 후위의 경우 리턴하고 증감을 한다. 


```js
let args = [1,2,3,4,5]
for(let i = 0; i < args.length; ){
    console.log(args[++i])        // 2,3,4,5,undefined
}
```

* ++i의 성능이 좋았다고 하지만 요즈음엔 굳이 쓸 필요가 있을까 싶습니다. 
* 꼭 필요한 경우가 생긴다면 예제와 함께 돌아와야겠다.