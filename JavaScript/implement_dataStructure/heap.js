class Heap{
    constructor(){
        this.arr = [];
    }
    reheapup(self,idx){
        if(idx){
            let parentIdx = parseInt(idx/2);
            if(self.arr[idx] > self.arr[parentIdx]){ // 큰 경우 상단으로 올림.
                let temp = self.arr[idx];
                self.arr[idx] = self.arr[parentIdx];
                self.arr[parentIdx] = temp;
                this.reheapup(self,parentIdx);//다시 확인
            }
        }
    }
    reheapDown(self,idx){
        let left = 0;
        let right = 0;
        let large = null;//
        if(idx*2 < self.arr.length){
            left = self.arr[idx*2]
            if(idx*2+1 < self.arr.length){
                right = self.arr[idx*2+1]
            }
            large = left > right ? idx*2 : idx*2 + 1; //right가 없으면 left 있으면 right idx 할당
            if(self.arr[idx] < self.arr[large]){//작은 경우 내림
                let temp = self.arr[idx];
                self.arr[idx] = self.arr[large];
                self.arr[large] = temp;
                this.reheapDown(self, large)
            }
        }
    }

    insert(val){ //맨 끝에 넣고 조건에 맞을때까지 올려줌
        let last = this.arr.length;
        this.arr[last] = val;
        this.reheapup(this, last)
    }

    delete(){
        if(this.arr.length===0) return false;
        let del = this.arr[0];
        this.arr[0] = this.arr.pop();
        this.reheapDown(this,0)
        return del;
    }
    sort(){
        let sort = [];
        let count = this.arr.length;
        for(let i = 0; i < count; i++){
            sort.push(this.delete())
        }
        return sort;
    }
}