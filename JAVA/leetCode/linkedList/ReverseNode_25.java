package category.ns.leetSolved;

import java.util.*;

public class ReverseNode_25 {
    class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            Queue<Stack<ListNode>> q = new LinkedList<>();
            ListNode ans,part,prev;
            while(true){
                part = head;
                Stack<ListNode> stack = new Stack<>();
                for(int i = 0; i < k; i++){
                    stack.push(head);
                    if(head.next == null){
                        boolean bt = false;
                        if(stack.size() == k){
                            q.add(stack);
                            bt = true;
                        }
                        if(!q.isEmpty()){
                            ans = prev = q.peek().peek();
                            while(!q.isEmpty()){
                                Stack<ListNode> st = q.poll();
                                while(!st.isEmpty()){
                                    prev = prev.next = st.pop();
                                }
                            }
                            if(!bt) prev.next = part;
                            else prev.next = null;

                            return ans;
                        } else {
                            return part;
                        }
                    } else {
                        head = head.next;
                    }
                }
                q.add(stack);
            }
        }
    }


    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
