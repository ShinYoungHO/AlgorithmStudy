function solution(board) {
    const pq = [[0,0,0,1], [0,0,0,2]];
    const len = board.length;
    let min = Number.MAX_SAFE_INTEGER;
    const dx = [-1, 0, 1, 0];
    const dy = [0, 1, 0, -1];
    const visited = Array.from(new Array(len), () => new Array(len).fill(min));
    while(pq.length){
        const [x, y, c, d] = dequeue(pq);
        if(x === len-1 && y === len-1){
            if(c < min) min = c;
            continue;
        }
        for(let i = 0; i < 4; i++){
            const nx = x + dx[i];
            const ny = y + dy[i];
            const nc = (d+i)%2 === 0 ? c+1 : c+6;

            if(nx < 0 || nx >= board.length || ny < 0 || ny >= board.length) continue;
            if(board[nx][ny]) continue;
            if(visited[nx][ny] < nc) continue;

            enqueue(pq, [nx, ny, nc, i])
            visited[nx][ny] = visited[nx][ny] > nc ? nc : visited[nx][ny];
        }
    }
    return min*100;
}


function enqueue(pq, v){
    pq.push(v);
    if(pq.length === 1) return;
    let curIdx = pq.length-1;
    let parIdx = Math.floor((curIdx-1)/2);
    while(parIdx >= 0 && pq[curIdx][2] < pq[parIdx][2]){
        [pq[parIdx], pq[curIdx]] = [pq[curIdx], pq[parIdx]];
        curIdx = parIdx;
        parIdx = Math.floor((curIdx-1)/2);
    }
}

function dequeue (pq){

    if(pq.length === 1) return pq.pop();
    let curIdx = null;
    let minIdx = 0;
    const result = pq[minIdx];
    pq[minIdx] = pq.pop()
    while(curIdx !== minIdx){
        curIdx = minIdx;
        const leftIdx = curIdx*2+1;
        const rightIdx = curIdx*2+2;
        if(leftIdx < pq.length && pq[curIdx][2] > pq[leftIdx][2]){
            curIdx = leftIdx;
        }
        if(rightIdx < pq.length && pq[curIdx][2] > pq[rightIdx][2]){
            curIdx = rightIdx;
        }
        [pq[minIdx], pq[curIdx]] = [pq[curIdx], pq[minIdx]];
    }
    return result;
}

///////////////////////////////////////////




function solution(board) {
    const len = board.length;
    let min = Number.MAX_SAFE_INTEGER;
    const visited = Array.from(new Array(len), () => new Array(len).fill(min));
    const q = [[0,0,0,1], [0,0,0,2]];
    const dx = [-1, 0, 1, 0];
    const dy = [0, 1, 0, -1];
    
    while(q.length){
        const [x, y, c, d] = q.shift();
        if(x === board.length-1 && y === board.length-1){
            if(c < min) min = c;
        }
        for(let i = 0; i < 4; i++){
            const nx = x + dx[i];
            const ny = y + dy[i];
            const nc = (d+i)%2 === 0 ? c+1 : c+6;
            if(nx < 0 || nx >= len || ny < 0 || ny >= len) continue;
            if(visited[nx][ny] < nc) continue;
            if(board[nx][ny]) continue;
            
            q.push([nx, ny, nc, i]);
            visited[nx][ny] = visited[nx][ny] > nc ? nc : visited[nx][ny];
        }
    }
    return min*100;
}

let a = 
solution([[0, 0, 1, 0], [0, 0, 0, 0], [0, 1, 0, 1], [1, 0, 0, 0]])
console.log(a)