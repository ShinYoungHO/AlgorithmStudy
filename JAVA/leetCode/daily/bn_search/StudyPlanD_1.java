package category.ns.leetSolved;

public class StudyPlanD_1 {
    class Solution1 {
        public int[] searchRange(int[] nums, int target) {
            int[] ans = new int[]{-1, -1};

            int l = 0;
            int r = nums.length-1;
            int m;
            int idx = -1;

            while(l <= r){
                m = (l+r) >> 1;
                if(nums[m]<=target){
                    l = m+1;
                    if(nums[m] == target) idx = Math.max(idx, m);
                } else {
                    r = m-1;
                }
            }

            if(idx == -1) return ans;
            ans[1] = idx;
            for(int i = idx; i >= 0; i--){
                if(nums[i] == target){
                    ans[0] = i;
                } else break;
            }

            return ans;
        }
    }

    class Solution2 {
        public int search(int[] nums, int target) {
            int l = 0;
            int r = nums.length-1;

            while(l<=r){
                int m = (r+l) >> 1;
                if(nums[m] == target) return m;
                if(nums[l] <= nums[m]){
                    if(nums[l] <= target && target <= nums[m]){
                        r = m-1;
                    } else {
                        l = m+1;
                    }
                } else {
                    if(nums[m] <= target && target <= nums[r]){
                        l = m+1;
                    } else {
                        r = m-1;
                    }
                }

            }
            return -1;
        }
    }

    class Solution3 {
        public boolean searchMatrix(int[][] matrix, int target) {
            int INF = Integer.MAX_VALUE;
            int mi = matrix[0].length-1;
            int l = 0;
            int r = matrix.length-1;
            int m;

            int ans = INF;
            while(l <= r){
                m = (l+r)>>1;
                if(matrix[m][mi] < target){
                    l = m+1;
                } else {
                    ans = Math.min(ans, m);
                    r = m-1;
                }
            }

            if(ans == INF) return false;

            l = 0;
            r = mi;
            while(l <= r){

                m = (l+r)>>1;
                if(matrix[ans][m] == target) return true;
                if(matrix[ans][m] < target){
                    l = m+1;
                } else {
                    r = m-1;
                }
            }
            return false;
        }
    }
}
