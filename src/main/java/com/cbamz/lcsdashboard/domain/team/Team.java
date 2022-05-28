package com.cbamz.lcsdashboard.domain.team;

import com.cbamz.lcsdashboard.domain.game.Game;
import lombok.*;

import javax.persistence.*;
import java.util.*;

// This entity represents a single Team that is participating in the LCS.

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TEAM")
public class Team {

    @SequenceGenerator(
            name = "team_sequence",
            sequenceName = "team_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "team_sequence"
    )
    private Long id;
    private String teamName;
    private String teamCode;
    private String league;

    // Capturing key staff data as part of the entity.
    private String ceo;
    private String gm;
    private String coach;
    private String ac;

    @Transient
    private List<Player> roster;

    @Transient
    private List<Game> gamesPlayed; // Marking as transient as we don't want this to be serialized.

    public Team(Long id, String teamName, String teamCode, String league, String ceo,
                String gm, String coach, String ac) {
        this.id = id;
        this.teamName = teamName;
        this.teamCode = teamCode;
        this.league = league;
        this.ceo = ceo;
        this.gm = gm;
        this.coach = coach;
        this.ac = ac;
    }
}