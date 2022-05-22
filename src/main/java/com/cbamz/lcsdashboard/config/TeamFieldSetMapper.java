package com.cbamz.lcsdashboard.config;

import com.cbamz.lcsdashboard.domain.team.Team;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class TeamFieldSetMapper implements FieldSetMapper<Team> {
    @Override
    public Team mapFieldSet(FieldSet fieldSet) throws BindException {
        return new Team(fieldSet.readLong("id"),
                fieldSet.readString("teamName"),
                fieldSet.readString("teamCode"),
                fieldSet.readString("league"),
                fieldSet.readString("ceo"),
                fieldSet.readString("gm"),
                fieldSet.readString("coach"),
                fieldSet.readString("ac"));
    }
}
