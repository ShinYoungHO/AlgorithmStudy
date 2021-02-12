입출력 node.js 
===

###input 입력.
```js
//입력 fs모듈 사용 
// '1 2' -> ['1','2']
const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split(' ');

```