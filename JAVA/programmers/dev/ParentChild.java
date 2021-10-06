package programmers.dev;
import java.util.Map;
import java.util.HashMap;

class ParentChild {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] result = new int[enroll.length];
        Map<String, Seller> sellerMap = new HashMap<>();
        String parent, child;
        sellerMap.put("-", new Seller("-"));

        for(int i = 0; i < enroll.length; i++){
            parent = referral[i];
            child = enroll[i];
            sellerMap.put(child, new Seller(child, parent));
        }

        for(int i = 0; i < seller.length; i++){
            Seller current = sellerMap.get(seller[i]);
            int income = amount[i]*100;
            while(current.parent != null){
                int profit = income/10;
                int rest = income-profit;
                current.income += rest;
                if(profit == 0) break;
                income = profit;
                current = sellerMap.get(current.parent);
            }
        }
        for(int i = 0; i < enroll.length; i++){
            result[i] = sellerMap.get(enroll[i]).income;
        }
        return result;
    }
}


class Seller{
    String name, parent;
    int income = 0;
    public Seller(String name, String parent){
        this.name = name;
        this.parent = parent;
    }
    public Seller(String name){
        this.name = name;
    }
}