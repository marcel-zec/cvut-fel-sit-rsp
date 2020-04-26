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
    state = { trip: null, selectedSession : {id:null, from_date:null,to_date:null, price:null}};
    sessionsIds = [];

    async componentDidMount() {
        const response = await fetch(
            `http://localhost:8080/trip/` + this.props.match.params.id
        );
        const data = await response.json();
        //data.sessions = [{id:2, from_date:"5.cerva",to_date:"7.zari", price:1999}];
        //data.sessions = [];
        console.log(data);
        //data.rating = 4.7;
        //data.reviews = [{author:"František Omáčka",rating:3.5,date:"7.srpna 2020",note:"Venenatis quis, ante. Maecenas fermentum, sem in pharetra pellentesque, velit turpis volutpat ante, in pharetra metus odio a lectus. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Pellentesque sapien. Duis pulvinar. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo. Mauris tincidunt sem sed arcu."},{author:"Tomáš Omáčka",rating:4.0,date:"7.zari 2020",note:"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Etiam quis quam. Donec iaculis gravida nulla. Etiam sapien elit, consequat eget, tristique non, venenatis quis, ante. Maecenas fermentum, sem in pharetra pellentesque, velit turpis volutpat ante, in pharetra metus odio a lectus. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Pellentesque sapien. Duis pulvinar. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo. Mauris tincidunt sem sed arcu."}]
        this.setState({ trip: data});
        if (data.sessions.length > 0){
            this.setState({ selectedSession: data.sessions[0]});
        }
        this.sessionsIds = data.sessions.map(function(session){
            return session.id;
        });
        //TODO: nacist prihlaseneho usera
    }
    sessionTripChange = async (selectElement) => {
        const optId = selectElement.value;
        if (this.sessionsIds.includes(parseInt(optId))){
            //update selectedSession
            const session = this.state.trip.sessions.find(session => session.id == optId);
            this.setSelectedSession(session);
        }
    }
    setSelectedSession(session){
        this.setState({selectedSession : session});
    }
    submitTrip(event){
        event.preventDefault();
        console.log(this.state.selectedSession);
        //validate user and trip

    }
    renderRating(rating){
        let starsElement=[];
        if(rating == 0){
            return <span style={{color: 'black'}}>Trip zatím nemá žádné hodnocení</span>;
        }
        for (var i = 1; i <= rating; i++) {
            starsElement.push(<FontAwesomeIcon key={i} icon="star" />);
        }
        if(rating-starsElement.length >= 0.5){
            starsElement.push(<FontAwesomeIcon key={starsElement.length+1} icon="star-half" />);
        }
        return starsElement;
    }
    renderAchievements(achievements, message="No achievements are required"){
        if (achievements.length==0){
            return message;
        }
        let toReturn = [];
        achievements.forEach(element => {
            toReturn.push(
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
        return toReturn;
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
            //setting sessions trip
            let options = null;
            let sessionBlock = null;
            let dateTitle = "Date";
            
            //create list of options for select when more sessions(dates)
            let optionArray = [];
            this.state.trip.sessions.forEach(element => {
                optionArray.push(
                    <option key={element.id} value={element.id}>
                        {element.from_date + " " + element.to_date}
                    </option>
                );
            });
            //if trip does not have any session show it
            if (this.state.trip.sessions == 0){
                options = (
                    <div>
                        Trip nemá žádnou session
                    </div>
                );
                sessionBlock = <div className="trip_price">Nelze zakoupit</div>
            }else{
                options = (
                    <Form.Control as="select" id="dateSessionSelect" onChange={(event) => this.sessionTripChange(event.target)}>
                        {optionArray}
                    </Form.Control>
                );
                sessionBlock =  (<div>
                                    <div className="trip_price">
                                        <span id="tripPrice"> {this.state.selectedSession.price}</span>
                                        Kč
                                    </div>
                                    <Button className="submit" variant="primary" type="submit" onClick={(event) => this.submitTrip(event)}> Koupit </Button>
                                </div>);
            }
            //set correct date(s)
            if (this.state.trip.sessions.length > 1) {
                dateTitle = "Dates";
            }
            //setting reviews
            const reviews = this.state.trip.reviews;
            const reviewsBlock = reviews.map((review) =>
                <div className="review">
                    <Row>
                        <Col className="rev_author" xs={6}><FontAwesomeIcon icon="user-alt"/><span>{review.author}</span></Col>
                        <Col className="rev_rating" xs={6}>{this.renderRating(review.rating)}</Col>
                    </Row>
                    <Row>
                        <Col><p className="note">{review.note}</p></Col>
                    </Row>
                </div>
            );
            //render page
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
                                    <div className="review_element">{this.renderRating(this.state.trip.rating)}</div>
                                        {sessionBlock}
                                        
                                        </div>
                                    </Col>
                                </Row>
                                </Form>
                            </Col>
                        </Card.Body>
                    </Card>
                    <Row>
                        <Col className="col-4 trip_restriction">
                            <Card className="mb-3">
                                <Card.Body>
                                    <Card.Title>
                                        Informations about trip
                                    </Card.Title>
                                    <Card.Subtitle className="mb-2 text-muted">
                                       <FontAwesomeIcon icon="money-bill" /> Deposit
                                    </Card.Subtitle>
                                    <Card.Text>
                                        {this.state.trip.deposit} Kč
                                    </Card.Text>
                                    <Card.Subtitle className="mb-2 text-muted">
                                    <FontAwesomeIcon icon="minus-circle" />Minimum level required
                                    </Card.Subtitle>
                                    <Card.Text>
                                        {this.state.trip.required_level}
                                    </Card.Text>
                                </Card.Body>
                            </Card>
                            <Card className="mb-3">
                                <Card.Body>
                                    <Card.Title>
                                        Required certifications
                                    </Card.Title>
                                    <ListGroup variant="flush">
                                        
                                    </ListGroup>
                                </Card.Body>
                            </Card>
                            <Card className="mb-3">
                                <Card.Body>
                                    <Card.Title>
                                        Required achievements
                                    </Card.Title>
                                    <ListGroup variant="flush">
                                        {this.renderAchievements(this.state.trip.required_achievements_special)}
                                        <Card.Subtitle className="subtitle">Categorized:</Card.Subtitle>
                                        {this.renderAchievements(this.state.trip.required_achievements_categorized)}
                                    </ListGroup>
                                </Card.Body>
                            </Card>
                            <Card className="mb-3">
                                <Card.Body>
                                    <Card.Title>Gain achievements</Card.Title>
                                    <ListGroup variant="flush">
                                    {this.renderAchievements(this.state.trip.gain_achievements_special,"No gain achievement")}
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
                            <Card id="trip_reviews" className="mb-5 trip_reviews">
                                <Card.Body>
                                    <Card.Title>Reviews</Card.Title>
                                    {reviewsBlock}
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
