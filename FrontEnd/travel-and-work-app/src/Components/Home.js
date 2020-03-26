import React from "react";
import { Container } from "react-bootstrap";
import Trip from "./Trip";
import CardColumns from "react-bootstrap/CardColumns";

class Home extends React.Component {
    /*
    async componentDidMount(){
        const response = await fetch(`http://localhost:8080/trip`);
        const data = await response.json();
        console.log(data);
      }
      */

    state = {
        trips: [
            {
                name: "Fdsaiom",
                price: 2000,
                xp: 15,
                stars: 5,
                dateFrom: "1995-12-17",
                dateTo: "1995-12-17"
            },
            {
                name: "Ndasmidas",
                price: 1350,
                xp: 20,
                stars: 3,
                dateFrom: "1995-12-17",
                dateTo: "1995-12-17"
            },
            {
                name: "Ddasmio",
                price: 2200,
                xp: 25,
                stars: 2,
                dateFrom: "1995-12-17",
                dateTo: "1995-12-17"
            },
            {
                name: "Mdmaoidsa",
                price: 800,
                xp: 5,
                stars: 1,
                dateFrom: "1995-12-17",
                dateTo: "1995-12-17"
            },
            {
                name: "Ldsinuifds",
                price: 1500,
                xp: 10,
                stars: 5,
                dateFrom: "1995-12-17",
                dateTo: "1995-12-17"
            }
        ]
    };

    render() {
        return (
            <Container>
                <CardColumns>
                    {this.state.trips.map(trip => {
                        return <Trip trip={trip} />;
                    })}
                </CardColumns>
            </Container>
        );
    }
}

export default Home;
