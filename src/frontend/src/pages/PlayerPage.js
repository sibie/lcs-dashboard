import '../styles/PlayerPage.css';

import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

import { GameDetailCard } from '../components/GameDetailCard';
import { GameSmallCard } from '../components/GameSmallCard';

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

export const PlayerPage = () => {

    // Using React State to access JSON data returned by SpringBoot API call.
    const [player, setPlayer] = useState({});

    // Using param from route to construct API call to be made.
    const { gamerTag } = useParams();

    /*
        UseEffect hook is used to make API call and add JSON response data to this component's state.
        Adding gamerTag param to hook so API call is refreshed whenever its value changes, i.e. user
        navigates to another PlayerPage component.
    */

    useEffect( 
        () => {
            const fetchPlayerDetails = async () => {
                // Note --> Need 'await' here since fetch returns a promise.
                const response = await fetch(`/api/v1/lcs/players/${gamerTag}`);
                const data = await response.json();
                setPlayer(data);
            };
            fetchPlayerDetails();
        }, [gamerTag]
    );

    // Placeholder to handle lag until data is returned by API.
    if(!player || !player.gamerTag) {
        return <h1>Fetching data...</h1>
    }

    let gameDetailCard;
    if(player.gamesPlayed.length > 0) {
        gameDetailCard = <GameDetailCard teamName={getTeamName(player.currentTeam)} game={player.gamesPlayed[0]}/>;
    }

    /*
        View to be rendered for each player. Includes player personal data and recent games as 
        GameDetailCard and GameSmallCard components, similar to Team page.

        Possible Enhancement - Instead of reusing Game components, create a new one that highlights the player's
        individual performance in the win/loss rather than everyone's data.
    */

    return (
        <div className="PlayerPage">
            <div className="player-name-header">
                <h1>{player.fullName}&nbsp;&nbsp;/&nbsp;&nbsp;{player.gamerTag}</h1>
            </div>
            <div className="player-additional-details">
                <span className="additional-details">Country of Birth:</span>
                <h2 className="additional-details">{player.countryOfBirth}</h2>
            </div>
            <div className="player-additional-details">
                <span>Birthyear / Age</span>
                <h2>{player.birthYear}&nbsp;&nbsp;/&nbsp;&nbsp;{player.age}</h2>
            </div>
            <div className="player-additional-details">
                <span>Current Team:</span>
                <h2>{getTeamName(player.currentTeam)}</h2>
            </div>
            <div className="PlayerLatestGameDetails">
                {gameDetailCard}
            </div>
            {player.gamesPlayed && player.gamesPlayed.slice(1).map(game => (
                <GameSmallCard key={game.id} teamName={getTeamName(player.currentTeam)} game={game} />
            ))}
        </div>
    );
}