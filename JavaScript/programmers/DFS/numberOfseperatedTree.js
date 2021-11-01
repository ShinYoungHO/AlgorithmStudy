// 방향이 있는 간선들의 목록이 주어질 때, 연결된 정점의 컴포넌트(그룹들)가 몇 개인지 반환하는 함수를 작성
function connectedVertices(edges) {
    let max = Number.MIN_SAFE_INTEGER
    for(let i = 0; i < edges.length; i++){
      let [x,y] = edges[i]; 
      if(x>=y){
        max = x >= max ? x : max
      }else{
        max = y >= max ? y : max
      }
    }
  
    let matrix = [];
    for(let i = 0; i < max+1; i++){
      matrix.push(Array(max+1).fill(0))
    }
    edges.forEach(el=>{
      let [x,y] = el;
      matrix[x][y] = 1;
      matrix[y][x] = 1;
    })
    let visited = Array(max+1).fill(false);
    let count = 0;
    const DFS = function(node){
      matrix[node].forEach((el,idx)=>{
        if(el===1 && visited[idx]===false){
          visited[idx]=true;
          DFS(idx);
        }
      })
    }
    for(let i = 0; i < visited.length; i++){
      if(visited[i] === false){
        count++;
        DFS(i);
      }
    }
    
    return count;
  }