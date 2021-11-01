let input = require('fs').readFileSync('/dev/stdin').toString().split('\n')
let area = input.shift().split(' ');
let X = area[0]/1;
let Y = area[1]/1; 
let dx = [0,1,0,-1]
let dy = [1,0,-1,0]//3 6 9 12
let queue = [];
let rbCord=[]; //[rx,ry,bx,by,0]
let visited = new Array(X);
// 0. visited 배열
for (let i = 0; i < X; i++) {
    visited[i] = new Array(Y);
    for (let j = 0; j < Y; j++) {
        visited[i][j] = new Array(X);
            for(let k = 0; k < X; k++){
                visited[i][j][k] = new Array(Y).fill([false]);
        }
    }
}

//1. R, B 찾기
for(let i = 1; i < X-1; i++){
    if(input[i].indexOf('R') !== -1){
        rbCord[0] = i;
        rbCord[1] = input[i].indexOf('R')
    }
    if(input[i].indexOf('B')!== -1){
        rbCord[2] = i;
        rbCord[3] = input[i].indexOf('B')
    }
}
rbCord.push(0);
queue.push(rbCord)
//2. 공 하나의 좌표를 받아와 끝까지 이동시키는 함수
function move(_rx, _ry, _dx, _dy, _c){
    while(input[_rx + _dx][_ry + _dy] !== '#' && input[_rx][_ry] !== 'O'){
        _rx += _dx;
        _ry += _dy;
        _c++;

    }
    return [_rx, _ry, _c];
}

//3. BFS
function BFS(){
    let len = null;
    while(queue.length > 0){
        let queued = queue.shift();
        for(let i = 0; i < 4; i++){
            let nrxy = move(queued[0],queued[1],dx[i],dy[i],0);
            let nbxy = move(queued[2],queued[3],dx[i],dy[i],0);
            if( input[nbxy[0]][nbxy[1]] === 'O') { 
                continue;
            }
            if( input[nrxy[0]][nrxy[1]] === 'O') {
                len = queued[4]+1;
                console.log(len)
                return; 
            }
            if(nrxy[0]===nbxy[0] && nrxy[1] === nbxy[1]){
                if( nrxy[2] > nbxy[2] ){
                    nrxy[0] -= dx[i];
                    nrxy[1] -= dy[i];
                }else{
                    nbxy[0] -= dx[i];
                    nbxy[1] -= dy[i];
                }
            }

            if( visited[nrxy[0]][nrxy[1]][nbxy[0]][nbxy[1]][0]===false){
                visited[nrxy[0]][nrxy[1]][nbxy[0]][nbxy[1]][0] = true;
                nrxy.pop();
                nbxy.pop();
                queue.push([...nrxy,...nbxy,queued[4]+1]);
            }
        }
    }
    console.log(-1);
}
BFS();
//R의 최단거리 찾는다.
//R의 최단거리 루트 중 B가 먼저 들어가면 안됨
//                 동시에 들어가면 안됨
//최단거리의 공 이동 내역 확보(x,y,-x,-y)
//부딪히면 ? 공 이동 좌표 확보

//최단거리가 존재해도 못가는 경우가 있다.
// 10 10
// ##########
// #R#...##B#
// #...#.##.#
// #####.##.#
// #......#.#
// #.######.#
// #.#...##.#
// #.#.#.#..#
// #...#.O#.#
// ##########
// O부터 R까지 최단거리를 찾아서 벽에 부딪힌 후 양 옆으로 길이있으면 R은 O에 도달 불가능
// 최단 거리가 아니고 길이 있는지 확인해야되니까 DFS?
// DFS로 길 확인 후 꺾일 때마다 좌표 저장?

