// 내 풀이
const updateMatrix = function(matrix) {
  const rLen = matrix.length;
  const cLen = matrix[0].length;
  const dr = [0,1,0,-1];
  const dc = [1,0,-1,0];
    
  const BFS = function (x, y) {
    // let visited = [];
    // for(let i = 0; i < rLen; i++){ 
    //   visited.push(Array(cLen).fill(false))
    // }
    let queue = [[x,y,0]];
    // visited[x][y] = true;
    while(queue.length>0){
      let [r,c,num] = queue.shift();
      for(let j = 0; j < 4; j++){
        const nr = r + dr[j];
        const nc = c + dc[j];
        if(nr >= rLen || nr < 0 || nc >= cLen || nc < 0 ) continue;
        // if(matrix[nr][nc] !== 0 && visited[nr][nc] === false){
        if(matrix[nr][nc] !== 0){
          queue.push([nr,nc,num+1])
          // visited[nr][nc] = true;
        }else if(matrix[nr][nc] === 0){
          return num+1;         
        }
      }
    }   
  }  
  
  let result = [];
  for(let k = 0; k < rLen; k++){
    result.push(matrix[k].slice())
  }
  for(let i = 0; i < rLen; i++){
    for(let j = 0; j < cLen; j++){
      if(result[i][j] !== 0){
        result[i][j] = BFS(i,j)
      }
    }
  }
  return result;
};

/*
// 메모리
var updateMatrix = function(matrix) {
  var res = [];
  var queue = new MyQueue();
  for (let y=0; y<matrix.length; y++) {
      res.push([]);
      for (let x=0; x<matrix[y].length; x++) {
          if (matrix[y][x] === 0) queue.enqueue({ x, y, distance: -1 });
      }
  }
  while (!queue.isEmpty()) {
      var { x, y, distance } = queue.dequeue();
      if (x < 0 || x >= matrix[0].length) continue;
      if (y < 0 || y >= matrix.length) continue;
      if (res[y][x] !== undefined) continue;
      res[y][x] = distance + 1;
      queue.enqueue({ x: x+1, y, distance: distance+1 });
      queue.enqueue({ x: x-1, y, distance: distance+1 });
      queue.enqueue({ x, y: y+1, distance: distance+1 });
      queue.enqueue({ x, y: y-1, distance: distance+1 });
  }
  return res;
};

var MyQueue = function() {
  this._head = {};
  this._tail = {};
  this._head.next = this._tail;
  this._tail.prev = this._head;
};

MyQueue.prototype.enqueue = function(value) {
  var a = this._tail.prev;
  var b = { value };
  var c = this._tail;
  a.next = b;
  b.prev = a;
  b.next = c;
  c.prev = b;
};

MyQueue.prototype.dequeue = function() {
  if (this.isEmpty()) throw new Error('Cannot dequeue an empty queue');
  var a = this._head;
  var b = this._head.next;
  var c = this._head.next.next;
  a.next = c;
  c.prev = a;
  return b.value;
};

MyQueue.prototype.isEmpty = function() {
  return this._head.next === this._tail;
};

*/

/**
// runtime
var updateMatrix = function(matrix) {
        let row = matrix.length;
        let col = matrix[0].length;
        let arr = [];
        for (let i = 0; i < row; i++) {
          for (let j = 0; j < col; j++) {
            if (matrix[i][j] === 0) {
              arr.push([i, j]);
            } else {
              matrix[i][j] = Infinity; ////////////////////
            }
          }
        }
        while (arr.length) {
          let [x, y] = arr.pop();/////////////////////////
          for (let i = x - 1; i <= x + 1; i++) {
            for (let j = y - 1; j <= y + 1; j++) {
              if (i >= 0 && i < row && j >= 0 && j < col) {
                if (x == i || y == j) {
                  if (matrix[i][j] > matrix[x][y] + 1) {
                    matrix[i][j] = matrix[x][y] + 1;
                    arr.push([i, j]);/////////////////////
                  }
                }
              }
            }
          }
        }
        return matrix;
};
 */