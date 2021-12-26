package category.ns.leetSolved;

public class AddTwoNumbers {
    /**
     * Definition for singly-linked list.

     */
    public class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }
    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            int x1,x2,sum,suf;
            x1 = l1.val;
            x2 = l2.val;
            sum = (x1+x2)%10;
            suf = (x1+x2)/10;

            ListNode ans = new ListNode(sum);
            ListNode con = ans;

            while(true){
                if(l1.next == l2.next) break;
                if(l1.next == null){
                    x1 = 0;
                } else {
                    l1 = l1.next;
                    x1 = l1.val;
                }
                if(l2.next == null){
                    x2 = 0;
                } else {
                    l2 = l2.next;
                    x2 = l2.val;
                }

                sum = (x1+x2+suf)%10;
                suf = (x1+x2+suf)/10;

                con =  con.next = new ListNode(sum);
            }
            if(suf != 0) con.next = new ListNode(suf);

            return ans;
        }
    }
}
