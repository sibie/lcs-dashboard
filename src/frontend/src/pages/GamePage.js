import '../styles/GamePage.css';

import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

import { GameDetailCard } from '../components/GameDetailCard';
import { YearSelector } from '../components/YearSelector';

/* This function is used to get Team Name from a given Team Code. Need this to reconcile between Player and Game data. */

function getTeamName(teamCode) {
    let teamName;
    switch (teamCode) {
        case "100T":
            teamName = "100 Thieves";
            break;
        case "C9":
            teamName = "Cloud9";
            break;
        case "CLG":
            teamName = "Counter Logic Gaming";
            break;
        case "DIG":
            teamName = "Dignitas QNTMPAY";
            break;
        case "EG":
            teamName = "Evil Geniuses";
            break;
        case "FLY":
            teamName = "FlyQuest";
            break;
        case "GG":
            teamName = "Golden Guardians";
            break;
        case "IMT":
            teamName = "Immortals";
            break;
        case "TL":
            teamName = "Team Liquid";
            break;
        case "TSM":
            teamName = "Team SoloMid";
            break;
        default:
            teamName = "Free Agent"
      }
      return teamName;
}

export const GamePage = () => {

    // Using State to access JSON data returned by SpringBoot APIs.
    const [games, setGames] = useState({});

    // Getting these params from URL route defined in App.js. Need them for API call.
    const { teamCode, year, split } = useParams();
    let teamName = getTeamName(teamCode);
    
    /*
        Bug to be fixed --> Split param from route is getting unwanted braces which interferes with API call.
        Eg - input is coming as "{spring}", instead of "spring".
        console.log(split);
    */

    // Temporary hack to remove the braces from Split param.
    const splitParam = split.slice(1, -1);

    /*
        UseEffect hook is used to make API call and map incoming JSON to component State. Adding route params
        so React knows to refresh the page any time these values change when user navigates to the same page
        but for a different team/year/split.
    */

    useEffect( 
        () => {
            const fetchGames = async () => {
                const response = await fetch(`/api/v1/lcs/teams/${teamCode}/games?year=${year}&split=${splitParam}`); // need await since fetch returns a promise.
                const data = await response.json();
                setGames(data);
            };
            fetchGames();
        }, [ teamCode, year, splitParam]
    );

    // The actual rendered view which is made up of YearSelector and GameDetailCard components.

    return (
        <div className="GamePage">
            <div className="year-selector">
                <div className="home-anchor">
                    <a href="/">Home</a>
                </div>
                <YearSelector teamCode={teamCode}/>
            </div>
            <div className="filtered-game-list">
                <h1 className='team-match-history-header'>{teamCode} Match History</h1>
                {Array.from(games).map(game => (
                    <GameDetailCard key={game.id} teamName={teamName} game={game} />
                ))}
            </div>
        </div>
    );
}