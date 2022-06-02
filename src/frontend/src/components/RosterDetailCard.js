import '../styles/RosterDetailCard.css';

import { React } from 'react';
import { Link } from 'react-router-dom';

// This component is used to display each player on the roster as part of Team page.

export const RosterDetailCard = ({roster}) => {
    const playerRoute = `/players/${roster.gamerTag}`;
    return (
        <div className="RosterDetailCard">
            <h3 className="player-name" key={roster.id}><Link to={playerRoute}>{roster.gamerTag}</Link></h3>
        </div>
    );
}