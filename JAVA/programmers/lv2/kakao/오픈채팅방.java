import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class Solution {
    public String[] solution(String[] record) {
        Map<String, User> users = new HashMap<>();
        List<Message> messages = new ArrayList<>();

        for(int i = 0; i < record.length; i++){
            StringTokenizer st = new StringTokenizer(record[i], " ");

            String type = st.nextToken();
            String uid = st.nextToken();
            String nickname = st.hasMoreTokens() ? st.nextToken() : "";
            boolean isLeave;
            User user = users.get(uid);
            
            if(type.equals("Enter")){
                if(user != null) user.nickname = nickname;
                else {
                    user = new User(uid, nickname);
                    users.put(uid, user);
                }
                isLeave = false;
                messages.add(new Message(isLeave, user));

            } else if(type.equals("Leave")){
                isLeave = true;
                messages.add(new Message(isLeave, user));
            } else if(type.equals("Change")){
                user.nickname = nickname;
            }
        }
        
        String[] result = new String[messages.size()];
        for(int i = 0; i < messages.size(); i++){
            result[i] = messages.get(i).toString();
        }
        return result;
    }
}

class Message{
    User user;
    boolean isLeave;
    public Message(boolean isLeave, User user){
        this.user = user;
        this.isLeave = isLeave;
    }
    @Override
    public String toString(){
        return isLeave ? this.user.nickname+"님이 나갔습니다." : this.user.nickname+"님이 들어왔습니다.";
    }
}

class User {
    String id;
    String nickname;
    public User(String userId, String userNickname){
        this.id = userId;
        this.nickname = userNickname;
    }
}