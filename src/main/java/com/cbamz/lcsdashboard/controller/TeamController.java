package com.cbamz.lcsdashboard.controller;

import com.cbamz.lcsdashboard.domain.game.Game;
import com.cbamz.lcsdashboard.domain.team.Player;
import com.cbamz.lcsdashboard.domain.team.Team;
import com.cbamz.lcsdashboard.service.GameService;
import com.cbamz.lcsdashboard.service.PlayerService;
import com.cbamz.lcsdashboard.service.TeamService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

// Defining REST APIs here to be used in frontend to render views. More to be added.

@RestController
@RequestMapping(path = "api/v1/lcs")
@AllArgsConstructor
@CrossOrigin
public class TeamController {
    private final TeamService teamService;
    private final GameService gameService;
    private final PlayerService playerService;

    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    public List<Team> viewAllTeams() {
        return teamService.loadAllTeams();
    }

    @RequestMapping("/teams/{teamCode}")
    public Team viewTeam(@PathVariable(value="teamCode") String teamCode) {
        Team team = teamService.loadTeamByTeamCode(teamCode); // NEED TO DEBUG
        List<Game> gamesPlayed = gameService.getGamesByTeam(team.getTeamName());
        Collections.reverse(gamesPlayed);
        team.setGamesPlayed(gamesPlayed);
        team.setRoster(playerService.loadPlayersByTeamCode(team.getTeamCode()));
        return team;
    }

    @RequestMapping("/teams/{teamCode}/games")
    public List<Game> viewTeamGames(@PathVariable(value="teamCode") String teamCode,
                                @RequestParam String year,
                                @RequestParam String split) {
        Team team = teamService.loadTeamByTeamCode(teamCode);
        List<Game> gamesPlayed = gameService.getGamesByTeam(team.getTeamName());
        gamesPlayed.removeIf(game -> !game.getGameYear().equalsIgnoreCase(year) || !game.getGameSplit().equalsIgnoreCase(split));
        Collections.reverse(gamesPlayed);
        return gamesPlayed;
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

    @RequestMapping(value = "/games", method = RequestMethod.GET)
    public List<Game> viewAllGames() {
        return gameService.loadAllGames();
    }
}
