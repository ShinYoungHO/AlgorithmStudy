package category.ns.programmersTMP._lv3;
import java.util.*;

public class 후보키 {

    class Solution {
        public int solution(String[][] relation) {
            List<Integer> ans = new ArrayList<>();
            int result = 0;
            aa: for(int i = (1<<relation[0].length)-1; i >= 1; i--){
                for(int j = 0; j < relation.length-1; j++){
                    String[] a = relation[j];
                    cc: for(int k = j+1; k < relation.length; k++){
                        String[] b = relation[k];
                        for(int l = 0; l < a.length; l++){
                            if((i&(1<<l)) == (1<<l)){
                                if(!a[l].equals(b[l])) continue cc;
                            }
                        }
                        continue aa;
                    }
                }
                ans.add(i);
            }
            ee: for(int i = 0; i < ans.size(); i++){
                for(int j = i+1; j < ans.size(); j++){
                    if((ans.get(i)|ans.get(j))==ans.get(i)) continue ee;
                }
                result++;
            }
            return result;
        }
    }
}
