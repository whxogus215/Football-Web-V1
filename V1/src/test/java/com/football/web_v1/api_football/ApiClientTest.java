package com.football.web_v1.api_football;

import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ApiClientTest {

    @Autowired
    private ApiClient apiClient;

    @Test
    public void teamSearchTest() throws IOException, JSONException {
        ResponseBody responseBody = apiClient.teamSearch(null);
        String result = responseBody.string();

        JSONObject resultJson = new JSONObject(result);
        JSONArray jsonArray = resultJson.getJSONArray("response")
                .getJSONObject(0)
                .getJSONObject("league")
                .getJSONArray("standings")
                .getJSONArray(0);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Object name = jsonObject.getJSONObject("team").get("name");

            System.out.println(name.toString());
        }
    }
}