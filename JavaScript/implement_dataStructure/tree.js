class Tree {
    constructor(value) {
      this.value = value;
      this.children = [];
    }
    insertNode(value) {
      const childNode = new Tree(value);
      this.children.push(childNode);
    }  
    contains(value) {
      if (this.value===value) {
        return true;
      }
      for(let i = 0; i < this.children.length; i++){
        let result = this.children[i].contains(value);
        if(result) return result;
      }
      return false;
    }
  }
  
  class BinarySearchTree{
      constructor(value){
          this.value = value;
          this.left = null;
          this.right = null;
      }
      insertNode(val){
          let node = new BinarySearchTree(val);
          if(this.value >= val){
              if( !this.left ){
                  this.left = node;
              }else{
                  this.left.insertNode(val);
              }
          }
          else{
              if( !this.right ){
                  this.right = node;
              }else{
                  this.right.insertNode(val);
              }
          }
      }
      contains1(val){//Bool type
          let result = false;
          if(this.value === val){
              result = true;
          } 
          else if(this.value >= val){
              if( this.left ){
                  result = this.left.contains1(val);
              }
          }
          else{
              if( this.right ){
                  result = this.right.contains1(val);
              }
          }
          return result;
      }
      contains2(val){//Node type
          let result = null;
          if(this.value === val){
              result = this;
          } 
          else if(this.value >= val){
              if( this.left ){
                  result = this.left.contains2(val);
              }
          }
          else{
              if( this.right ){
                  result = this.right.contains2(val);
              }
          }
          return result;
      }
  }