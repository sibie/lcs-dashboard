package com.cbamz.lcsdashboard.domain.team;

import lombok.*;

import javax.persistence.*;
import java.util.Calendar;

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

    public Integer getAge() {
        Integer year = Calendar.getInstance().get(Calendar.YEAR);
        return year - Integer.parseInt(birthYear);
    }
}
