import './App.css';

import { HashRouter as Router, Routes, Route } from 'react-router-dom';

import { TeamPage } from './pages/TeamPage';
import { PlayerPage } from './pages/PlayerPage';
import { GamePage } from './pages/GamePage';
import { HomePage } from './pages/HomePage';

// Using BrowserRouter to define routes to each of the available pages in the application.

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/teams/:teamCode/games/:year/:split" element={<GamePage />} />
          <Route path="/teams/:teamCode" element={<TeamPage />} />
          <Route path="/players/:gamerTag" element={<PlayerPage />} />
          <Route path="/" element={<HomePage />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
