** < 알고리즘 종류(md) / 예제(js) / 문법(md/js) > 폴더 별 내용 **
===
***
> 예제
>  
> 1. 연습문제는 사이트 별로 분류
> 2. 별도의 알고리즘 기법이 사용된 경우 알고리즘 명으로 된 폴더명에 해당 문제 출제 사이트 기입
> 3. 백준 홈페이지 문제들의 경우 js파일 최상단에 문제번호 기입.

***


###node.js - Baekjoon참고.

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