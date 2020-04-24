import React, { useEffect } from "react";
import logo from "./logo.svg";
import "./App.css";
//na routovanie v aplikacii
import Router from "./Router";
//kniznica na HTTP dotazy
import Navigation from "./Components/Navigation";
import { library } from "@fortawesome/fontawesome-svg-core";
import {
    faTrophy,
    faPowerOff,
    faCog,
    faSuitcase,
    faStar,
    faStarHalf,
    faSwimmer,
    faMedal,
    faAward,
    faCommentMedical,
    faCommentDots,
    faShieldAlt,
    faCrown,
    faWater,
    faSwimmingPool,
    faLocationArrow,
    faTrashAlt,
    faGraduationCap,
    faFish,
    faMountain,
    faFlag,
    faSnowflake,
    faHamburger,
    faPizzaSlice,
    faGlassCheers,
    faPastafarianism,
    faCompass,
    faRunning,
    faCampground,
    faChessRook,
    faChevronLeft,
    faTimes,
    faClock
} from "@fortawesome/free-solid-svg-icons";
import { faStar as emptyStar } from "@fortawesome/free-regular-svg-icons";

//allow use string names of icons from FontAwesome
library.add(
    faTrophy,
    faPowerOff,
    faCog,
    faSuitcase,
    faStar,
    faStarHalf,
    emptyStar,
    faSwimmer,
    faMedal,
    faAward,
    faCommentMedical,
    faCommentDots,
    faShieldAlt,
    faCrown,
    faWater,
    faSwimmingPool,
    faLocationArrow,
    faTrashAlt,
    faGraduationCap,
    faFish,
    faMountain,
    faFlag,
    faSnowflake,
    faHamburger,
    faPizzaSlice,
    faGlassCheers,
    faPastafarianism,
    faCompass,
    faRunning,
    faCampground,
    faChessRook,
    faChevronLeft,
    faTimes,
    faClock
);

class App extends React.Component {
    render() {
        return (
            //obalia sa vsetky komponenty, ktore maju zvladat routovanie

            <div className="App">
                <Navigation />
                <Router />
                {/* router z Router.js */}
            </div>
        );
    }
}

export default App;
