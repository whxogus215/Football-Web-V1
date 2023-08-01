package com.football.web_v1.api_football.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchTeamRes {

    private Integer rank;

    private Integer win;
    private Integer draw;
    private Integer lose;
    private Integer played;
    private Integer goalsAgainst;
    private Integer goalsFor;

    private String name;
    private String logo;

    private Integer goalsDiff;

    private Integer points;

}
