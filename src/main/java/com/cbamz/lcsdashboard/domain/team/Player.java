package com.cbamz.lcsdashboard.domain.team;

import com.cbamz.lcsdashboard.domain.game.Game;
import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

// This entity represents a single pro player affiliated with the LCS.

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PLAYER")
public class Player {

    @SequenceGenerator(
            name = "player_sequence",
            sequenceName = "player_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "player_sequence"
    )
    private Long id;
    private String fullName;
    private String gamerTag;
    private String birthYear;
    private String role;

    private String currentTeam;
    private String countryOfBirth;

    @Transient
    private List<Game> gamesPlayed;

    public Player(Long id, String fullName, String gamerTag, String birthYear, String role, String currentTeam, String countryOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.gamerTag = gamerTag;
        this.birthYear = birthYear;
        this.role = role;
        this.currentTeam = currentTeam;
        this.countryOfBirth = countryOfBirth;
    }

    public Integer getAge() {
        Integer year = Calendar.getInstance().get(Calendar.YEAR);
        return year - Integer.parseInt(birthYear);
    }
}
