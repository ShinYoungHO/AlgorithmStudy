package hackerRank.api;

import org.json.JSONArray;
import org.json.JSONObject;
// f
import java.io.*;
import java.net.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;

class Result {
    static String src = "https://jsonmock.hackerrank.com/api/football_matches";
    static JSONParser parser;
    public static int getTotalGoals(String team, int year) throws IOException, Exception{
        System.out.println(team+":"+ year);
        String[] targetURIs = { getURI("team1", change(team), year), getURI("team2", change(team), year) };
        BufferedReader br;
        StringBuilder sb;
        URL url;
        HttpURLConnection connection = null;


        parser = new JSONParser();
        int res = 0;
        for(int i = 0; i < 2; i++){
            long page = 1;
            while(true){
                String target = targetURIs[i];

                try{
                    System.out.println(target+"&page="+page);
                    url = new URL(target+"&page="+page);
                    // url = new URL(target);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.addRequestProperty("Content-Type", "application/json");
                    System.out.println(connection.getResponseCode());
                    if(connection.getResponseCode() != 200) return 0;

                } catch(Exception e){
                    e.printStackTrace();
                }
                if(connection == null) throw new Exception();

                br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));

                String line;
                sb = new StringBuilder();
                while((line = br.readLine()) != null){
                    sb.append(line.trim());
                }
                System.out.println(sb);
                try{
                    JSONObject obj = (JSONObject) parser.parse(sb.toString());
                    if((Long)obj.get("total") == 0) break;
                    JSONArray jsArr = (JSONArray) obj.get("data");

                    for(int j = 0; j < jsArr.size(); j++){
                        JSONObject tmp = (JSONObject) jsArr.get(j);
                        if(i == 0) res+= Integer.parseInt((String)tmp.get("team1goals"));
                        else res+=Integer.parseInt((String)tmp.get("team2goals"));
                    }

                    Long cur = (Long) obj.get("total_pages");
                    if(cur <= page) break;
                    page++;
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }

        return res;
    }

    static String getURI(String s, String team, int year){
        return src+"?year="+year+"&"+s+"="+team;
    }

    static String change(String s){
        return s.trim().replaceAll("\\W", "");
    }

}

public class RESTAPI2 {
    public static void main(String[] args) throws Exception {

        new Result().getTotalGoals("1",1);
    }
}
// 11 13 12 67 117
