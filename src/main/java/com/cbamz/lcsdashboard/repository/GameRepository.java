package com.cbamz.lcsdashboard.repository;

import com.cbamz.lcsdashboard.domain.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    // Returns all games played by a team, regardless of which side they are on.
    List<Game> getByTeam1OrTeam2(String team1, String team2);

    // Retrieves all games played by a player, position is not a limitation.
    List<Game> getByTop1OrJungle1OrMid1OrBot1OrSupport1(String top1,
                                                        String jungle1,
                                                        String mid1,
                                                        String bot1,
                                                        String support1);

    List<Game> getByTop2OrJungle2OrMid2OrBot2OrSupport2(String top2,
                                                        String jungle2,
                                                        String mid2,
                                                        String bot2,
                                                        String support2);
}
