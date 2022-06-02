package com.cbamz.lcsdashboard.domain.game;

import lombok.*;

import javax.persistence.*;

// This entity represents a single game played between two teams in the LCS.

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "GAME")
public class Game {

    @SequenceGenerator(
            name = "game_sequence",
            sequenceName = "game_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "game_sequence"
    )
    private Long id;
    private String gameYear;
    private String gameSplit;
    private String gameWeek;
    private String gameDay;

    // Need this later to identify Playoff games which are actually Bo5s.
    private String isPlayoffs;

    // Competing teams.
    private String team1;
    private String team2;

    // Team 1 roster.
    private String top1;
    private String jungle1;
    private String mid1;
    private String bot1;
    private String support1;

    // Team 2 roster.
    private String top2;
    private String jungle2;
    private String mid2;
    private String bot2;
    private String support2;

    // Key Match information.
    private String winner;
    private String blueside;
    private String redside;
    private String mvp;
    private String gameTime;

    // Advanced Stats.
    private String team1Stats;
    private String team2Stats;
    private String team1Bans;
    private String team2Bans;
    private String team1Picks;
    private String team2Picks;
    private String dragonSoul;

}
