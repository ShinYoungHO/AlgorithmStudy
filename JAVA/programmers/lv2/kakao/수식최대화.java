import java.util.ArrayList;

class Solution {
    ArrayList<Long> numbers = new ArrayList<>();
    ArrayList<String> operators = new ArrayList<>();
    long ans;
    public long solution(String expression) {
        this.ans = 0;
        String[] seq = new String[]{"+", "-", "*"};
        boolean[] seqMap = new boolean[3];
        String[] order = new String[3];
        
        String[] nums = expression.split("\\W");
        String[] ops = expression.split("(\\d)+");
        
        for(int i = 0; i < nums.length; i++){
            numbers.add(Long.parseLong(nums[i]));
            if(i != 0) operators.add(ops[i]);
        }
        
        execute(seq, seqMap, order, 0);
        return ans;
    }
    
    public void execute(String[] seq, boolean[] seqMap, String[] order, int idx){
        if(idx == 3){
            Long v = getValue(order);
            if(ans < Math.abs(v)){
                ans = Math.abs(v);
            }
            return;
        }
        for(int i = 0; i < 3; i++){
            if(!seqMap[i]){
                seqMap[i] = true;
                order[idx] = seq[i];
                execute(seq, seqMap, order, idx+1);
                seqMap[i] = false;
            }
        }
    }
    
    public Long getValue(String[] order){
        ArrayList<Long> tmpNums = new ArrayList<>();
        ArrayList<String> tmpOperators = new ArrayList<>();
        
        for(int i = 0; i < this.numbers.size(); i++){
            tmpNums.add(numbers.get(i));
            if(i != 0) tmpOperators.add(operators.get(i-1));
        }
        
        
        for(int i = 0; i < order.length; i++){
            String targetOperator = order[i];
            for(int j = 0; j < tmpOperators.size(); j++){
                if(tmpOperators.get(j).equals(targetOperator)){
                    tmpNums.add(j, calc(tmpNums.remove(j), tmpNums.remove(j), tmpOperators.remove(j--)));
                }
            }
        }
        return tmpNums.get(0);
    }
    
    public Long calc(Long a, Long b, String op){
        if(op.equals("*")){
            return a*b;
        } else if(op.equals("+")){
            return a+b;
        } else {
            return a-b;
        }
    }
}