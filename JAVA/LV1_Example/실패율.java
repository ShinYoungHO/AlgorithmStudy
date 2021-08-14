import java.util.Arrays;

class Solution3 {
    public int[] solution(int N, int[] stages) {
        FailureWithStage[] failure = new FailureWithStage[N];
        int[] ans = new int[N];

        int userCount = stages.length;
        int stageIdx = 0;
        Arrays.sort(stages);

        for(int i = 1; i <= N; i++){
            int sameStageCount = 0;
            if(stageIdx < stages.length && i < stages[stageIdx] || userCount <= 0){
                failure[i-1] = new FailureWithStage(0.0, i);
                continue;
            }
            while( stageIdx < stages.length && i == stages[stageIdx]){
                sameStageCount++;
                stageIdx++;
            }

            failure[i-1] = new FailureWithStage((double) sameStageCount / (double) userCount,i);
            userCount = userCount-sameStageCount;
            System.out.println(userCount);
        }

        Arrays.sort(failure);
        for(int i = 0; i < failure.length; i++){
            ans[i] = failure[i].stage;
        }
        return ans;
    }
    class FailureWithStage implements Comparable<FailureWithStage> {
        private Double failure;
        private Integer stage;
        public FailureWithStage(Double failure, Integer stage){
            this.failure = failure;
            this.stage = stage;
        }

        public Double getFailure() {
            return failure;
        }

        public Integer getStage() {
            return stage;
        }

        @Override
        public int compareTo(FailureWithStage o) {
            if(this.failure - o.getFailure() > 0.0) return -1;
            else if(this.failure - o.getFailure() < 0.0) return 1;
            else return this.stage - o.getStage();
        }
    }

    public static void main(String[] args) {
        int[] arr = {4,4,4,4,4};
        Solution3 a = new Solution3();
        a.solution(8, arr);

    }
}