import React, { useEffect } from "react";
import logo from "./logo.svg";
import "./App.css";
//na routovanie v aplikacii
import Router from "./Router";
//kniznica na HTTP dotazy
import Navigation from "./Components/Navigation";
import { library } from "@fortawesome/fontawesome-svg-core";
import { BrowserRouter } from "react-router-dom";
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
    faClock,
    faMapMarkerAlt,
    faMapSigns,
    faCalendarAlt,
    faUserAlt,
    faMoneyBill,
    faMinusCircle,
    faCheckCircle,
    faMoneyBillAlt
} from "@fortawesome/free-solid-svg-icons";
import { faStar as emptyStar } from "@fortawesome/free-regular-svg-icons";
import { appContext } from "./appContext";
import Cookies from "js-cookie";

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
    faClock,
    faMapMarkerAlt,
    faMapSigns,
    faCalendarAlt,
    faUserAlt,
    faMoneyBill,
    faMinusCircle,
    faCheckCircle,
    faMoneyBillAlt
);

class App extends React.Component {
    state = {
        user: null,
    };

    logout = () => {
        Cookies.remove("JSESSIONID");
        this.setState({ user: null });
    };

    login = (user) => {
        this.setState({ user: user });
        console.log("from login");
        console.log(this.state.user);
    };

    async componentDidMount() {
        if (Cookies.get("JSESSIONID") && this.state.user == null) {
            console.log("app condition");
            fetch(`http://localhost:8080/user/current`, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                },
                credentials: "include",
            })
                .then((response) => {
                    if (response.ok) return response.json();
                    else {
                        this.logout();
                        return null;
                    }
                })
                .then((data) => {
                    console.log(data);
                    this.login(data);
                });
        }
    }

    render() {
        const value = {
            user: this.state.user,
            logout: this.logout,
            login: this.login,
        };

        console.log(value);
        return (
            //obalia sa vsetky komponenty, ktore maju zvladat routovanie
            <appContext.Provider value={value}>
                <BrowserRouter>
                    <div className="App">
                        <Navigation />
                        <Router />
                        {/* router z Router.js */}
                    </div>
                </BrowserRouter>
            </appContext.Provider>
        );
    }
}

export default App;
