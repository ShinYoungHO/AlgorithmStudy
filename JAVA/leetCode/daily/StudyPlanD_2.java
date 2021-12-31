package category.ns.leetSolved;

public class StudyPlanD_2 {
    public static class FindMinimumInRotatedSortedArray_153 {
        class Solution {
            public int findMin(int[] nums) {
                int l = 0;
                int r = nums.length-1;
                int min = Integer.MAX_VALUE;

                while(l <= r){
                    int m = (l+r)>>1;
                    min = Math.min(min, nums[m]);
                    if(nums[l] <= nums[m] && nums[m] >= nums[r]){
                        l = m+1;
                    } else {
                        r = m-1;
                    }
                }
                return min;
            }
        }
    }

    public static class FindPeakElement_162 {
        class Solution {
            public int findPeakElement(int[] nums) {
                int l=0;
                int r= nums.length-1;

                while(l <= r){

                    int n = (l+r) >> 1;

                    if(n == 0 || (nums[n] > nums[n-1])){
                        if(n == nums.length-1 || (nums[n] > nums[n+1])){
                            return n;
                        }else{
                            l = n+1;
                        }
                    }else{
                        r = n-1;
                    }
                }

                return 0;
            }

        }
    }
}
