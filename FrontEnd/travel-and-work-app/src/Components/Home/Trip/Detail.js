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
    sessionTripChange = async (event, selectElement) => {
        const dateSelect = document.getElementById("dateSessionSelect");
        let opt = dateSelect.options[dateSelect.selectedIndex];
        let newPrice = opt.getAttribute("sessionprice");
        document.getElementById("tripPrice").innerHTML = newPrice;
        //TODO: aktualizovat i state
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
            let price = null;
            let options = null;
            let dateTitle = "Date";
            

            if (this.state.trip.sessions.length == 1) {
                //set one date when only one session
                const session = this.state.trip.sessions[0];
                price = session.price;
                options = (
                    <Card.Text>
                        {session.from_date + " " + session.to_date}
                    </Card.Text>
                );
            } else if (this.state.trip.sessions.length > 1) {
                //create list of options for select when more sessions(dates)
                price = this.state.trip.sessions[0].price;
                let optionArray = [];
                this.state.trip.sessions.forEach(element => {
                    optionArray.push(
                        <option key={element.id} sessionid={element.id} sessionprice={element.price}>
                            {element.from_date + " " + element.to_date}
                        </option>
                    );
                });
                options = (
                    <Form.Control as="select" id="dateSessionSelect" onChange={(event) => this.sessionTripChange(event)}>
                        {optionArray}
                    </Form.Control>
                );
                dateTitle = "Dates";
            }else{
                price = "trip nema session";
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
                <Container id="trip_detail">
                    <Card className="mb-3 trip_main">
                        <Card.Body className="d-flex flex-row">
                            <Col  xs={5} className="image">
                                <Image
                                    src="https://specials-images.forbesimg.com/imageserve/5db15891616a45000704761f/960x0.jpg?fit=scale"
                                    rounded
                                />
                            </Col>
                            <Col xs={7} className="trip_info">
                                <Row className="d-flex flex-column">
                                    <Col>
                                        <Card.Title className="trip_name">
                                            {this.state.trip.name}
                                        </Card.Title>
                                    </Col>
                                </Row>
                                <Form>
                                <Row>
                                    <Col>
                                        <Card.Title className="mb-2 text-muted">
                                           <FontAwesomeIcon icon="map-marker-alt" /> Location
                                        </Card.Title>
                                        <Card.Text>

                                            {this.state.trip.location}
                                        </Card.Text>
                                        <Card.Title className="mb-2 text-muted">
                                            <FontAwesomeIcon icon="map-signs" /> Points
                                        </Card.Title>
                                        <Card.Text>
                                            {this.state.trip.possible_xp_reward} XP
                                        </Card.Text>
                                        <Card.Title className="mb-2 text-muted">
                                           <FontAwesomeIcon icon="calendar-alt" /> {dateTitle}
                                        </Card.Title>
                                        {options}
                                    </Col>
                                    <Col >
                                    <div className="rev-price-buy">
                                        <div className="trip_price">
                                            <span id="tripPrice">
                                            {price}
                                            </span>
                                            Kč
                                        </div>
                                        

                                        <Button className="submit" variant="primary" type="submit"> Koupit </Button>
                                        </div>
                                    </Col>
                                </Row>
                                </Form>
                            </Col>
                        </Card.Body>
                    </Card>
                    <Row>
                        <Col className="col-4">
                            <Card className="mb-3">
                                <Card.Body>
                                    <Card.Title>
                                        Informations about trip
                                    </Card.Title>
                                    <Card.Subtitle className="mb-2 text-muted">
                                        Deposit
                                    </Card.Subtitle>
                                    <Card.Text>
                                        {this.state.trip.deposit} Kč
                                    </Card.Text>
                                    <Card.Subtitle className="mb-2 text-muted">
                                        Minimum level required
                                    </Card.Subtitle>
                                    <Card.Text>
                                        {this.state.trip.requiered_level}
                                    </Card.Text>
                                </Card.Body>
                            </Card>
                            <Card className="mb-3">
                                <Card.Body>
                                    <Card.Title>
                                        Required achievements
                                    </Card.Title>
                                    <ListGroup variant="flush">
                                        {requiredAchievements}
                                    </ListGroup>
                                </Card.Body>
                            </Card>

                            <Card className="mb-3">
                                <Card.Body>
                                    <Card.Title>Gain achievements</Card.Title>
                                    <ListGroup variant="flush">
                                        {gainAchievements}
                                    </ListGroup>
                                </Card.Body>
                            </Card>
                        </Col>
                        <Col className="col-8">
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
