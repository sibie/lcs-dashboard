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
public class TeamService {
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public List<Team> getTeams() {
        return teamRepository.findAll();
    }

    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }
}
