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

class ProbA {
    static JSONParser parser = new JSONParser();
    static int res;
    static int getNumDraws(int year) throws IOException, Exception{
        FootBallURI uriFact = new FootBallURI();
        ReqObject obj;
        res = 0;
        int page;
        for(int i = 0; i <= 10; i++){
            page = 1;
            obj = new ReqObject();
            String uri = uriFact.getFullUri(year, page, i);
            getReq(uri, obj);
            res+=obj.total;
        }
        return res;
    }
    static synchronized void addToObj(ReqObject obj, JSONObject jsonObj) throws Exception{
        try{
            JSONArray jsonArr = (JSONArray)jsonObj.get("data");
            for(int i = 0; i < jsonArr.size(); i++){
                JSONObject dataObj = (JSONObject)jsonArr.get(i);
                String team1goals = (String) dataObj.get("team1goals");
                String team2goals = (String) dataObj.get("team2goals");
            }


            obj.page = (Long)jsonObj.get("page");
            obj.per_page = (Long)jsonObj.get("per_page");
            obj.total = (Long)jsonObj.get("total");
            obj.total_pages = (Long)jsonObj.get("total_pages");
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    static synchronized void getReq(String uri, ReqObject obj) throws IOException, Exception {
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
                JSONObject jsonObj = (JSONObject) parser.parse(sb.toString());
                addToObj(obj, jsonObj);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    static class FootBallURI{
        String src = "https://jsonmock.hackerrank.com/api/football_matches?";

        String getFullUri(Integer year, Integer page, int i){
            StringBuilder sb = new StringBuilder();
            sb.append(src)
                    .append(combine("year", year)).append("&")
                    .append(combine("team1goals", i)).append("&")
                    .append(combine("team2goals", i)).append("&")
                    .append(combine("page", page)).append("&");


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

public class Prob1 {
}
