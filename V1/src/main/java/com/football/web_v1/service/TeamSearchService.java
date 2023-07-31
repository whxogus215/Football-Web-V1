package com.football.web_v1.service;

import com.football.web_v1.api_football.ApiClient;
import com.football.web_v1.api_football.dto.SearchTeamReq;
import lombok.RequiredArgsConstructor;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class TeamSearchService {


    private final ApiClient apiClient;

    public void search(String league, Integer season) throws IOException {
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

        // 전달받은 JSON 값에서 경기 수, 승/무/패, 승점, 골득실 추출해서 JSON으로 다시 만들어서 반환
            // for문으로 돌면서 JSON 객체 배열로 추가해서 반환하기!
    }

}
