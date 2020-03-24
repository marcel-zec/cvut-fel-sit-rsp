import React, { useEffect } from "react";
import logo from "./logo.svg";
import "./App.css";
//na routovanie v aplikacii
import Router from "./Router";
//kniznica na HTTP dotazy
import axios from "axios";
import Home from "./Components/Home";
import Navigation from "./Components/Navigation";

class App extends React.Component {
    render() {
        return (
            //obalia sa vsetky komponenty, ktore maju zvladat routovanie

            <div className="App">
                <Navigation />
                {/* router z Router.js */}
                <Router />
            </div>
        );
    }
}

export default App;
