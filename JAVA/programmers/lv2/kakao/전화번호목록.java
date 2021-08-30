import java.util.Set;
import java.util.HashSet;

class Solution {
    public boolean solution(String[] phone_book) {
        Set<String> book = new HashSet<>();
        for(int i = 0; i < phone_book.length; i++) book.add(phone_book[i]);
        
        for(int i = 0; i < phone_book.length; i++){
            String phone_number = phone_book[i];
            int range = phone_number.length()-1;
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < range; j++){
                sb.append(phone_number.charAt(j));
                if(book.contains(sb.toString())) return false;
            }
        }
        return true;
    }
}