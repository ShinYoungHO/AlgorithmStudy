function studentReports(students) {
    const onlyFemales = students.filter(function (el) {
      return el.gender === 'female';
    });
  
    return onlyFemales.map(function (el) {
      const sum = el.grades.reduce(function (acc, cur) {
        return acc + cur;
      }, 0);
      const avg = sum / el.grades.length;
  
      el.grades = avg;
      return el;
    });
  }

  debugger;
function getLengthOfLongestElement(arr) {
  // 여기에 코드를 입력하세요

  let result = arr.reduce(function(sum,ele){
    if(ele.length>sum.length){
      return sum=ele;
    }else{
      return sum=sum;
    }
  },'');

  return result.length;
}

getLengthOfLongestElement(['one', 'two', 'three'])


function isPrime(element, index, array) {
    var start = 2;
    while (start <= Math.sqrt(element)) {
      if (element % start++ < 1) {
        return false;
      }
    }
    return element > 1;
  }
  
  console.log([4, 6, 8, 12].find(isPrime)); // undefined, not found
  console.log([4, 5, 8, 12].find(isPrime)); // 5