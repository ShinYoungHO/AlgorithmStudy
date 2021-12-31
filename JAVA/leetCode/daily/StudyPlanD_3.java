package category.ns.leetSolved;
import java.util.*;

public class StudyPlanD_3 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode ans = null;
        ListNode ansP = null;
        ListNode point = head;

        while(point != null){
            int v = point.val;
            if(point.next != null && v == point.next.val){
                if(ansP!=null) ansP.next = null;
                while(point.val == v){
                    ListNode tmp = point;
                    point = tmp.next;
                    tmp.next = null;
                    if(point == null) break;
                }
            } else {
                if(ans == null){
                    ans = point;
                    ansP = point;
                } else {
                    ansP.next = point;
                    ansP = ansP.next;
                }
                point = point.next;
            }
        }

        return ans;
    }


    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < n ; i++){
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int l = i + 1;
            int r = n - 1;
            while(l<r){

                if(l > i+1 && nums[l]==nums[l-1]) {
                    l++;
                    continue;
                }

                if(r < n-1 && nums[r]==nums[r+1]){
                    r--;
                    continue;
                }

                if(nums[i]+nums[l]+nums[r] == 0) {
                    ans.add(List.of(nums[i], nums[l], nums[r]));
                    l++; r--;
                    continue;
                }

                if((nums[i]+nums[l]+nums[r]) > 0) r--;
                else l++;

            }
        }
        return ans;
    }

}
