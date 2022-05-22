package com.cbamz.lcsdashboard.config;

import com.cbamz.lcsdashboard.domain.team.Player;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class PlayerFieldSetMapper implements FieldSetMapper<Player> {
    @Override
    public Player mapFieldSet(FieldSet fieldSet) throws BindException {
        return new Player(fieldSet.readLong("id"),
                fieldSet.readString("fullName"),
                fieldSet.readString("gamerTag"),
                fieldSet.readString("birthYear"),
                fieldSet.readString("role"),
                fieldSet.readString("currentTeam"));

    }
}