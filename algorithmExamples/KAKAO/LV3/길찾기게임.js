function solution(nodeinfo) {
  const rearrange = nodeinfo
    .map((el, idx) => [...el, idx + 1])
    .sort((a, b) => {
      return a[1] !== b[1] ? b[1] - a[1] : a[0] - b[0];
    });
  const rootNode = getNodeFromInfos(...rearrange[0]);
  for (let i = 1; i < rearrange.length; i++) {
    saveNode(rootNode, getNodeFromInfos(...rearrange[i]));
  }

  const preRes = [];
  const postRes = [];
  getPreOrd(rootNode, preRes);
  getPostOrd(rootNode, postRes);
  return [preRes, postRes];
}

function saveNode(root, node) {
  let curNode = root;
  while (true) {
    if (curNode.y > node.y) {
      if (curNode.x > node.x) {
        if (curNode.left) {
          curNode = curNode.left;
          continue;
        } else {
          curNode.left = node;
          break;
        }
      } else {
        if (curNode.right) {
          curNode = curNode.right;
          continue;
        } else {
          curNode.right = node;
          break;
        }
      }
    } else {
      break;
    }
  }
}
function getNodeFromInfos(x, y, v) {
  return {
    value: v,
    x,
    y,
    left: null,
    right: null,
  };
}

function getPostOrd(node, postRes) {
  if (node.left) {
    getPostOrd(node.left, postRes);
  }
  if (node.right) {
    getPostOrd(node.right, postRes);
  }
  postRes.push(node.value);
}

function getPreOrd(node, preRes) {
  preRes.push(node.value);
  if (node.left) {
    getPreOrd(node.left, preRes);
  }
  if (node.right) {
    getPreOrd(node.right, preRes);
  }
}
