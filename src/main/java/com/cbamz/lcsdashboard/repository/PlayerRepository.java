package com.cbamz.lcsdashboard.repository;

import com.cbamz.lcsdashboard.domain.team.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByGamerTag(String gamerTag);
    List<Player> getByCurrentTeam(String currentTeam);
}