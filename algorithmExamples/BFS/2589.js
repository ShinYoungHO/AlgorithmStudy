let input = require('fs').readFileSync('/dev/stdin').toString().split('\n')
let rc = input.shift();
rc = rc.split(' ')
let max = -1;
let rowLen = parseInt(rc[0]);
let colLen = parseInt(rc[1]);
let visited = [];
for(let i = 0; i < rowLen; i++){
    visited.push(Array(colLen).fill(-1))
}
for(let i = 0; i < rowLen; i++){
    for(let j = 0; j < colLen; j++){
        if(input[i][j]==='L'){
            maxLenFinder(i,j);
        }
    }
}

function maxLenFinder(row,col){
    let shiftedVal = null;
    let queue = [];
    let copyVisited = visited.map(v => v.slice());
 
    let dRow = [0, 1, 0, -1];
    let dCol = [1, 0, -1, 0];//3,6,9,12
    queue.push([row,col]);//[row,col]
    copyVisited[row][col] = 0;
    let tempMax = null;

    while(queue.length > 0){//queue가 다 빠질 때 까지 진행
        shiftedVal = queue.shift();
        let row = shiftedVal[0];
        let col = shiftedVal[1];

        for(let i=0; i<4; i++){//조건에 맞는 사방값들 queue에 push;
            nRow = row+dRow[i];
            nCol = col+dCol[i];            
            if(nRow < 0 || nRow >= rowLen || nCol < 0 || nCol >= colLen){ continue } //더했을 때 벗어나면 더해주지 않는다.          
            if(copyVisited[nRow][nCol] !== -1) { continue } // 방문 안했던 곳은 -1이다.
            if(input[nRow][nCol] === 'L'){ // 다음 노드가 Land일 경우 queue에 푸쉬
                queue.push([nRow, nCol]);
                copyVisited[nRow][nCol] = copyVisited[row][col]+1;  
                tempMax = copyVisited[nRow][nCol];
            }
        }  
    }
    if(tempMax > max){
        max = tempMax;
    }
}
console.log(max);