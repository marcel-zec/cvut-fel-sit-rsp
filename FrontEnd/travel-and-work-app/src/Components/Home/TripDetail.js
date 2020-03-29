import React from "react";
import Card from "react-bootstrap/Card";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Button, Row, Col, Container, Image } from "react-bootstrap";
import { Link } from "react-router-dom";
import axios from "axios";
import Spinner from "react-bootstrap/Spinner";
import { Form } from "react-bootstrap";

class TripDetail extends React.Component {
    state = { trip: null };

    async componentDidMount() {
        const response = await fetch(
            `http://localhost:8080/trip/` + this.props.match.params.id
        );
        const data = await response.json();
        console.log(data);
        this.setState({ trip: data });
    }

    render() {
        if (this.state.trip === null) {
            return (
                <Container className="p-5">
                    <Spinner animation="border" role="status">
                        <span className="sr-only">Loading...</span>
                    </Spinner>
                </Container>
            );
        } else {
            //set correct date(s)
            let options = null;
            let dateTitle = "Date";
            if (this.state.trip.sessions.length == 1) {
                //set one date when only one session
                const session = this.state.trip.sessions[0];
                options = (
                    <Card.Text>
                        {session.from_date + " " + session.to_date}
                    </Card.Text>
                );
            } else if (this.state.trip.sessions.length > 1) {
                //create list of options for select when more sessions(dates)
                let optionArray = [];
                this.state.trip.sessions.forEach(element => {
                    optionArray.push(
                        <option>
                            {element.from_date + " " + element.to_date}
                        </option>
                    );
                });
                options = (
                    <Form.Control as="select">{optionArray}</Form.Control>
                );
                dateTitle = "Dates";
            }
            return (
                <Container>
                    <Card>
                        <Card.Body className="d-flex flex-row">
                            <Col>
                                <Image
                                    src="https://pbs.twimg.com/profile_images/617828221707513856/ygo8Rtr__400x400.jpg"
                                    rounded
                                />
                            </Col>
                            <Col>
                                <Row className="d-flex flex-column">
                                    <Card.Title>
                                        {this.state.trip.name}
                                    </Card.Title>
                                </Row>
                                <Row>
                                    <Col>
                                        <Card.Title className="mb-2 text-muted">
                                            Location
                                        </Card.Title>
                                        <Card.Text>
                                            {this.state.trip.location}
                                        </Card.Text>
                                        <Card.Title className="mb-2 text-muted">
                                            Points
                                        </Card.Title>
                                        <Card.Text>
                                            {this.state.trip.possible_xp_reward}
                                        </Card.Text>
                                        <Card.Title className="mb-2 text-muted">
                                            {dateTitle}
                                        </Card.Title>
                                        {options}
                                    </Col>
                                    <Col>dsadsa</Col>
                                </Row>
                            </Col>
                        </Card.Body>
                    </Card>
                </Container>
            );
        }
    }
}

export default TripDetail;
