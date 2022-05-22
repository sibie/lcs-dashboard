package com.cbamz.lcsdashboard.repository;

import com.cbamz.lcsdashboard.domain.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

}