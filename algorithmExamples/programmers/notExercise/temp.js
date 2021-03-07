// function popularRestaurant(n, menus) {
//     // TODO: 여기에 코드를 작성합니다.
//     // 시간을 이분 탐색 합니다.
//     menus = menus.sort((a,b)=>a-b)
//     let mps = menus.reduce((acc,cur)=>{
//       acc+=1/cur;
//       return acc;
//     },0);
//     let meanTimeFloor = parseInt(n/mps);
//     let temp = 0;
//     for(let i = 0; i < menus.length; i++){
//       temp+= parseInt(meanTimeFloor/menus[i]);
//     }
  
//     while(temp<n){
//       meanTimeFloor++;
//       for(let i = 0; i < menus.length; i++){
//         let isInt = meanTimeFloor/menus[i];
//         if(isInt%1===0){
//           temp+= 1
//         }
//         if(temp>=n) break;
//       }
//     }
//     return [ meanTimeFloor, parseInt(meanTimeFloor/menus[0]) ]
  
//   }
  //1초에 만들어지는 음식의 수는 menus의 역수를 모두 더한 값.
  
  
  //10,[1,2,3,4] - 평균 4.8초 
  //                8개 - 4초
  
  //6 [7, 10] - 평균 24.70
  //             5 - 24초

// class Stack {
//   constructor() {
//     this.data = {};
//     this.top = 0;
//   }
//   size() {
//     return this.top;
//   }
//   push(el) {
//     this.data[this.top] = el;
//     this.top += 1;
//   }
//   pop() {
//     if (this.size() <= 0) {
//       return;
//     }
//     const result = this.data[this.top-1];
//     delete this.data[this.top-1];
//     this.top -= 1;
//     return result;
//   }
//   contains(el){
//     for(let keys in this.data){
//       if(this.data[keys]===el){
//         return true;
//       }
//     }
//     return false;
//   }
//   peek(){
//     return !!this.top&&this.data[this.top-1]
//   }
//   isEmpty(){
//     return !this.top
//   }
// }

// let a = new Stack
// a.push(10)
// console.log(!!1)
// console.log(a)
// console.log(a.peek())
// console.log(a.isEmpty())

class Node{
  constructor(el){
    this.data = el;
    this.next = null;
  }    
}

class Queue {
  constructor() {
    this.count = 0;
    this.head = null;
    this.rear = null;
  }
  
  size() {
    return this.count;
  }
  enqueue(el) {
    let node = new Node(el)
    if( !this.head ){
      this.head = node
    }else{
      this.rear.next = node;///
    }
    this.rear = node;
    return ++this.count
  }	
  dequeue() {
    if( !this.head ){
      return false;
    }
    let data = this.head.data;
    this.head = this.head.next;

    this.count--;
    return data;
  }
  front(){
    return this.head && this.head.data;
  }
}

let a = new Queue()
a.enqueue(1)
a.enqueue(10)
a.enqueue(100)
// a.dequeue(1)
console.log(a.size())
// function aa(){
//   let a = 1;
//   let b = 1;
//   return [++a,b++]
// }
// console.log(aa())