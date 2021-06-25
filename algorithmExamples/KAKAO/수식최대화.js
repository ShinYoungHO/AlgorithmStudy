function solution(expression) {
  const orders = ["*+-", "*-+", "+-*", "+*-", "-+*", "-*+"];
  let splitted = [];
  let number = "";
  for (let i = 0; i < expression.length; i++) {
    if (expression[i].match(/[0-9]/)) {
      number += expression[i];
      if (i === expression.length - 1) {
        splitted.push(Number(number));
      }
    } else {
      splitted.push(Number(number), expression[i]);
      number = "";
    }
  }
  let max = Number.MIN_SAFE_INTEGER;
  orders.forEach((order) => {
    let sliced = splitted.slice();
    order.split("").forEach((operator) => {
      let noOperator = false;
      while (!noOperator) {
        let isExist = false;
        for (let i = 0; i < sliced.length - 1; i++) {
          if (sliced[i] === operator) {
            sliced.splice(
              i - 1,
              3,
              solve(operator, sliced[i - 1], sliced[i + 1])
            );
            isExist = true;
            i -= 3;
          }
        }
        if (!isExist) {
          noOperator = true;
        }
      }
    });
    let abs = sliced[0] < 0 ? -sliced[0] : sliced[0];
    max = max < abs ? abs : max;
  });
  return max;
}

function solve(operator, a, b) {
  [a, b] = [BigInt(a), BigInt(b)];
  switch (operator) {
    case "*":
      return a * b;
    case "+":
      return a + b;
    case "-":
      return a - b;
    default:
      return -1;
  }
}
