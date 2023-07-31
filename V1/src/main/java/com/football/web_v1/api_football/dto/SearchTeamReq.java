package com.football.web_v1.api_football.dto;

import com.football.web_v1.api_football.LeagueCode;
import lombok.Getter;

@Getter
public class SearchTeamReq {

    private Integer season;
    private Integer league;

    public SearchTeamReq(String league, Integer season) {
        this.league = LeagueCode.valueOf(league).getValue();
        this.season = season;
    }
}
