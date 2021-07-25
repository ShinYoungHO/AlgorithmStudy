class Solution{
    String solution(String word){
        int a = word.length();
        String word1;
        if ( a % 2 == 0 ){
            word1 = word.substring(a/2 - 1, (a/2) + 1);
        } else{
            word1 = word.substring((a/2), (a/2) + 1);
        }
        return word1;
    }
}