package com.cbamz.lcsdashboard.config.batch;

import com.cbamz.lcsdashboard.domain.game.Game;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

// Custom implementation of FieldSetMapper to map csv column inputs to form a new Game object to write into DB.

public class GameFieldSetMapper implements FieldSetMapper<Game> {
    @Override
    public Game mapFieldSet(FieldSet fieldSet) throws BindException {
        return new Game(fieldSet.readLong("id"),
                fieldSet.readString("gameYear"),
                fieldSet.readString("gameSplit"),
                fieldSet.readString("gameWeek"),
                fieldSet.readString("gameDay"),
                fieldSet.readString("isPlayoffs"),
                fieldSet.readString("team1"),
                fieldSet.readString("team2"),
                fieldSet.readString("top1"),
                fieldSet.readString("jungle1"),
                fieldSet.readString("mid1"),
                fieldSet.readString("bot1"),
                fieldSet.readString("support1"),
                fieldSet.readString("top2"),
                fieldSet.readString("jungle2"),
                fieldSet.readString("mid2"),
                fieldSet.readString("bot2"),
                fieldSet.readString("support2"),
                fieldSet.readString("winner"),
                fieldSet.readString("blueside"),
                fieldSet.readString("redside"),
                fieldSet.readString("mvp"));
    }
}
