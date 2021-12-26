package category.ns.leetSolved;

import java.util.PriorityQueue;

public class MedianOfTwoSortedArray_4 {
    class Solution {
        PriorityQueue<Integer> high, low;
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            high = new PriorityQueue<>((n1, n2)-> n2-n1);
            low = new PriorityQueue<>();

            solve(nums1);
            solve(nums2);

            if(high.size() == low.size()){
                return (double)(high.peek()+low.peek())/2;
            } else {
                if(high.size()>low.size()){
                    return (double)high.peek();
                } else {
                    return (double)low.peek();
                }
            }
        }

        void solve(int[] nums){
            // high, low
            for(int i = 0; i < nums.length; i++){
                int v = nums[i];
                if(high.size() == 0) {
                    high.add(v);
                    continue;
                }

                if(low.size() == 0){
                    if(high.peek() > v){
                        low.add(high.poll());
                        high.add(v);
                    } else {
                        low.add(v);
                    }
                    continue;
                }

                if(low.peek() < v){
                    low.add(v);
                } else {
                    high.add(v);
                }

                if(high.size() >= low.size()+2){
                    low.add(high.poll());
                }

                if(low.size() >= high.size()+2){
                    high.add(low.poll());
                }

            }
        }
    }

    class Bet {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {

            int n1 = nums1.length;
            int n2 = nums2.length;
            if(n1>n2)
                return findMedianSortedArrays(nums2, nums1);


            //  n1 <= n2
            int low = 0, high = n1;
            while(low <= high){
                int Amid = low+(high-low)/2;
                int Bmid = (n1+n2+1)/2 - Amid;

                double l1 = Amid == 0 ? Integer.MIN_VALUE : nums1[Amid-1];
                double h1 = Amid == n1? Integer.MAX_VALUE : nums1[Amid];

                double l2 = Bmid == 0 ? Integer.MIN_VALUE : nums2[Bmid-1];
                double h2 = Bmid == n2? Integer.MAX_VALUE : nums2[Bmid];

                if(l1<=h2 && l2<=h1){
                    if((n1+n2) % 2 == 0 ){
                        return (Math.max(l1,l2) + Math.min(h1,h2))/2;
                    }else
                        return Math.max(l1,l2);
                }else if(l2>h1)
                    low = Amid+1;
                else
                    high = Amid-1;
            }
            return -1;
        }
    }


}
