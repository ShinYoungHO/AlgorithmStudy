package hackerRank.api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class RESETAPI3 {

    /*
     * Complete the 'getTotalGoals' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING team
     *  2. INTEGER year
     */

    public static int getTotalGoals(String team, int year) throws IOException {
        FootBallURI uriFact = new FootBallURI();
        ReqObject obj = new ReqObject();

        int page = 2;
        while(obj.page != obj.total_pages){
            String uri = uriFact.getFullUriTeam(year, team, page);
            String jsonString = getReq(uri);
            System.out.println(jsonString);
            // addToObj(obj, jsonString);

            page++;
            if(page == 2) break;
        }



        return 0;
    }

    static void addToObj(ReqObject obj, String jsonString){
        JSONObject jsonObj = new JSONObject(jsonString);
        JSONArray jsonArr = jsonObj.getJSONArray("data");


        obj.page = jsonObj.getInt("page");
        obj.per_page = jsonObj.getInt("per_page");
        obj.total = jsonObj.getInt("total");
        obj.total_pages = jsonObj.getInt("total_pages");

    }

    static String getReq(String uri) throws IOException{
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
        } catch(Error e){}
        return sb.toString();
    }

    static class FootBallURI{
        String src = "https://jsonmock.hackerrank.com/api/football_matches?";

        String getFullUriTeam(Integer year, String team1, Integer page){
            StringBuilder sb = new StringBuilder();
            sb.append(src)
                    .append(combine("year", year)).append("&")
                    .append(combine("team1", team1)).append("&")
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
        Integer page = 0;
        Integer total_pages = 1;
        Integer per_page, total;
        Integer cnt = 0;
        List<Data> data = new ArrayList<>();

        void addData(Data d){
            data.add(d);
        }

    }

    static class Data {
        String competition, round, team1, team2;
        Integer team1goals, team2goals,year;

        Data(int year, String competition, String team1goals, String team2goals, String round, String team1, String team2){
            this.year = year;
            this.competition = competition;
            this.team1goals = Integer.parseInt(team1goals);
            this.team2goals = Integer.parseInt(team2goals);
            this.round = round;
            this.team1 = team1;
            this.team2 = team2;
        }
    }
}
