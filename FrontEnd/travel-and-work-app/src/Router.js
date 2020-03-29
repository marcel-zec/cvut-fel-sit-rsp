import React from "react";
import Home from "./Components/Home/Home";
import Logout from "./Components/Logout";
import Login from "./Components/Login";
import Profile from "./Components/Profile/Profile";
import ProfileTrips from "./Components/Profile/Trips/ProfileTrips";
import ProfileDetails from "./Components/Profile/ProfileDetails";
import ProfileAchievments from "./Components/Profile/ProfileAchievments";
import TripCreate from "./Components/TripCreate";
import TripEdit from "./Components/TripEdit";
import TripHistoryList from "./Components/TripHistoryList";
import Register from "./Components/Register";
import { Redirect, Switch, Route } from "react-router-dom";
import TripDetail from "./Components/Home/TripDetail";

function Router(props) {
    const { auth } = props;

    const allowAuth = component => {
        /* if (auth.authenticated) {
            return component;
        } else {
            return (
                <Redirect to={{pathname: "/login"}}/>
            )
        }*/
        return component;
    };

    const allowGuest = component => {
        /* if (!auth.authenticated) {
            return component;
        } else {
            return (
                <Redirect to={{pathname: "/"}}/>
            )
        }*/
        return component;
    };

    return (
        <div>
            <Route path="/profile" component={Profile} />
            <Route path="/profile/details" component={ProfileDetails} />
            <Route path="/profile/achievments" component={ProfileAchievments} />
            <Route path="/profile/trips" component={ProfileTrips} />
            <Switch>
                {/*Global*/}
                <Route
                    path="/login"
                    exact={true}
                    render={() => {
                        return allowGuest(<Login />);
                    }}
                />
                <Route path="/logout" exact component={Logout} />
                {/*User*/}
                <Route path="/register" exact component={Register} />
                <Route
                    path="/"
                    exact={true}
                    render={() => {
                        return allowAuth(<Home />);
                    }}
                />
                <Route path="/trips" exact component={Home} />
                <Route path="/trips/:id" component={TripDetail} />

                <Route
                    path="/trips/history"
                    exact
                    component={TripHistoryList}
                />
                {/*Admin*/}
                <Route path="/trips/create" exact component={TripCreate} />
                <Route path="/trips/:id/edit" exact component={TripEdit} />
            </Switch>
        </div>
    );
}

function mapStateToProps(state) {
    const { auth } = state;

    return {
        auth
    };
}
/*
export default connect(
    mapStateToProps
)(Router);
*/
export default Router;
