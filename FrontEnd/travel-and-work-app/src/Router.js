import React from "react";
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

function Router(props) {
  const { auth } = props;

  const allowAuth = (component) => {
    /* if (auth.authenticated) {
            return component;
        } else {
            return (
                <Redirect to={{pathname: "/login"}}/>
            )
        }*/
    return component;
  };

  const allowGuest = (component) => {
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

        {/*Admin*/}
        <Route path="/trip" exact component={IndexTrip} />
        <Route path="/trip/create" exact component={CreateTrip} />
        <Route path="/trip/:id/edit" exact component={EditTrip} />

        <Route path="/achievement" exact component={IndexAchievement} />
        <Route path="/achievement/create" exact component={CreateAchievement} />
        <Route path="/achievement/:id/edit" exact component={EditAchievement} />

        <Route path="/category" exact component={IndexCategory} />
        <Route path="/category/create" exact component={CreateCategory} />
        <Route path="/category/:id/edit" exact component={EditCategory} />

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
