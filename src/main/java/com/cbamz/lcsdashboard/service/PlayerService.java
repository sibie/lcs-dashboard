package com.cbamz.lcsdashboard.service;

import com.cbamz.lcsdashboard.domain.team.Player;
import com.cbamz.lcsdashboard.domain.team.Team;
import com.cbamz.lcsdashboard.repository.PlayerRepository;
import com.cbamz.lcsdashboard.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    public List<Player> loadAllPlayers() {
        return playerRepository.findAll();
    }

    public Player loadPlayerByGamerTag(String gamerTag) {
        return playerRepository.findByGamerTag(gamerTag).get();
    }

    public List<Player> loadPlayersByTeamCode(String teamCode) { return playerRepository.getByCurrentTeam(teamCode); }

}