package com.cbamz.lcsdashboard.config.batch;

import com.cbamz.lcsdashboard.domain.game.Game;
import com.cbamz.lcsdashboard.domain.team.Player;
import com.cbamz.lcsdashboard.domain.team.Team;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    // Need these beans to setup Spring Batch job to parse raw data from CSV files.
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    // Datasource is auto-configured to H2.
    @Autowired
    public DataSource dataSource;

    /*
        Using FlatFileItemReader to parse raw data and transform into entities to write into
        the database. The actual entity formation is done using custom implementations of
        FieldSetMapper, one each for players, teams and games.
     */

    @Bean
    public FlatFileItemReader<Player> playerItemReader() {
        FlatFileItemReader<Player> reader = new FlatFileItemReader<>();
        reader.setLinesToSkip(1); // Skipping the parsing of column headers.
        reader.setResource(new ClassPathResource("/data/player-data.csv"));

        DefaultLineMapper<Player> playerLineMapper = new DefaultLineMapper<>();

        // Setting column headers to tokenizer so data is mapped correctly for each record.
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[]{"id", "fullName", "gamerTag", "birthYear", "role", "currentTeam", "countryOfBirth"});

        playerLineMapper.setLineTokenizer(tokenizer);
        playerLineMapper.setFieldSetMapper(new PlayerFieldSetMapper());
        playerLineMapper.afterPropertiesSet();
        reader.setLineMapper(playerLineMapper);
        return reader;
    }

    @Bean
    public FlatFileItemReader<Team> teamItemReader() {
        FlatFileItemReader<Team> reader = new FlatFileItemReader<>();
        reader.setLinesToSkip(1);
        reader.setResource(new ClassPathResource("/data/team-data.csv"));

        DefaultLineMapper<Team> teamLineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[]{"id", "teamName", "teamCode", "league", "ceo", "gm", "coach", "ac"});

        teamLineMapper.setLineTokenizer(tokenizer);
        teamLineMapper.setFieldSetMapper(new TeamFieldSetMapper());
        teamLineMapper.afterPropertiesSet();
        reader.setLineMapper(teamLineMapper);
        return reader;
    }

    @Bean
    public FlatFileItemReader<Game> gameItemReader() {
        FlatFileItemReader<Game> reader = new FlatFileItemReader<>();
        reader.setLinesToSkip(1);
        reader.setResource(new ClassPathResource("/data/game-data.csv"));

        DefaultLineMapper<Game> gameLineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[]{"id", "gameYear", "gameSplit", "gameWeek", "gameDay", "isPlayoffs", "team1", "team2", "top1", "jungle1", "mid1", "bot1", "support1", "top2", "jungle2", "mid2", "bot2", "support2", "winner", "blueside", "redside", "mvp", "gameTime", "team1Stats", "team2Stats", "team1Bans", "team2Bans", "team1Picks", "team2Picks", "dragonSoul"});

        gameLineMapper.setLineTokenizer(tokenizer);
        gameLineMapper.setFieldSetMapper(new GameFieldSetMapper());
        gameLineMapper.afterPropertiesSet();
        reader.setLineMapper(gameLineMapper);
        return reader;
    }

    /*
        Using JDBCBatchItemWriter to transpose each record read from csv into database for persistence.
        Custom query to be used is defined as part of Sql attribute for the writer object.
     */

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    public JdbcBatchItemWriter<Player> playerItemWriter() {
        JdbcBatchItemWriter<Player> itemWriter = new JdbcBatchItemWriter<>();

        itemWriter.setDataSource(this.dataSource);
        itemWriter.setSql("INSERT INTO PLAYER (id, birth_year, current_team, full_name, gamer_tag, role, country_of_birth) VALUES (:id, :birthYear, :currentTeam, :fullName, :gamerTag, :role, :countryOfBirth)");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
        itemWriter.afterPropertiesSet();

        return itemWriter;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    public JdbcBatchItemWriter<Team> teamItemWriter() {
        JdbcBatchItemWriter<Team> itemWriter = new JdbcBatchItemWriter<>();

        itemWriter.setDataSource(this.dataSource);
        itemWriter.setSql("INSERT INTO TEAM (id, team_name, team_code, league, ceo, gm, coach, ac) VALUES (:id, :teamName, :teamCode, :league, :ceo, :gm, :coach, :ac)");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
        itemWriter.afterPropertiesSet();

        return itemWriter;
    }

    @Bean
    public JdbcBatchItemWriter<Game> gameItemWriter() {
        JdbcBatchItemWriter<Game> itemWriter = new JdbcBatchItemWriter<>();

        itemWriter.setDataSource(this.dataSource);
        itemWriter.setSql("INSERT INTO GAME (id, game_year, game_split, game_week, game_day, is_playoffs, team1, team2, top1, jungle1, mid1, bot1, support1, top2, jungle2, mid2, bot2, support2, winner, blueside, redside, mvp, game_time, team1stats, team2stats, team1bans, team2bans, team1picks, team2picks, dragon_soul) VALUES (:id, :gameYear, :gameSplit, :gameWeek, :gameDay, :isPlayoffs, :team1, :team2, :top1, :jungle1, :mid1, :bot1, :support1, :top2, :jungle2, :mid2, :bot2, :support2, :winner, :blueside, :redside, :mvp, :gameTime, :team1Stats, :team2Stats, :team1Bans, :team2Bans, :team1Picks, :team2Picks, :dragonSoul)");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
        itemWriter.afterPropertiesSet();

        return itemWriter;
    }

    /*
        Defining each of the steps to be performed by the JobBuilderFactory, i.e. parsing of players, followed
        by teams and finally games. This can be enhanced further as our use case grows in complexity.
     */

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Player, Player>chunk(10)
                .reader(playerItemReader())
                .writer(playerItemWriter())
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .<Team, Team>chunk(10)
                .reader(teamItemReader())
                .writer(teamItemWriter())
                .build();
    }

    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3")
                .<Game, Game>chunk(10)
                .reader(gameItemReader())
                .writer(gameItemWriter())
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
                .start(step1())
                .next(step2())
                .next(step3())
                .build();
    }
}