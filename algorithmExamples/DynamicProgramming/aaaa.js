function orderOfPresentation(n, k) {
    //3이고 k가 3
    //n! = 6
    //[1,2,3]
    //[2,3,1]
    //[[],[1,2,3],[1,3,2],//[2,1,3],[2,3,1],//[3,1,2],[3,2,1]] => 2
    //[0,1,3]
      const order = [];
      for(let i = 0; i <= n; i++){
        order.push(i);
      }
      
      let factorial = [1];
      for(let i = 1; i <=n; i++){
        factorial[i] = factorial[i-1]*i
      }
      let result = null;
      if (Array.isArray(k) === true){
        result = 0;
        while(order.length>=2){
            // console.log(result)

          result += factorial[n-1]*(order.indexOf(k[0]));
          console.log(result)
          k.shift();
          order.splice(k[0]-1,1)

          n--;
        }
        return result;
      }
      
    }


   console.log( orderOfPresentation(3, [2, 3, 1]))