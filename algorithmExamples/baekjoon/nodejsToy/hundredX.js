let input = require('fs').readFileSync('/dev/stdin').toString().split('\n')

console.log( parseInt(input[0]) * parseInt(input[1][2]) );
console.log( parseInt(input[0]) * parseInt(input[1][1]) );
console.log( parseInt(input[0]) * parseInt(input[1][0]) );
console.log( parseInt(input[0]) * parseInt(input[1]) );
