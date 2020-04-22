import React, { useContext } from "react";
import Home from "./Components/Home/Trip/Index";
import Logout from "./Components/Auth/Logout";
import Login from "./Components/Auth/Login";
import Profile from "./Components/Profile/Profile";
import ProfileTrips from "./Components/Profile/Trips/ProfileTrips";
import ProfileDetails from "./Components/Profile/ProfileDetails";
import ProfileAchievments from "./Components/Profile/ProfileAchievments";
import CreateTrip from "./Components/Admin/Trip/Create";
import EditTrip from "./Components/Admin/Trip/Edit";
import IndexTrip from "./Components/Admin/Trip/Index";
import Register from "./Components/Register";
import { Redirect, Switch, Route } from "react-router-dom";
import TripDetail from "./Components/Home/Trip/Detail";
import IndexAchievement from "./Components/Admin/Achievement/Index";
import CreateAchievement from "./Components/Admin/Achievement/Create";
import EditAchievement from "./Components/Admin/Achievement/Edit";
import IndexCategory from "./Components/Admin/Category/Index";
import CreateCategory from "./Components/Admin/Category/Create";
import EditCategory from "./Components/Admin/Category/Edit";
import IndexUser from "./Components/Admin/User/Index";
import { appContext } from "./appContext";

function Router(props) {
    //const { auth } = props;
    const context = useContext(appContext);

    const allowAuth = (component) => {
        if (context.user != null) {
            return component;
        } else {
            return <Redirect to={{ pathname: "/login" }} />;
        }
    };

    const allowAuthAdmin = (component) => {
        if (context.user != null) {
            if (context.user.role == "ADMIN") {
                return component;
            } else {
                return <Redirect to={{ pathname: "/" }} />;
            }
        } else {
            return <Redirect to={{ pathname: "/login" }} />;
        }
    };

    const allowGuest = (component) => {
        if (context.user === null) {
            console.log(context.user);
            return component;
        } else {
            return <Redirect to={{ pathname: "/" }} />;
        }
    };

    return (
        <div>
            <Route
                path="/profile"
                render={() => {
                    return allowAuth(<Profile />);
                }}
            />
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
                <Route
                    path="/logout"
                    render={() => {
                        return allowAuth(<Logout />);
                    }}
                />
                {/*User*/}
                <Route
                    path="/register"
                    exact={true}
                    render={() => {
                        return allowGuest(<Register />);
                    }}
                />
                <Route path="/" exact={true} component={Home} />

                <Route path="/trips/:id" component={TripDetail} />

                {/*Admin*/}
                <Route path="/trip" exact component={IndexTrip} />
                <Route path="/trip/create" exact component={CreateTrip} />
                <Route path="/trip/:id/edit" exact component={EditTrip} />

                {/*
                <Route
                    path="/trip"
                    exact={true}
                    render={() => {
                        return allowAuthAdmin(<IndexTrip />);
                    }}
                />

                <Route
                    path="/trip/create"
                    exact={true}
                    render={() => {
                        return allowAuthAdmin(<CreateTrip />);
                    }}
                />

                <Route
                    path="/trip/:id/edit"
                    exact={true}
                    render={() => {
                        return allowAuthAdmin(<EditTrip />);
                    }}
                />
                */}

                <Route path="/achievement" exact component={IndexAchievement} />
                <Route
                    path="/achievement/create"
                    exact
                    component={CreateAchievement}
                />
                <Route
                    path="/achievement/:id/edit"
                    exact
                    component={EditAchievement}
                />

                {/*
                <Route
                    path="/achievement"
                    exact={true}
                    render={() => {
                        return allowAuthAdmin(<IndexAchievement />);
                    }}
                />

                <Route
                    path="/achievement/create"
                    exact={true}
                    render={() => {
                        return allowAuthAdmin(<CreateAchievement />);
                    }}
                />

                <Route
                    path="/achievement/:id/edit"
                    exact={true}
                    render={() => {
                        return allowAuthAdmin(<EditAchievement />);
                    }}
                />
                */}

                <Route path="/category" exact component={IndexCategory} />
                <Route
                    path="/category/create"
                    exact
                    component={CreateCategory}
                />
                <Route
                    path="/category/:id/edit"
                    exact
                    component={EditCategory}
                />

                {/*
                <Route
                    path="/category"
                    exact={true}
                    render={() => {
                        return allowAuthAdmin(<IndexCategory />);
                    }}
                />

                <Route
                    path="/category/create"
                    exact={true}
                    render={() => {
                        return allowAuthAdmin(<CreateCategory />);
                    }}
                />

                <Route
                    path="/category/:id/edit"
                    exact={true}
                    render={() => {
                        return allowAuthAdmin(<EditCategory />);
                    }}
                />
                */}

                <Route path="/user" exact component={IndexUser} />
            </Switch>
        </div>
    );
}

function mapStateToProps(state) {
    const { auth } = state;

    return {
        auth,
    };
}
/*
export default connect(
    mapStateToProps
)(Router);
*/
export default Router;
