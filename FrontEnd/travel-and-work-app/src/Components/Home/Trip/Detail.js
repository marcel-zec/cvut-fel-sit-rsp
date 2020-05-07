import React from "react";
import Card from "react-bootstrap/Card";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Button, Row, Col, Container, Image, ListGroup } from "react-bootstrap";
import { Link } from "react-router-dom";
import axios from "axios";
import Spinner from "react-bootstrap/Spinner";
import { Form,Modal } from "react-bootstrap";
import AchievmentModal from "../../SmartGadgets/AchievementModal";
import AchievementListInline from "../../SmartGadgets/AchievementListInline";
import { appContext } from "../../../appContext"

class Detail extends React.Component {
    state = { trip: null, selectedSession : {id:null, from_date:null,to_date:null, price:null}};
    user = null;
    formIsValid = false;
    static contextType = appContext;
    userDTO = {achievements_special:null,certifications:null,achievements_categorized:null,level:1};
    sessionsIds = [];
    
    async componentDidMount() {
        const response = await fetch(
            `http://localhost:8080/trip/` + this.props.match.params.id,{
                method: "GET",
                mode: "cors",
                credentials: "include",
                headers: {
                    "Content-Type": "application/json",
                },
            });
        );
        const data = await response.json();
        //data.sessions = [{id:2, from_date:"5.cerva",to_date:"7.zari", price:1999}];
        //data.sessions = [];
        console.log(data);
        this.user = this.context.user;
        this.userDTO.achievements_special = [{id: 2, name: "Kuchař ryby fugu", description: "Uživatel má zkušenosti s přípravou jedovatých ryb fugu.", icon: "fish", recieved_via_enrollments: Array(0)}
        ,{id: 3, name: "Kuchař ryby fugu", description: "Uživatel má zkušenosti s přípravou jedovatých ryb fugu.", icon: "fish", recieved_via_enrollments: Array(0)}
                                            ,{id: 4, name: "Horolezec", description: "Uživatel má zkušenosti s lezením po skalách.", icon: "mountain", recieved_via_enrollments: Array(0)}];
        this.userDTO.certifications = [{id: 2, name: "Certifikát Angličtina B2", description: "Uživatel má certifikát B2 v anglickém jazyku.", icon: "graduation-cap"}];
        this.userDTO.achievements_categorized = [{id: 3, name: "Kuchtík", description: "Uživatel byl jednou vařit.", icon: "hamburger"}];
        data.rating = 4.7;
        data.reviews = [{author:"František Omáčka",rating:3.5,date:"7.srpna 2020",note:"Venenatis quis, ante. Maecenas fermentum, sem in pharetra pellentesque, velit turpis volutpat ante, in pharetra metus odio a lectus. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Pellentesque sapien. Duis pulvinar. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo. Mauris tincidunt sem sed arcu."},{author:"Tomáš Omáčka",rating:4.0,date:"7.zari 2020",note:"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Etiam quis quam. Donec iaculis gravida nulla. Etiam sapien elit, consequat eget, tristique non, venenatis quis, ante. Maecenas fermentum, sem in pharetra pellentesque, velit turpis volutpat ante, in pharetra metus odio a lectus. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Pellentesque sapien. Duis pulvinar. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo. Mauris tincidunt sem sed arcu."}]
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
    submitTrip(event,formElement){
        event.preventDefault();
        console.log(this.state.selectedSession);
        //check checbox is checked
        let checboxIsChecked = false;
        checboxIsChecked = document.querySelector("#checkboxAgreement input").checked;
        if(checboxIsChecked&&this.formIsValid){
            console.log("READY K ODESLANI");
        }
    }
    validateUserAchievement(achievement,userList){
        return userList.some(item => item.id == achievement.id);
    }
    validatePurchase(event){
        event.preventDefault();
        document.querySelector(".popup_background").classList.remove("hidden");
        let specialValidate = false;
        let certificateslValidate = false;
        let categorizedValidate = false;
        let levelPassed = false;


        const req_ach_special = this.state.trip.required_achievements_special;
        const req_ach_categorized = this.state.trip.required_achievements_categorized;
        const req_cerf = this.state.trip.required_achievements_certificate;
        const minlevel = this.state.trip.required_level;

        specialValidate = req_ach_special.every(val => this.validateUserAchievement(val,this.userDTO.achievements_special));
        categorizedValidate = req_ach_categorized.every(val => this.validateUserAchievement(val,this.userDTO.achievements_categorized));
        certificateslValidate = req_cerf.every(val => this.validateUserAchievement(val,this.userDTO.certifications));
        levelPassed = this.userDTO.level >= minlevel;


        if(specialValidate&&categorizedValidate&&certificateslValidate&&levelPassed){
            console.log("je to validdni");
            this.formIsValid = true;
        }else{
            console.log("neni to validni");
            document.querySelector("#confirmPurchase").style.display = "none";
            document.querySelector("#checkboxAgreement").style.display = "none";
            document.querySelector("#validationFalse").style.display = "block";
        }
    }
    closeValidateWindow(element){
        document.querySelector(".popup_background").classList.add("hidden");
    }
    renderRating(rating){
        let starsElement=[];
        if(rating == 0){
            return <span style={{color: 'black'}}>Trip has not any review</span>;
        }
        for (var i = 1; i <= rating; i++) {
            starsElement.push(<FontAwesomeIcon key={i} icon="star" />);
        }
        if(rating-starsElement.length >= 0.5){
            starsElement.push(<FontAwesomeIcon key={starsElement.length+1} icon="star-half" />);
        }
        return starsElement;
    }
    validLevel(){
        if (this.userDTO.level >= this.state.trip.required_level){
            return <FontAwesomeIcon className="checked" icon="check-circle"/>;
        }
        return <FontAwesomeIcon className="false" icon="minus-circle"/>;
    }
    renderAchievements(achievements, message="No achievements are required"){
        if (achievements.length == 0){
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
                sessionBlock = <div className="trip_price">Cannot be purchased</div>
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
                                    <Button className="submit" variant="primary" type="submit" onClick={(event) => this.validatePurchase(event)}> Purchase </Button>
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
                                        {this.renderAchievements(this.state.trip.required_achievements_certificate,"No certifications are required")}
                                    </ListGroup>
                                </Card.Body>
                            </Card>
                            <Card className="mb-3">
                                <Card.Body>
                                    <Card.Title>
                                        Required achievements
                                    </Card.Title>
                                    <ListGroup variant="flush">
                                        {this.renderAchievements(this.state.trip.required_achievements_special,"No achievements are required")}
                                    </ListGroup>
                                    <Card.Subtitle className="subtitle">Categorized:</Card.Subtitle>
                                    <ListGroup variant="flush">
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
                    <div className="popup_background hidden">
                        <div className="window radius trip_validation customScroll">
                            <span className="close" onClick={event => this.closeValidateWindow(event.target)}><FontAwesomeIcon icon="times"></FontAwesomeIcon></span>
                            <h5>Trip validation summary</h5>
                            <Row>
                                <Col className="alignLeft">
                                    <Card.Title>Trip name</Card.Title>
                                    <Card.Body>{this.state.trip.name}</Card.Body>
                                </Col>
                            </Row>
                            <Row>
                                <Col className="alignLeft">
                                    <Card.Title>Location</Card.Title>
                                    <Card.Body>{this.state.trip.location}</Card.Body>
                                </Col>
                                <Col>
                                    <Card.Title>Reward</Card.Title>
                                    <Card.Body>{this.state.trip.possible_xp_reward} XP</Card.Body>
                                </Col>
                            </Row>
                            <Row>
                                <Col xs={6} className="alignLeft">
                                    <Card.Title>Selected trip session</Card.Title>
                                    <Card.Body>{this.state.selectedSession.from_date+" - "+this.state.selectedSession.to_date}</Card.Body>
                                </Col>
                                <Col>
                                    <Card.Title>Price</Card.Title>
                                    <Card.Body>{this.state.selectedSession.price} Kč</Card.Body>
                                </Col>
                                <Col>
                                    <Card.Title>Deposit</Card.Title>
                                    <Card.Body>{this.state.trip.deposit} Kč</Card.Body>
                                </Col>
                            </Row>
                            <div className="achievements">
                            <h5 >Requirements</h5>
                            <Row>
                                <Col className="alignLeft">
                                    <Card.Title>Required certifications</Card.Title>
                                    <Card.Body className="flex">
                                        <AchievementListInline achievements={this.state.trip.required_achievements_certificate} userList={this.userDTO.certifications} message={"No certifications are required"}/>
                                    </Card.Body>
                                </Col>
                                <Col xs={4}>
                                    <Card.Title>Minimum level</Card.Title>
                                    <Card.Body><span style={{display:"inline-block",verticalAlign:"middle",marginRight:"10px",marginTop:"-5px"}}>{this.state.trip.required_level}</span>  {this.validLevel()}</Card.Body>
                                </Col>
                            </Row>
                            <Row>
                                
                            </Row>
                            <Row>
                                <Col className="alignLeft">
                                    <Card.Title>Required achievements</Card.Title>

                                    <Card.Body>
                                        <AchievementListInline achievements={this.state.trip.required_achievements_special} userList={this.userDTO.achievements_special} message={"No achievements are required"}/>
                                    </Card.Body>
                                    <Card.Body>
                                        <AchievementListInline achievements={this.state.trip.required_achievements_categorized} userList={this.userDTO.achievements_categorized} message={"No categorized achievements are required"}/>
                                    </Card.Body>
                                </Col>
                            </Row>
                            <form onSubmit={(event) => this.submitTrip(event,event.target)}>
                            <Row>
                                <Col className="alignLeft">
                                    <label id="checkboxAgreement" className="containerInput">I agree with GDPR conditions and Travel&amp;Work&copy; conditions
                                        <input type="checkbox"/>
                                        <span className="checkmark"></span>
                                        <div className="validate_error">You have to agreed with conditions!</div>
                                    </label>
                                    <div id="validationFalse">We are sorry, you don't meet conditions for purchase this trip.</div>
                                </Col>
                            </Row>
                            <Row>
                                <Col></Col>
                                <Col xs={6}>
                                    <Button id="confirmPurchase" className="submit" variant="primary" type="submit">
                                        Confirm purchase
                                    </Button>
                                </Col>
                                <Col></Col>
                            </Row>
                            </form>
                            </div>
                        </div>
                        </div>
                </Container>
            );
        }
    }
}

export default Detail;
