package com.football.web_v1.api_football;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LeagueCode {

    ENG(39),
    FRA(61),
    ITA(71),
    GER(78),
    ESP(94);

    private final Integer value;

}
