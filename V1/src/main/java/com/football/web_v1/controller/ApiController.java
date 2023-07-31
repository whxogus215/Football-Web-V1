package com.football.web_v1.controller;

import com.football.web_v1.service.TeamSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class ApiController {

    private final TeamSearchService teamSearchService;

    @GetMapping("/table")
    public void searchLeagueTable(@RequestParam String league, @RequestParam Integer season) throws IOException {
        teamSearchService.search(league, season);
    }
}
