// a/b
console.log('a')
console.log('b')
const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split(' ');

console.log( Number(input[0]) / Number(input[1]) )
