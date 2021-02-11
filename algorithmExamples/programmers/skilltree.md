추가예정..

```js
function solution(skill, skill_trees) {
    var answer = 0;
    let temp = 0;
    for(let i = 0; i<skill.length; i++){
       answer = skill_trees.reduce(function(cur,acc){
        if( acc.indexOf(skill[i]) !== -1 ){
            acc = acc.slice(0,acc.indexOf(skill[i]))                
        }else{
            return 1;
        }
        }) 
    }
    
    return answer;
}
```