package com.football.web_v1.api_football.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class SearchTeamRes {

    private Integer rank;

    private Map<String, Object> allStats;

    private Map<String, Object> teamInfo;

    private Integer goalsDiff;

    private Integer points;

}
