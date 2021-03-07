//Add
const BitwiseAdd = (a,b) => {
    while( b !==0 ){
        let carry = (a & b); //둘다 1을 가지고 있는 위치가 모두 1
        a = a ^ b;           //둘 중 하나만 1인 위치와 
        b = carry << 1;     
    }
    return a;
}

//subtract
const BitwiseNegative = (a) => { //음수로 만들고
    return BitwiseAdd(~a,1);
}

const BitwiseSubtract = (a,b) => {//음수랑 양수 덧셈
    return BitwiseAdd(a, BitwiseNegative(b))
}

//Multiply
const BitwiseMultiply = (a,b) => {
    let m = 1, c = 0;
    if(a < 0){
        a = BitwiseNegative(a);
        b = BitwiseNegative(b);
    }
    while(a >= m && b){
        if(a & m){
            c = BitwiseAdd(b,c);
        }
        b = b << 1;
        m = m << 1;
    }
    return c;
}
