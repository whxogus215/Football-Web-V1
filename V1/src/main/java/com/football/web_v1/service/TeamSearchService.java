package com.football.web_v1.service;

import com.football.web_v1.api_football.ApiClient;
import com.football.web_v1.api_football.dto.SearchTeamReq;
import com.football.web_v1.api_football.dto.SearchTeamRes;
import lombok.RequiredArgsConstructor;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamSearchService {


    private final ApiClient apiClient;

    public List<SearchTeamRes> search(String league, Integer season) throws IOException {
        // 컨트롤러에서 전달받은 요청 파라미터로 API-Football에 요청
        SearchTeamReq searchTeamReq = new SearchTeamReq(league, season);
        ResponseBody responseBody = apiClient.teamSearch(searchTeamReq);

        String result = responseBody.string();

        JSONObject resultJson = new JSONObject(result);
        JSONArray jsonArray = resultJson.getJSONArray("response")
                .getJSONObject(0)
                .getJSONObject("league")
                .getJSONArray("standings")
                .getJSONArray(0);

        List<SearchTeamRes> results = new ArrayList<>();

        // 전달받은 JSON 값에서 경기 수, 승/무/패, 승점, 골득실 추출해서 DTO로 생성해서 반환
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            SearchTeamRes searchTeamRes = new SearchTeamRes();
            // JSON "rank"

            searchTeamRes.setRank(jsonObject.getInt("rank"));
            // JSON "all"
            searchTeamRes.setLose(jsonObject.getJSONObject("all").getInt("lose"));
            searchTeamRes.setDraw(jsonObject.getJSONObject("all").getInt("draw"));
            searchTeamRes.setPlayed(jsonObject.getJSONObject("all").getInt("played"));
            searchTeamRes.setWin(jsonObject.getJSONObject("all").getInt("win"));
            searchTeamRes.setGoalsAgainst(jsonObject.getJSONObject("all").getJSONObject("goals").getInt("against"));
            searchTeamRes.setGoalsFor(jsonObject.getJSONObject("all").getJSONObject("goals").getInt("for"));

            // JSON "team"
            searchTeamRes.setName(jsonObject.getJSONObject("team").getString("name"));
            searchTeamRes.setLogo(jsonObject.getJSONObject("team").getString("logo"));

            // JSON "goalsDiff"
            searchTeamRes.setGoalsDiff(jsonObject.getInt("goalsDiff"));
            // JSON "points"
            searchTeamRes.setPoints(jsonObject.getInt("points"));

            results.add(searchTeamRes);
        }

        return results;
    }
}
