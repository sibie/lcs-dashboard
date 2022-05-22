package com.cbamz.lcsdashboard.controller;

import com.cbamz.lcsdashboard.domain.team.Player;
import com.cbamz.lcsdashboard.domain.team.Team;
import com.cbamz.lcsdashboard.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/lcs")
@AllArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    public List<Team> getTeams() {
        return teamService.getTeams();
    }

    @RequestMapping(value = "/players", method = RequestMethod.GET)
    public List<Player> getPlayers() {
        return teamService.getPlayers();
    }
}
