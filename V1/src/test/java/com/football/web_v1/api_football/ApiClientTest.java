package com.football.web_v1.api_football;

import com.football.web_v1.api_football.dto.SearchTeamReq;
import com.football.web_v1.api_football.dto.SearchTeamRes;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ApiClientTest {

    @Autowired
    private ApiClient apiClient;

    @Test
    public void teamSearchTest() throws IOException, JSONException {
        SearchTeamReq searchTeamReq = new SearchTeamReq("ENG", 2021);
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
            searchTeamRes.setRank(jsonObject.getInt("rank"));
            searchTeamRes.setAllStat(jsonObject.getJSONObject("all"));
            searchTeamRes.setTeamInfo(jsonObject.getJSONObject("team"));
            searchTeamRes.setGoalsDiff(jsonObject.getInt("goalsDiff"));
            searchTeamRes.setPoints(jsonObject.getInt("points"));

            results.add(searchTeamRes);
        }

    }
}