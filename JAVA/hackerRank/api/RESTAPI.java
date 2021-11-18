package hackerRank.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class RESTAPI {
}


class Result2 {
    static JSONParser parser = new JSONParser();

    public static int getTotalGoals(String team, int year) throws IOException, Exception {
        int ans = 0;
        ans += getAns(team, year, "team1");
        ans += getAns(team, year, "team2");
        return ans;
    }

    static int getAns(String team, int year, String hw) throws IOException, Exception{
        FootBallURI uriFact = new FootBallURI();
        ReqObject obj = new ReqObject();
        int res = 0;
        int page = 1;
        while(obj.page != obj.total_pages){
            String uri = uriFact.getFullUriTeam(hw, year, team, page);
            JSONObject jsonObject = getReq(uri);
            addToObj(obj, jsonObject);
            if(obj.data.size() == 0) break;
            page++;
            System.out.println(page);
        }

        for(int i = 0; i < obj.data.size(); i++){
            Data d = obj.data.get(i);

            if(d.team1.equals(team)){
                res+=d.team1goals;
                System.out.println(d.team1goals);
            } else {
                res+=d.team2goals;
                System.out.println(d.team2goals);
            }
        }
        return res;
    }

    static void addToObj(ReqObject obj, JSONObject jsonObj) throws Exception{
        try{
            JSONArray jsonArr = (JSONArray)jsonObj.get("data");
            for(int i = 0; i < jsonArr.size(); i++){
                JSONObject dataObj = (JSONObject)jsonArr.get(i);
                Long year = (Long) dataObj.get("year");
                String competition = (String)dataObj.get("competition");
                String team1goals = (String) dataObj.get("team1goals");
                String team2goals = (String) dataObj.get("team2goals");
                String round = (String) dataObj.get("round");
                String team1 = (String) dataObj.get("team1");
                String team2 = (String) dataObj.get("team2");

                Data dat = new Data(year, competition, team1goals, team2goals, round, team1, team2);

                obj.addData(dat);
            }


            obj.page = (Long)jsonObj.get("page");
            obj.per_page = (Long)jsonObj.get("per_page");
            obj.total = (Long)jsonObj.get("total");
            obj.total_pages = (Long)jsonObj.get("total_pages");
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    static JSONObject getReq(String uri) throws IOException, Exception {
        StringBuilder sb = new StringBuilder();
        try{
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            if(con.getResponseCode() == 200){
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                String line;
                while((line = br.readLine()) != null){
                    sb.append(line.trim());
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        JSONObject jsonObj = (JSONObject) parser.parse(sb.toString());
        return jsonObj;
    }

    static class FootBallURI{
        String src = "https://jsonmock.hackerrank.com/api/football_matches?";

        String getFullUriTeam(String hw,Integer year, String team1, Integer page){
            StringBuilder sb = new StringBuilder();
            sb.append(src)
                    .append(combine("year", year)).append("&")
                    .append(combine(hw, team1)).append("&")
                    .append(combine("page", page));

            return sb.toString();
        }

        String getFullUriVS(Integer year, String team1, String team2){
            StringBuilder sb = new StringBuilder();
            sb.append(src)
                    .append(combine("year", year)).append("&")
                    .append(combine("team1", team1)).append("&")
                    .append(combine("team2",team2));

            return sb.toString();
        }

        <K,V>String combine(K key, V value){
            StringBuilder sb = new StringBuilder();
            sb.append(key).append("=").append(value);
            return sb.toString();
        }
    }
    static class ReqObject{
        Long page = 0l;
        Long total_pages = 1l;
        Long per_page, total;
        Long cnt = 0l;
        List<Data> data = new ArrayList<>();

        void addData(Data d){
            data.add(d);
        }

    }

    static class Data {
        String competition, round, team1, team2;
        Long team1goals, team2goals,year;

        Data(Long year, String competition, String team1goals, String team2goals, String round, String team1, String team2){
            this.year = year;
            this.competition = competition;
            this.team1goals = Long.parseLong(team1goals);
            this.team2goals = Long.parseLong(team2goals);
            this.round = round;
            this.team1 = team1;
            this.team2 = team2;
        }
    }
}

//Unexpected token END OF FILE at position 0.
