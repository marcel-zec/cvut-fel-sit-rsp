import React from "react";
import Home from "./Components/Home";
import Logout from "./Components/Logout";
import Login from "./Components/Login";
import Profile from "./Components/Profile";
import Trip from "./Components/Trip";
import TripCreate from "./Components/TripCreate";
import TripEdit from "./Components/TripEdit";
import TripHistoryList from "./Components/TripHistoryList";
import TripHistory from "./Components/TripHistory";
import TripList from "./Components/TripList";
import Register from "./Components/Register";
import { Redirect, Switch, Route } from "react-router-dom";

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
            <Route path="/profile" exact component={Profile} />
            <Route path="/trips" exact component={TripList} />
            <Route path="/trips/:id" component={Trip} />
            <Route path="/trips/history" exact component={TripHistoryList} />
            <Route path="/trips/history/:id" component={TripHistory} />
            {/*Admin*/}
            <Route path="/trips/create" exact component={TripCreate} />
            <Route path="/trips/:id/edit" exact component={TripEdit} />
        </Switch>
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
