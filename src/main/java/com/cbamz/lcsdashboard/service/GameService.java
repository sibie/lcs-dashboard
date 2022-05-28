package com.cbamz.lcsdashboard.service;

import com.cbamz.lcsdashboard.domain.game.Game;
import com.cbamz.lcsdashboard.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GameService {
    private final GameRepository gameRepository;

    public List<Game> loadAllGames() {
        return gameRepository.findAll();
    }

    public List<Game> getGamesByTeam(String team) {
        return gameRepository.getByTeam1OrTeam2(team, team);
    }

    public List<Game> getGamesByPlayer(String player) {
        List<Game> allGames = new ArrayList<Game>();

        // Need all games played by the player on either side.
        allGames.addAll(gameRepository.getByTop1OrJungle1OrMid1OrBot1OrSupport1(player, player, player, player, player));
        allGames.addAll(gameRepository.getByTop2OrJungle2OrMid2OrBot2OrSupport2(player, player, player, player, player));

        return allGames;
    }
}
