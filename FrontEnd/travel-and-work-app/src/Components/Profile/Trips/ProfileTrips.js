import React from "react";
import Profile from "../Profile";
import { Container } from "react-bootstrap";
import TripHistory from "./TripHistory";

class ProfileTrips extends Profile {
    state = {
        tripHistory: [
            {
                name: "Fdsaiom",
                xp: 12,
                tripDate: "1995-12-17",
                achievments: [
                    { id: 1, title: "Aaa", description: "Aaa description" },
                    { id: 2, title: "Bbb", description: "Bbb description" }
                ],
                comment: {
                    commentDate: "1996-02-11",
                    content:
                        "Lorem ipsum damlkd nuids mkoda dainuinjuida odasjuida miodauida danuomodmaoi, dakiokioda daiodsaiojk. Ddajoiiojjiioa dasd mmfydc ahuida 12 djuidjas dajiosda. Cadjis mmhusa miouynouicnsd. :)"
                }
            },
            {
                name: "Samujifds",
                xp: 2,
                tripDate: "1995-04-02",
                achievments: [
                    { id: 3, title: "Ccc", description: "Ccc description" }
                ]
            },
            {
                name: "Mdsaerrr",
                xp: 15,
                tripDate: "1995-06-19",
                achievments: [
                    { id: 4, title: "Ddd", description: "Ddd description" }
                ],
                comment: {
                    commentDate: "1996-02-11",
                    content:
                        "Lorem ipsum damlkd nuids mkoda dainuinjuida odasjuida miodauida danuomodmaoi, dakiokioda daiodsaiojk. Ddajoiiojjiioa dasd mmfydc ahuida 12 djuidjas dajiosda. Cadjis mmhusa miouynouicnsd. :)"
                }
            },
            {
                name: "Ldsamiodsa",
                xp: 8,
                tripDate: "1995-04-15",
                achievments: []
            }
        ]
    };
    render() {
        return (
            <Container>
                {this.state.tripHistory.map(trip => {
                    return <TripHistory trip={trip} />;
                })}
            </Container>
        );
    }
}

export default ProfileTrips;
