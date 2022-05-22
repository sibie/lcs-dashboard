package com.cbamz.lcsdashboard.repository;

import com.cbamz.lcsdashboard.domain.team.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

}