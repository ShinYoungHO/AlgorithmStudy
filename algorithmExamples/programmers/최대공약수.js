// 두 수를 입력받아 두 수의 최대공약수와 최소공배수를 반환하는 함수, solution을 완성해 보세요. 배열의 맨 앞에 최대공약수, 그다음 최소공배수를 넣어 반환하면 됩니다. 예를 들어 두 수 3, 12의 최대공약수는 3, 최소공배수는 12이므로 solution(3, 12)는 [3, 12]를 반환해야 합니다.

// 제한 사항
// 두 수는 1이상 1000000이하의 자연수입니다.

////////////////////////////////유클리드 호제법///////////////////////////////////
function solution(n, m) {
    //n>m
    let gComDiv = gComDivSolve(n,m);    
    gComDiv.push(n*m/gComDiv);
    return gComDiv;  
}

function gComDivSolve(n,m){
    if(n<m){return gComDivSolve(m,n)}
    let rest = n%m
    if(rest === 0){
        return [m];
    }
    return gComDivSolve(m,rest)
}


// function solution(n, m) {
//     //n>m
//     let count=0;
//     function gComDivSolve(n,m){
//         if(n < m){return gComDivSolve(m,n)}

//         if(m===n || m === 0){return count===0?m:m*count*2}
//         if(!(m%2) && !(n%2)){
//             count++;
//             return gComDivSolve(n/2,m/2)
//         }else if(!(n%2) && (m%2)){
//             return gComDivSolve(n/2,m)
//         }else if((n%2) && !(m%2)){
//             return gComDivSolve(n,m/2)
//         }else if((n%2) && (m%2)){
//             return gComDivSolve((n-m)/2,m)
//         }
//     }    
    
//     let gComDiv = gComDivSolve(n,m);    
//     // gComDiv.push(n*m/gComDiv);
//     return [gComDiv,n*m/gComDiv];  
// }