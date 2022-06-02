import '../styles/GameSmallCard.css';

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

export const GameSmallCard = ({teamName, game}) => {

    // To distinguish current team from opponent while rendering data.
    const opponent = game.team1 === teamName ? game.team2 : game.team1;
    const winnerCode = getTeamCode(game.winner);

    // Generating route for opponent TeamPage component.
    const opponentRoute = `/teams/${getTeamCode(opponent)}`;

    // This component is a compressed version of GameDetailCard for showing the next 3 recent games.
    return (
        <div className={teamName === game.winner ?  "GameSmallCard win-card" : "GameSmallCard loss-card"}>
            <p className="vs" key={game.id}>VS <Link to={opponentRoute}>{opponent}</Link></p>
            <p className="game-result" >{winnerCode} won in {game.gameTime}.</p>
        </div>
    );
}