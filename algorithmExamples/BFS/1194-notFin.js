/*


5 5
....1
#1###
.1.#0
....A
.1.#.

-1

거리는 queue에 할당하고 
무한루프를 막기 위해 뭔갈해야댐
키를 모두 찾은 경우?
1. 키를 획득했는데 같은 키를 또 획득하는 경로
2. 맞는 키가 없는 상태에서 문을 만나는 경로
3. 
visited를 밖에서부터 채우면



key 체크

*/
const fs = require('fs')
const input = fs.readFileSync('/dev/stdin').toString().split('\n')
const [m,n] = input.shift().split(' ');
const keys = {};
const [p,q] = [];//start
const visited = [];

for(let i = 0; i < m/1; i++){
    visited.push(Array(m/1).fill(false))
}

for(let i = 0; i < n/1/1; i++){
    for(let j = 0; j < n/1; j++){
        if(input[i][j]===0){
            [p,q] = [i,j];
        }
    }
    if([p,q].length) break;
}
const DFS = function(){
    const dx = [0,1,0,-1];
    const dy = [1,0,-1,0];
    const queue = [];
    queue.push([...[p,q],0]);
    while(queue.length){
        let [x,y,z] = queue.shift();//[x,y]
        for(let i = 0; i < 4; i++){
            let [a,b] = [x+dx[i],y+dy[i]]//next coord
            if( a >= 0 && a <= m/1 && b >= 0 && b <= n/1 ){//mXn
                if(input[a][b] === '#') continue;

                else if(input[a][b] === '.') {
                    queue.push([a,b,z+1])
                }

                else if(input[a,b] === '1'){ 
                    return z+1
                }

                else if(input[a,b] === input[a,b].toUpperCase()){//다음 좌표가 문인 경우
                    if( !queue.length ){
                        return -1;//////-1없어도 되나 체크
                    }
                    else if( !keys[input[a][b]] ){//키가 없다
                        continue;
                    }
                    else{//키가 있다.
                        queue.push(input[a,b]);
                    }

                }
                else{//키 만난 경우 
                    if(keys[input[a][b]]) continue;
                    else{
                        keys[input[a][b]] = true;
                        queue.push(input[a][b]);
                    }
                }
            }
        }
    }
}
// DFS()
console.log(0101&1010)

