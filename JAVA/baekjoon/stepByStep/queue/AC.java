package stepByStep.queue;

import netscape.javascript.JSObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class AC {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            String commands = br.readLine();
            int l = Integer.parseInt(br.readLine());
            int[] arr = Arrays.stream(br.readLine().replace("[","").split("\\W")).mapToInt(Integer::parseInt).toArray();
            DoubleLinkedList dr = new DoubleLinkedList();

            for(int j = 0; j < l; j++){
                dr.add(arr[j]);
            }

            boolean isValid = true;
            for(int j = 0; j < commands.length(); j++){
                char command = commands.charAt(j);
                if(command == 'R') dr.setReverse();
                else if(command == 'D'){
                    boolean result = dr.poll();
                    if(!result) {
                        isValid = false;
                        break;
                    }
                }
            }
            if(isValid) sb.append(dr+"\n");
            else sb.append("error\n");
        }
        System.out.println(sb);
    }
}

class DoubleLinkedList{
    boolean isReverse = false;
    ListElement first;
    ListElement last;
    int size = 0;

    public void add(int v){
        ListElement le = new ListElement(v);
        if(isEmpty()) {
            this.first = le;
            this.last = le;
        } else {
            le.left = this.last;
            this.last.right = le;
            this.last = le;
        }
        this.size++;
    }

    public boolean poll(){
        if(isEmpty()) return false;
        else if(size == 1) {
            first = null;
            last = null;
            this.size--;
            return true;
        }
        if(isReverse){
            ListElement value = this.last;
            this.last = value.left;
            this.last.right = null;
        } else {
            ListElement value = this.first;
            this.first = value.right;
            this.first.left = null;
        }
        this.size--;
        return true;
    }

    public void setReverse(){
        this.isReverse = !isReverse;
    }


    @Override
    public String toString(){
        if(isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder();
        if(isReverse){
            ListElement le = last;
            while(true){
                sb.append(","+le.value);
                if(le.left == null) break;
                le = le.left;
            }
        } else {
            ListElement le = first;
            while(true){
                sb.append(","+le.value);
                if(le.right == null) break;
                le = le.right;
            }
        }
        return "["+sb.substring(1)+"]";
    }

    public boolean isEmpty() {
        return size > 0 ? false : true;
    }

}

class ListElement {
    int value;
    ListElement left;
    ListElement right;
    public ListElement(int v){
        this.value = v;
    }
}