import '../styles/GameDetailCard.css';

import { React } from 'react';
import { Link } from 'react-router-dom';

/* This function is used to get Team Code from a given Team Name. Need this to reconcile between Player and Game data. */

function getTeamCode(teamName) {
    let teamCode;
    switch (teamName) {
        case "100 Thieves":
            teamCode = "100T";
            break;
        case "Cloud9":
            teamCode = "C9";
            break;
        case "Counter Logic Gaming":
            teamCode = "CLG";
            break;
        case "Dignitas QNTMPAY":
            teamCode = "DIG";
            break;
        case "Evil Geniuses":
            teamCode = "EG";
            break;
        case "FlyQuest":
            teamCode = "FLY";
            break;
        case "Golden Guardians":
            teamCode = "GG";
            break;
        case "Immortals":
            teamCode = "IMT";
            break;
        case "Team Liquid":
            teamCode = "TL";
            break;
        case "Team SoloMid":
            teamCode = "TSM";
            break;
        default:
            teamCode = "NA"
      }
      return teamCode;
}

export const GameDetailCard = ({teamName, game}) => {
    // Determining which team is the opponent in a Game record.
    const opponent = game.team1 === teamName ? game.team2 : game.team1;
    const winnerCode = getTeamCode(game.winner);

    // Indicates whether the team won or lost, this is used to color each card to distinguish wins from losses.
    const isMatchWon = teamName === game.winner;

    // Building out the routes to be used to link users to available player and team pages.
    const opponentRoute = `/teams/${getTeamCode(opponent)}`;
    const mvpRoute = `/players/${game.mvp}`;
    
    // Variables needed to process Game data retrieved from API.
    let teamRawStats, teamRawPicks;
    let teamTop, teamJungle, teamMid, teamBot, teamSupport;

    let opponentRawStats, opponentRawPicks;
    let opponentTop, opponentJungle, opponentMid, opponentBot, opponentSupport;

    // let teamRawBans, opponentRawBans; --> Not using this for the time being.

    /*
        Since the data labels simply read "team1" and "team2" and our team can be on either side,
        we need to identify which field contains our team data and set the variables accordingly.
    */

    if(game.team1 === teamName) {

        teamRawStats = game.team1Stats;
        teamRawPicks = game.team1Picks;
        // teamRawBans = game.team1Bans; --> Not using this for the time being.
        teamTop = game.top1;
        teamJungle = game.jungle1;
        teamMid = game.mid1;
        teamBot = game.bot1;
        teamSupport = game.support1;

        opponentRawStats = game.team2Stats;
        opponentRawPicks = game.team2Picks;
        // opponentRawBans = game.team2Bans; --> Not using this for the time being.
        opponentTop = game.top2;
        opponentJungle = game.jungle2
        opponentMid = game.mid2;
        opponentBot = game.bot2;
        opponentSupport = game.support2;

    } else {

        teamRawStats = game.team2Stats;
        teamRawPicks = game.team2Picks;
        // teamRawBans = game.team2Bans; --> Not using this for the time being.
        teamTop = game.top2;
        teamJungle = game.jungle2;
        teamMid = game.mid2;
        teamBot = game.bot2;
        teamSupport = game.support2;

        opponentRawStats = game.team1Stats;
        opponentRawPicks = game.team1Picks;
        // opponentRawBans = game.team1Bans; --> Not using this for the time being.
        opponentTop = game.top1;
        opponentJungle = game.jungle1;
        opponentMid = game.mid1;
        opponentBot = game.bot1;
        opponentSupport = game.support1;
    }

    // Preparing routes to PlayerPage for each of the players being displayed on game card.

    const teamTopPath = `/players/${teamTop}`;
    const teamJunglePath = `/players/${teamJungle}`;
    const teamMidPath = `/players/${teamMid}`;
    const teamBotPath = `/players/${teamBot}`;
    const teamSupportPath = `/players/${teamSupport}`;

    const opponentTopPath = `/players/${opponentTop}`;
    const opponentJunglePath = `/players/${opponentJungle}`;
    const opponentMidPath = `/players/${opponentMid}`;
    const opponentBotPath = `/players/${opponentBot}`;
    const opponentSupportPath = `/players/${opponentSupport}`;

    // Transforming raw stats in which all the data is concatenated into a single string into separate variables.

    let teamStats = teamRawStats.split("--");
    let teamKills = teamStats[0].split("-")[0];
    let teamTurrets = teamStats[0].split("-")[1];
    let teamDragons = teamStats[0].split("-")[2];
    let teamBarons = teamStats[0].split("-")[3];
    let teamGold = teamStats[0].split("-")[4];
    let teamPicks = teamRawPicks.split("-");

    let opponentStats = opponentRawStats.split("--");
    let opponentKills = opponentStats[0].split("-")[0];
    let opponentTurrets = opponentStats[0].split("-")[1];
    let opponentDragons = opponentStats[0].split("-")[2];
    let opponentBarons = opponentStats[0].split("-")[3];
    let opponentGold = opponentStats[0].split("-")[4];
    let opponentPicks = opponentRawPicks.split("-");

    let teamStatsTable, playerStatsTable;

    /* Generating general stats for both teams with data like total kills, turrets taken, team gold, etc. */

    teamStatsTable = [
        <div key="team-table" className="team-stats-table">
            <table className="table">
                <thead>
                    <tr>
                        <th className="table-header">Metric</th>
                        <th className="table-header">{getTeamCode(teamName)}</th>
                        <th className="table-header">{getTeamCode(opponent)}</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td className="stat-metric">Kills</td>
                        <td className="team-score">{teamKills}</td>
                        <td className="opponent-score">{opponentKills}</td>
                    </tr>
                    <tr>
                        <td className="stat-metric">Turrets</td>
                        <td className="team-score">{teamTurrets}</td>
                        <td className="opponent-score">{opponentTurrets}</td>
                    </tr>
                    <tr>
                        <td className="stat-metric">Dragons</td>
                        <td className="team-score">{teamDragons}</td>
                        <td className="opponent-score">{opponentDragons}</td>
                    </tr>
                    <tr>
                        <td className="stat-metric">Barons</td>
                        <td className="team-score">{teamBarons}</td>
                        <td className="opponent-score">{opponentBarons}</td>
                    </tr>
                    <tr>
                        <td className="stat-metric">Gold</td>
                        <td className="team-score">{teamGold}</td>
                        <td className="opponent-score">{opponentGold}</td>
                    </tr>
                </tbody>
            </table>
        </div>];

    /*
        In LoL, teams either play on Blue side or Red side and this is determined before the game.
        Generally Blue is on the left of the map, while Red is on the right, so here we generate
        the table component accordingly depending on game record data.
    */

    if(game.blueSide === teamName) {
        playerStatsTable = [
            <div key="player-table" className="player-stats-table">
                <table className="table">
                    <thead>
                        <tr>
                            <th className="table-header-blue">{getTeamCode(teamName)}</th>
                            <th className="table-header-blue">Pick</th>
                            <th className="table-header-blue">KDA-CS</th>
                            <th className="table-header-red">{getTeamCode(opponent)}</th>
                            <th className="table-header-red">Pick</th>
                            <th className="table-header-red">KDA-CS</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td className="game-player"><Link to={teamTopPath}>{teamTop}</Link></td>
                            <td className="team-score">{teamPicks[0]}</td>
                            <td className="team-score">{teamStats[1]}</td>
                            <td className="game-player"><Link to={opponentTopPath}>{opponentTop}</Link></td>
                            <td className="opponent-score">{opponentPicks[0]}</td>
                            <td className="opponent-score">{opponentStats[1]}</td>
                        </tr>
                        <tr>
                            <td className="game-player"><Link to={teamJunglePath}>{teamJungle}</Link></td>
                            <td className="team-score">{teamPicks[1]}</td>
                            <td className="team-score">{teamStats[2]}</td>
                            <td className="game-player"><Link to={opponentJunglePath}>{opponentJungle}</Link></td>
                            <td className="opponent-score">{opponentPicks[1]}</td>
                            <td className="opponent-score">{opponentStats[2]}</td>
                        </tr>
                        <tr>
                            <td className="game-player"><Link to={teamMidPath}>{teamMid}</Link></td>
                            <td className="team-score">{teamPicks[2]}</td>
                            <td className="team-score">{teamStats[3]}</td>
                            <td className="game-player"><Link to={opponentMidPath}>{opponentMid}</Link></td>
                            <td className="opponent-score">{opponentPicks[2]}</td>
                            <td className="opponent-score">{opponentStats[3]}</td>
                        </tr>
                        <tr>
                            <td className="game-player"><Link to={teamBotPath}>{teamBot}</Link></td>
                            <td className="team-score">{teamPicks[3]}</td>
                            <td className="team-score">{teamStats[4]}</td>
                            <td className="game-player"><Link to={opponentBotPath}>{opponentBot}</Link></td>
                            <td className="opponent-score">{opponentPicks[3]}</td>
                            <td className="opponent-score">{opponentStats[4]}</td>
                        </tr>
                        <tr>
                            <td className="game-player"><Link to={teamSupportPath}>{teamSupport}</Link></td>
                            <td className="team-score">{teamPicks[4]}</td>
                            <td className="team-score">{teamStats[5]}</td>
                            <td className="game-player"><Link to={opponentSupportPath}>{opponentSupport}</Link></td>
                            <td className="opponent-score">{opponentPicks[4]}</td>
                            <td className="opponent-score">{opponentStats[5]}</td>
                        </tr>
                    </tbody>
                </table>
            </div>];
    } else {
        playerStatsTable = [
            <div key="player-table" className="player-stats-table">
                <table className="table">
                    <thead>
                        <tr>
                            <th className="table-header-blue">{getTeamCode(opponent)}</th>
                            <th className="table-header-blue">Pick</th>
                            <th className="table-header-blue">KDA-CS</th>
                            <th className="table-header-red">{getTeamCode(teamName)}</th>
                            <th className="table-header-red">Pick</th>
                            <th className="table-header-red">KDA-CS</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td className="game-player"><Link to={opponentTopPath}>{opponentTop}</Link></td>
                            <td className="opponent-score">{opponentPicks[0]}</td>
                            <td className="opponent-score">{opponentStats[1]}</td>
                            <td className="game-player"><Link to={teamTopPath}>{teamTop}</Link></td>
                            <td className="team-score">{teamPicks[0]}</td>
                            <td className="team-score">{teamStats[1]}</td>
                        </tr>
                        <tr>
                            <td className="game-player"><Link to={opponentJunglePath}>{opponentJungle}</Link></td>
                            <td className="opponent-score">{opponentPicks[1]}</td>
                            <td className="opponent-score">{opponentStats[2]}</td>
                            <td className="game-player"><Link to={teamJunglePath}>{teamJungle}</Link></td>
                            <td className="team-score">{teamPicks[1]}</td>
                            <td className="team-score">{teamStats[2]}</td>
                        </tr>
                        <tr>
                            <td className="game-player"><Link to={opponentMidPath}>{opponentMid}</Link></td>
                            <td className="opponent-score">{opponentPicks[2]}</td>
                            <td className="opponent-score">{opponentStats[3]}</td>
                            <td className="game-player"><Link to={teamMidPath}>{teamMid}</Link></td>
                            <td className="team-score">{teamPicks[2]}</td>
                            <td className="team-score">{teamStats[3]}</td>
                        </tr>
                        <tr>
                            <td className="game-player"><Link to={opponentBotPath}>{opponentBot}</Link></td>
                            <td className="opponent-score">{opponentPicks[3]}</td>
                            <td className="opponent-score">{opponentStats[4]}</td>
                            <td className="game-player"><Link to={teamBotPath}>{teamBot}</Link></td>
                            <td className="team-score">{teamPicks[3]}</td>
                            <td className="team-score">{teamStats[4]}</td>
                        </tr>
                        <tr>
                            <td className="game-player"><Link to={opponentSupportPath}>{opponentSupport}</Link></td>
                            <td className="opponent-score">{opponentPicks[4]}</td>
                            <td className="opponent-score">{opponentStats[5]}</td>
                            <td className="game-player"><Link to={teamSupportPath}>{teamSupport}</Link></td>
                            <td className="team-score">{teamPicks[4]}</td>
                            <td className="team-score">{teamStats[5]}</td>
                        </tr>
                    </tbody>
                </table>
            </div>];
    }

    /*
        Generating the component to be rendered on the page. Includes the following:
        - Opponent that the team faced,
        - When the game was played,
        - Game result, MVP,
        - Team and Player stats table.


    */

    return (
        <div className={isMatchWon ?  "GameDetailCard win-card" : "GameDetailCard loss-card"}>
            <div className="match-data-div-left">
                <span className="vs">VS</span>
                <h2 key={game.id}><Link to={opponentRoute}>{opponent}</Link></h2>
            </div>
            <div className="match-data-div-right">
                <h4 className="game-year-split">{game.gameYear}&nbsp;&nbsp;{game.gameSplit}</h4>
                <h4 className="game-week-day">Week {game.gameWeek}&nbsp;&nbsp;Day&nbsp;&nbsp;{game.gameDay}</h4>
            </div>
            <div className="match-data-div-left">
                <h2 className="game-result">{winnerCode} won in {game.gameTime}.</h2>
            </div>
            <div className="match-data-div-right">
                <h4 className="game-mvp">MVP&nbsp;&nbsp;-&nbsp;&nbsp;<Link to={mvpRoute}>{game.mvp}</Link></h4>
                <h4>Soul&nbsp;&nbsp;-&nbsp;&nbsp;{game.dragonSoul}</h4>
            </div>
            <div className="PlayerStatsTable">
                {playerStatsTable}
            </div>
            <div className="TeamStatsTable">
                    {teamStatsTable}
                </div>
        </div>
    );
}