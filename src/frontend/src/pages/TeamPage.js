import '../styles/TeamPage.css';

import { React, useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';

import { RosterDetailCard } from '../components/RosterDetailCard';
import { GameDetailCard } from '../components/GameDetailCard';
import { GameSmallCard } from '../components/GameSmallCard';

/*
    Staff names are not organized as JSON, its a string containing both name and alias. This
    function is used to transform the string into commonly used format in esports, i.e.
    --> FirstName "Alias" LastName.
*/

function handleAlias (field) { 
    if(field.indexOf('--') === -1) {
        return field; // This field does not have an alias to split.
    } else {
        let aliasSplit = field.split("--");
        let fullName = aliasSplit[1].split(" ");
        let reformatedField = fullName[0] + " \"" + aliasSplit[0] + "\" ";
        for(let i = 1; i < fullName.length; i++) {
            reformatedField += fullName[i];
        }
        return reformatedField;
    }
}

export const TeamPage = () => {

    // Using React State to access JSON data returned by SpringBoot API call.
    const [team, setTeam] = useState({});

    // Using Team Code in page route to use in API call.
    const { teamCode } = useParams();

    // UseEffect hook is used to make API call and map JSON data to component state.

    useEffect( 
        () => {
            const fetchTeamDetails = async () => {
                const response = await fetch(`/api/v1/lcs/teams/${teamCode}`);
                const data = await response.json();
                setTeam(data);
            };
            fetchTeamDetails();
        }, [teamCode]
    );

    // Placeholder to handle lag until data is returned by API.
    if(!team || !team.teamCode) {
        return <h1>Fetching Data...</h1>
    }

    // Reformatting Staff raw data into the desired format.

    let teamLeads, ceo, gm, coach, ac;
    ceo = handleAlias(team.ceo);
    gm = handleAlias(team.gm)
    coach = handleAlias(team.coach)
    ac = handleAlias(team.ac);

    // In case one person is handling both CEO and GM responsiblities, we want to show it as a single table row.

    if(team.ceo === team.gm) {
        teamLeads = [
        <div key="staff-table" className="staff-table">
            <table className="table">
                <tbody>
                    <tr>
                        <td className="staff-role">CEO/GM:</td>
                        <td className="staff-name">{ceo}</td>
                    </tr>
                    <tr>
                        <td className="staff-role">Head Coach:</td>
                        <td className="staff-name">{coach}</td>
                    </tr>
                    <tr>
                        <td className="staff-role">Assistant Coach:</td>
                        <td className="staff-name">{ac}</td>
                    </tr>
                </tbody>
            </table>
        </div>];
    } else {
        teamLeads = [
            <div key="staff-table" className="staff-table">
                <table className="table">
                    <tbody>
                        <tr>
                            <td className="staff-role">CEO:</td>
                            <td className="staff-name">{ceo}</td>
                        </tr>
                        <tr>
                            <td className="staff-role">GM:</td>
                            <td className="staff-name">{gm}</td>
                        </tr>
                        <tr>
                            <td className="staff-role">Head Coach:</td>
                            <td className="staff-name">{coach}</td>
                        </tr>
                        <tr>
                            <td className="staff-role">Assistant Coach:</td>
                            <td className="staff-name">{ac}</td>
                        </tr>
                    </tbody>
                </table>
            </div>];
    }

    // Sorting the roster by player role, this is the standard in LoL esports.

    let sortedRoster = Array.from(team.roster);
    let map = {TOP: 1, JUNGLE: 2, MID: 3, BOT: 4, SUPPORT: 5, TOPSUB: 6, JUNGLESUB: 7, MIDSUB: 8, BOTSUB: 9, SUPPORTSUB: 10};
    sortedRoster.sort(function(x, y) { return map[x.role] - map[y.role]});

    // Preparing URL to navigate to latest available split for the current year in Game page.
    let browseTeamHistoryURL;
    if(process.env.REACT_APP_IS_MID_YEAR === "YES") {
        browseTeamHistoryURL = `/teams/${teamCode}/games/${process.env.REACT_APP_DATA_END_YEAR}/%7Bspring}`;
    } else {
        browseTeamHistoryURL = `/teams/${teamCode}/games/${process.env.REACT_APP_DATA_END_YEAR}/%7Bsummer}`;
    }

    // View being rendered for Team page and includes RosterDetailCard, GameDetailCard and GameSmallCard components.

    return (
        <div className="TeamPage">
            <div className="team-name-section">
                <h1 className="team-name">{team.teamName}</h1>
            </div>
            <div className="StaffDetails">
                {teamLeads}
            </div>
            <div className="role-name-section">
                <h2 className="role-name">TOP</h2>
            </div>
            <div className="role-name-section">
                <h3 className="role-name">JUNGLE</h3>
            </div>
            <div className="role-name-section">
                <h3 className="role-name">MID</h3>
            </div>
            <div className="role-name-section">
                <h2 className="role-name">BOT</h2>
            </div>
            <div className="role-name-section">
                <h2 className="role-name">SUPPORT</h2>
            </div>
            {sortedRoster && sortedRoster.slice(0,5).map(roster => (
                <RosterDetailCard key={roster.id} roster={roster} />
            ))}
            <div className="role-name-section"><h3 className="subs-label">SUBS:</h3></div>
            {sortedRoster && sortedRoster.slice(5).map(roster => (
                <RosterDetailCard key={roster.id} roster={roster} />
            ))}
            <div key={team.gamesPlayed[0].id} className="LatestGameDetails">
                <GameDetailCard teamName={team.teamName} game={team.gamesPlayed[0]}/>
            </div>
            {team.gamesPlayed && team.gamesPlayed.slice(1).map(game => (
                <GameSmallCard key={game.id} teamName={team.teamName} game={game} />
            ))}
            <div className="nav-anchor">
                <a href="/">Home</a>
            </div>
            <div className="nav-anchor">
                <Link to={browseTeamHistoryURL}>Browse Games</Link>
            </div>
        </div>
    );
}