import { React } from 'react';
import { Link } from 'react-router-dom';

/* This function is used to get Team Name from a given Team Code, for displaying available teams on Home Page. */

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

// Component that shows an available Team with link to respective TeamPage component, on the Home page.

export const TeamTile = ({teamName}) => {
    return (
        <div className="TeamTile">
            <h1 className="team-label"><Link to={`/teams/${getTeamCode(teamName)}`}>{teamName}</Link></h1>
        </div>
    )
}