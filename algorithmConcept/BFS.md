BFS 너비 우선탐색 
===

* 인접한 노드부터 먼저 탐색하는 방법
* 인접한 노드를 모두 탐색하기 때문에 탐색했던 노드들을 거쳐가다가 이미 탐색을 마쳤던 노드로 돌아간다면 무한 루프에 빠질 수 있다.
* 방문 했던 이력이 있는지 확인한 뒤 queue에 넣어줘야 한다.
* queue에 노드 주변 노드들을 모두 넣어주고 선입선출 방식으로 노드들의 검사를 수행하면 주변의 모든 노드를 검사할 수 있다.
* visited라는 map를 새로 만들어서 queue에 넣을때마다 그 부분은 1로 바꿔주며 방문했음을 체크.
* (백준 2589)

```js
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
    let copyVisited = JSON.parse(JSON.stringify(visited))
 
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
```



