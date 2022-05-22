package com.cbamz.lcsdashboard.config;

import com.cbamz.lcsdashboard.domain.team.Player;
import com.cbamz.lcsdashboard.domain.team.Team;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RosterConstructor implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Waiting for Batch job to complete...");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("Need to figure this out...");

        // Step 1 -> Get all players records.

        // Step 2 -> Need a fresh map of Set<Player> for subs and a map of Map<String, Player> for rosters.

        // Step 3 -> Iterate through each player and load these structures with respective starters/subs.

        // Step 4 --> Update Team table in DB with the updated rosters and subs.

        // Note - This probably needs a rework when I get to the match data components.

        /* This is how I could have done it with JPA, but this has to happen at runtime. Need to use JDBC.
           Keeping it for reference.

        List<Team> teamList = teamRepository.findAll();
        List<Player> playerList = playerRepository.findAll();
        playerList.removeIf(player -> player.getCurrentTeam().equalsIgnoreCase("NO"));

        Iterator<Player> it = playerList.iterator();
        while (it.hasNext()) {
            Player player = it.next();

            Team team = teamList.stream()
                    .filter(t -> player.getCurrentTeam().equals(t.getTeamCode()))
                    .findAny().get();
            int teamIndex = teamList.indexOf(team);

            if(player.getRole().contains("SUB"))
                team.getSubstitutes().add(player);
            else
                team.getRoster().put(player.getRole(), player);

            teamList.set(teamIndex, team);
        }

        teamRepository.saveAll(teamList); */
    }
}
