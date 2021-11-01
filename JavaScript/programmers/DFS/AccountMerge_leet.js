var accountsMerge = function(accounts) {
  let map = {};
  // build the map;
  for(let account of accounts){
      let [name, ...emails] = account
      if(!map[name]) {
          map[name] = {}; // 각 account마다 객체를 가지고 있음.key 에는 이메일, key의 밸류엔 이메일이 들어가는데, 부모요소 이메일이 들어감.
      };
      if(emails.length === 1){
          map[name][emails[0]] = find(name, emails[0]);
      } else {
          for(let i = 0; i<emails.length-1; i++){
              union(name, emails[i], emails[i+1]);
          }
      }
  }

  
  function union(name, a, b){
      let parentOfA = find(name, a);
      let parentOfB = find(name, b);
      
      // path compression
      Object.keys(map[name])
          .forEach(key => {
              if(map[name][key] === parentOfB) map[name][key] = parentOfA;
          })
  }
  
  function find(name, a){//a : 이메일
      if(typeof map[name][a] === "undefined") map[name][a] = a;
      if(map[name][a] !== a){// 들어온 이메일의 부모요소가 a와 다를경우 => 부모요소를 가질 경우
          map[name][a] = find(name, map[name][a]);// 다시 부모요소를 찾아가며 할당해준다,
      }
      console.log(map)
      console.log('\n')
      console.log('\n')
      return map[name][a];// 부모요소를 반환한다.
  }

  // console.log(map)
  // destructure the map
  let res = [];
  Object.keys(map).forEach(name => {
      let accounts = [...new Set(Object.values(map[name]))];
      accounts.forEach(account => {
          let emails = Object.keys(map[name]).filter(email => map[name][email] === account).sort();
          res.push([name, ...emails]);
      })
  })
  return res;
};

// console.log(accountsMerge([
//   ["Ethan","Ethan1@m.co","Ethan2@m.co","Ethan0@m.co"],
//   ["David","David1@m.co","David2@m.co","David0@m.co"],
//   ["Lily","Lily0@m.co","Lily0@m.co","Lily4@m.co"],
//   ["Gabe","Gabe1@m.co","Gabe4@m.co","Gabe0@m.co"],
//   ["Ethan","Ethan2@m.co","Ethan1@m.co","Ethan0@m.co"]
// ]))
console.log(accountsMerge([
  ["John","johnsmith@mail.com","john_newyork@mail.com"],
  ["John","johnsmith@mail.com","john00@mail.com"],
  ["Mary","mary@mail.com"],
  ["John","johnnybravo@mail.com"]
]))