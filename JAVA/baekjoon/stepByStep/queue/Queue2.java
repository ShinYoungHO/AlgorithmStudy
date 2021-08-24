package stepByStep.queue;

import java.io.*;


public class Queue2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Queue1 q = new Queue1();
        int n = Integer.parseInt(br.readLine());

        for(int i = 1; i <= n; i++){
            String[] input = br.readLine().split(" ");
            String command = input[0];
            if(command.equals("push")){
                q.push(Integer.parseInt(input[1]));
            } else if(command.equals("pop")){
                bw.write(q.pop()+"\n");
            } else if(command.equals("size")){
                bw.write(q.size()+"\n");
            } else if(command.equals("empty")){
                bw.write(q.empty()+"\n");
            } else if(command.equals("front")){
                bw.write(q.front()+"\n");
            } else if(command.equals("back")){
                bw.write(q.back()+"\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

class Queue1 {
    Element first;
    Element last;
    int size = 0;

    public int push(int v){
        Element e = new Element(v);
        if(empty() == 1) {
            this.first = e;
            this.last = e;
        } else {
            this.last.setNext(e);
            this.last = e;
        }

        this.size++;
        return v;
    }

    public int pop(){
        if(empty() == 1) return -1;
        Element result = this.first;
        this.first = result.next;
        result.setNext(null);

        this.size--;
        return result.getValue();
    }

    public int size(){
        return this.size;
    }

    public int empty(){
        if(size == 0) return 1;
        return 0;
    }

    public int front(){
        if(empty() == 1) return -1;
        return this.first.getValue();
    }
    public int back(){
        if(empty() == 1) return -1;
        return this.last.getValue();
    }
}

class Element {
    int value;
    Element next;

    public Element (int v){
        value = v;
    }
    public Element getNext() {
        return next;
    }

    public int getValue() {
        return value;
    }

    public void setNext(Element next) {
        this.next = next;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

