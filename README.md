node.js - Baekjoon
===
##단계별 예제 [@jh05013](https://www.acmicpc.net/step) step1 clear.
###input 입력 - 한줄 / devided by ' '
```js
//입력 fs모듈 사용 
// '1 2' -> ['1','2']
const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split(' ');

```
* 표준 입력을 String으로 변환 후 공백기준으로 split
* 여러줄 입력의 경우 split을 \n으로
* node fileName.js 실행
* console 로 출력 시 입력의 끝인 EOF를 찾을 수 없어서 ctrl + D 로 결과 확인.



***