let input = require('fs').readFileSync('/dev/stdin').toString().split(' ')

const result = function(){
    const A = parseInt(input[0]);
    const B = parseInt(input[1]);
    const C = parseInt(input[2]);

    console.log((A+B)%C);
    console.log(((A%C) + (B%C))%C);
    console.log((A*B)%C);
    console.log(((A%C) * (B%C))%C);
}
result();


let input = require('fs').readFileSync('/dev/stdin').toString().split(' ')

    console.log((parseInt(input[0])+parseInt(input[1]))%parseInt(input[2]));
    console.log(((parseInt(input[0])%parseInt(input[2])) + (parseInt(input[1])%parseInt(input[2])))%parseInt(input[2]));
    console.log((parseInt(input[0])*parseInt(input[1]))%parseInt(input[2]));
    console.log(((parseInt(input[0])%parseInt(input[2])) * (parseInt(input[1])%parseInt(input[2])))%parseInt(input[2]));


