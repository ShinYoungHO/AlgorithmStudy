기타 익숙하지 않은 발상들

===

|코드|결과|
|---|---|
|Array(5).fill(1)|[1, 1, 1, 1, 1]|
|Array(5).fill(1).map( (a,b) => a+b)  )|[1, 2, 3, 4, 5]|
|"A".match(/^[A-Z]$/)       |  ["A", index: 0, input: "A", groups: undefined]|
|"a".match(/^[A-Z]$/)               |           null|
| "s".match(/[A-Z]/gi)                 |         ["s"]|
|"S".match(/[A-Z]/gi)                  |        ["S"]|
|"abcde".replace( /(\w)(\w)/g, 12 )|"1212e"|
|"abc de".replace( /(\w)(\w)/g, 12 )|"12c 12"|
|"abc  de".replace( /(\w)(\w)/g, 12 )|"12c  12"|
|['1','2','3','4','5'].reduce((acc,cur)=>**acc = acc + cur**)|"12345"|
|['1','2','3','4','5'].reduce((acc,cur)=>**cur = acc + cur**)|"12345"|
|['1','2','3','4','5'].reduce((acc,cur)=>acc = cur + acc)|"54321"|
|['1','2','3','4','5'].reduce((acc,cur)=>cur = cur + acc)|"54321"|
|1+1+'2'|"22"|


<br><br><br>

|코드|
|--|
|return n <= a/2 ? b : solution(n, a + 1, b += n % a ? 0 : a); ;;;;;;; 약수의 합|
|`s.toUpperCase().replace(/(\w)(\w)/g, function(a){return a[0].toUpperCase()+a[1].toLowerCase();})`|




