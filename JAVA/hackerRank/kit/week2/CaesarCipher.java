package hackerRank.kit.week2;

public class CaesarCipher { }


class Result {
    public String caesarCipher(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c>='a' && c<='z'){
                int tmp = c-'0';
                int rot = c-'a';
                char m = (char)(tmp-rot+(rot+k)%26+'0');
                sb.append(m);
            } else if(c<='Z'&&c>='A'){
                int tmp = c-'0';
                int rot = c-'A';
                char m = (char)(tmp-rot+(rot+k)%26+'0');
                sb.append(m);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}