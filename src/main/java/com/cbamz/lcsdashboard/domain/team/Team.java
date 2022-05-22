package com.cbamz.lcsdashboard.domain.team;

import lombok.*;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    private String ceo;
    private String gm;
    private String coach;
    private String ac;

    @ElementCollection
    @CollectionTable(name = "team_roster", joinColumns = @JoinColumn(name = "team_id"))
    @Column(name = "roster")
    private Map<String, Player> roster;

    @ElementCollection
    @CollectionTable(name = "team_substitutes", joinColumns = @JoinColumn(name = "team_id"))
    @Column(name = "substitutes")
    private Set<Player> substitutes;

    public Team(Long id, String teamName, String teamCode, String league, String ceo, String gm, String coach, String ac) {
        this.id = id;
        this.teamName = teamName;
        this.teamCode = teamCode;
        this.league = league;
        this.ceo = ceo;
        this.gm = gm;
        this.coach = coach;
        this.ac = ac;

        roster = new HashMap<String, Player>();
        substitutes = new HashSet<Player>();

    }

   /* This is probably useless, keeping it here just in case.

   public void setRoster(Player top, Player jungle, Player mid, Player bot, Player support) {
        roster.put(PlayerRole.TOP, top);
        roster.put(PlayerRole.JUNGLE, jungle);
        roster.put(PlayerRole.MID, mid);
        roster.put(PlayerRole.BOT, bot);
        roster.put(PlayerRole.SUPPORT, support);
    } */

    public Player getTop() {
        return roster.get(PlayerRole.TOP);
    }

    public Player getJungle() {
        return roster.get(PlayerRole.JUNGLE);
    }

    public Player getMid() {
        return roster.get(PlayerRole.MID);
    }

    public Player getBot() {
        return roster.get(PlayerRole.BOT);
    }

    public Player getSupport() {
        return roster.get(PlayerRole.SUPPORT);
    }

}
