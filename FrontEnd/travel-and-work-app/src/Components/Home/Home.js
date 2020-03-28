import React from "react";
import { Container } from "react-bootstrap";
import TripSmall from "./TripSmall";
import CardColumns from "react-bootstrap/CardColumns";

class Home extends React.Component {
    async componentDidMount() {
        const response = await fetch(`http://localhost:8080/trip`);
        const data = await response.json();
        console.log(data);
        this.setState({ trips: data });
    }

    state = { trips: [] };

    render() {
        return (
            <Container>
                <CardColumns>
                    {this.state.trips.map(trip => {
                        return <TripSmall key={trip.short_name} trip={trip} />;
                    })}
                </CardColumns>
            </Container>
        );
    }
}

export default Home;
