package com.football.web_v1.api_football;

import com.football.web_v1.api_football.dto.SearchTeamReq;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApiClient {

    OkHttpClient client = new OkHttpClient();

    @Value("${football.client.key}")
    private String clientKey;

    @Value("${football.client.host}")
    private String apiHost;

    public ResponseBody teamSearch(SearchTeamReq searchTeamReq) throws IOException {

        Request request = new Request.Builder()
                .url(String.format("https://api-football-v1.p.rapidapi.com/v3/standings?season=%d&league=%d", searchTeamReq.getSeason(), searchTeamReq.getLeague()))
                .get()
                .addHeader("X-RapidAPI-Key", clientKey)
                .addHeader("X-RapidAPI-Host", apiHost)
                .build();

        Response response = client.newCall(request).execute();

        return response.body();
    }
}
