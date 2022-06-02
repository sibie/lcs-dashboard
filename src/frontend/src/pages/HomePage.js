import '../styles/HomePage.css';

import { React } from 'react';
import { TeamTile } from '../components/TeamTile';

export const HomePage = () => {

    /* 
        List of available teams. Doing it this way to avoid another needless API call, but putting them into
        .env files would probably be better so we don't have to change the code if the franchise teams change.
    */
    const teams = ["100 Thieves", "Cloud9", "Counter Logic Gaming", "Dignitas QNTMPAY", "Evil Geniuses", "Flyquest", "Golden Guardians", "Immortals", "Team SoloMid", "Team Liquid" ];
    
    // The view being rendered on at root URL, with TeamTiles for each team to navigate to respective Team page.

    return (
        <div className="HomePage">
            <div className="header-section">
                <h1 className="app-name">LCS Dashboard</h1>
                <p className="about">The League Championship Series (LCS) is the top level of professional League of Legends in North America. This esports league is run by Riot Games and comprises of ten franchise teams. This utility can be used to track game stats!</p>
            </div>
            <div className="team-grid">
                { teams.map(team => <TeamTile key={team} teamName={team} />)}
            </div>
        </div>
    );
}