package com.cbamz.lcsdashboard.controller;

import com.cbamz.lcsdashboard.domain.game.Game;
import com.cbamz.lcsdashboard.domain.team.Player;
import com.cbamz.lcsdashboard.domain.team.Team;
import com.cbamz.lcsdashboard.service.GameService;
import com.cbamz.lcsdashboard.service.PlayerService;
import com.cbamz.lcsdashboard.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Defining REST APIs here to be used in frontend to render views. More to be added.

@RestController
@RequestMapping(path = "api/v1/lcs")
@AllArgsConstructor
public class TeamController {
    private final TeamService teamService;
    private final GameService gameService;
    private final PlayerService playerService;

    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    public List<Team> viewAllTeams() {
        return teamService.loadAllTeams();
    }

    @RequestMapping("/teams/{teamName}")
    public Team viewTeam(@PathVariable(value="teamName") String teamName) {
        Team team = teamService.loadTeamByTeamName(teamName); // NEED TO DEBUG
        team.setGamesPlayed(gameService.getGamesByTeam(teamName));
        team.setRoster(playerService.loadPlayersByTeamCode(team.getTeamCode()));
        return team;
    }

    @RequestMapping(value = "/players", method = RequestMethod.GET)
    public List<Player> viewAllPlayers() {
        return teamService.loadAllPlayers();
    }

    @RequestMapping("/players/{playerName}")
    public Player viewPlayer(@PathVariable(value="playerName") String playerName) {
        Player player = teamService.loadPlayerByGamerTag(playerName);
        player.setGamesPlayed(gameService.getGamesByPlayer(playerName));
        return player;
    }

    @RequestMapping(value = "/matches", method = RequestMethod.GET)
    public List<Game> viewAllMatches() {
        return gameService.loadAllGames();
    }
}
