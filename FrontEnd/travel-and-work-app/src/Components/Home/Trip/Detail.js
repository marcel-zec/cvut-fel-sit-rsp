import React from "react";
import Card from "react-bootstrap/Card";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Button, Row, Col, Container, Image, ListGroup } from "react-bootstrap";
import { Link } from "react-router-dom";
import axios from "axios";
import Spinner from "react-bootstrap/Spinner";
import { Form } from "react-bootstrap";
import AchievmentModal from "../../SmartGadgets/AchievementModal";

class Detail extends React.Component {
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

            //setting acheivments
            let requiredAchievements = "none";
            let gainAchievements = "none";
            if (this.state.trip.required_achievements.length > 0) {
                requiredAchievements = [];
                this.state.trip.required_achievements.forEach(element => {
                    requiredAchievements.push(
                        <ListGroup.Item>
                            <AchievmentModal
                                titleBeforeIcon={true}
                                icon={element.icon}
                                title={element.name}
                                description={element.description}
                            />
                        </ListGroup.Item>
                    );
                });
            }
            if (this.state.trip.gain_achievements.length > 0) {
                gainAchievements = [];
                this.state.trip.gain_achievements.forEach(element => {
                    gainAchievements.push(
                        <ListGroup.Item>
                            <AchievmentModal
                                titleBeforeIcon={true}
                                icon={element.icon}
                                title={element.name}
                                description={element.description}
                            />
                        </ListGroup.Item>
                    );
                });
            }

            return (
                <Container>
                    <Card className="mb-3">
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
                    <Row>
                        <Col>
                            <Card style={{ width: "18rem" }} className="mb-3">
                                <Card.Body>
                                    <Card.Title>
                                        Informations about trip
                                    </Card.Title>
                                    <Card.Subtitle className="mb-2 text-muted">
                                        Deposit
                                    </Card.Subtitle>
                                    <Card.Text>
                                        {this.state.trip.deposit}
                                    </Card.Text>
                                    <Card.Subtitle className="mb-2 text-muted">
                                        Minimum level required
                                    </Card.Subtitle>
                                    <Card.Text>
                                        {this.state.trip.requiered_level}
                                    </Card.Text>
                                </Card.Body>
                            </Card>

                            <Card style={{ width: "18rem" }} className="mb-3">
                                <Card.Body>
                                    <Card.Title>
                                        Required achievements
                                    </Card.Title>
                                    <ListGroup variant="flush">
                                        {requiredAchievements}
                                    </ListGroup>
                                </Card.Body>
                            </Card>

                            <Card style={{ width: "18rem" }} className="mb-3">
                                <Card.Body>
                                    <Card.Title>Gain achievements</Card.Title>
                                    <ListGroup variant="flush">
                                        {gainAchievements}
                                    </ListGroup>
                                </Card.Body>
                            </Card>
                        </Col>
                        <Col>
                            <Card className="mb-5">
                                <Card.Body>
                                    <Card.Title>Description</Card.Title>

                                    <Card.Text>
                                        {this.state.trip.description}
                                    </Card.Text>
                                </Card.Body>
                            </Card>
                        </Col>
                    </Row>
                </Container>
            );
        }
    }
}

export default Detail;
