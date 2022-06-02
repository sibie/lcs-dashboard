import '../styles/YearSelector.css';

import { React } from 'react';
import { Link } from 'react-router-dom';

// Generates list of year/split for which game data is available.

export const YearSelector = ({teamCode}) => {
    
    let years = [];

    // .env variables which indicate the timeframe for which APIs contain game data.
    const startYear = process.env.REACT_APP_DATA_START_YEAR;
    const endYear = process.env.REACT_APP_DATA_END_YEAR;
    const isMidYear = process.env.REACT_APP_IS_MID_YEAR;

    // Generating an array of options from .env values. Contains params which we can appened to render queried games.

    let latestYear = endYear;
    if(isMidYear === "YES") {
        years.push(latestYear + "-spring");
    } else {
        years.push(latestYear + "-summer");
        years.push(latestYear + "-spring");
    }

    for (let i = endYear - 1; i >= startYear; i-- ) {
        years.push(i + "-summer");
        years.push(i + "-spring");
    }

    // The actual component containing links to render the required games on right side of GamePage.

    return (
        <ol className="YearSelector">
        { years.map(year => (
            <li key={year}>
                <Link to={`/teams/${teamCode}/games/${year.split("-")[0]}/{${year.split("-")[1]}}`}>{year.split("-")[0]} - {year.split("-")[1].toUpperCase()}</Link>
            </li>
        )) }
        </ol>
    )
}