import React from 'react';
import logo from './logo.svg';
import './App.css';
//na routovanie v aplikacii
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

function App() {
  return (
    //obalia sa vsetky komponenty, ktore maju zvladat routovanie
    <Router>
        <Switch>   
                {/*Global*/}
                <Route path="/login" exact component={Login} />
                <Route path="/logout" exact component={Logout} />
                {/*User*/}
                <Route path="/register" exact component={Register} />
                <Route path="/" exact component={Home} />
                <Route path="/profile" component={Profile} />
                <Route path="/trips" component={TripList} />
                <Route path="/trips/:id" component={Trip} />
                <Route path="/trips/history" component={TripHistoryList} />
                <Route path="/trips/history/:id" component={TripHistory} />
        </Switch>    

    <div className="App">
      <header className="App-header">
        <h1>Ahojky</h1>
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Aplikacia hotova. Môžeme odovzdavať! 
        </p>
      </header>
    </div>

    </Router>
  );
}

export default App;
