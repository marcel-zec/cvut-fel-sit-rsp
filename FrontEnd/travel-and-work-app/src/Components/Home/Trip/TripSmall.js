import React from "react";
import Card from "react-bootstrap/Card";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Button, Row, Col } from "react-bootstrap";
import { Link } from "react-router-dom";

class TripSmall extends React.Component {
    render() {
        const reviewStars = [];
        for (let i = 0; i < 5; i++) {
            if (i + 1 <= this.props.trip.rating) {
                reviewStars.push(<FontAwesomeIcon icon="star" />);
            } else if (
                i - this.props.trip.rating < 0 &&
                i - this.props.trip.rating > -1
            ) {
                reviewStars.push(<FontAwesomeIcon icon="star-half" />);
            } else {
                reviewStars.push(<FontAwesomeIcon icon={["far", "star"]} />);
            }
        }

        let numberOfDates = 0;
        this.props.trip.sessions.forEach(() => {
            numberOfDates++;
        });
        let dates = null;
        if (numberOfDates == 1) {
            const session = this.props.trip.sessions[0];
            dates = session.from_date + " " + session.to_date;
        } else {
            dates = numberOfDates + " dates";
        }
        let sessions = this.props.trip.sessions;
        let lowestPrice = sessions[0].price;

        sessions.forEach(session => {
            if (session.price < lowestPrice) {
                lowestPrice = session.price;
            }
        });

        return (
            <Link to={"/trips/" + this.props.trip.short_name}>
                <Card className="p-3">
                    <div className="image-card">
                        <Card.Img
                            variant="top"
                            src="https://www.transparency.cz/wp-content/uploads/Jablonec-nad-Nisou-621x466.jpg"
                        />
                    </div>

                    <Card.ImgOverlay className="d-flex flex-column justify-content-start align-items-start">
                        <span className="image-text">
                            {" "}
                            {this.props.trip.possible_xp_reward} xp{" "}
                        </span>
                        <Card.Title className="ml-3" id="trip-title">
                            <p>
                                {" "}
                                <span> {this.props.trip.name} </span>
                            </p>
                        </Card.Title>
                    </Card.ImgOverlay>
                    <Card.Body>
                        <Row>
                            <Col className="d-flex flex-column align-items-center">
                                <Row>{dates}</Row>
                                <Row>{reviewStars}</Row>
                            </Col>
                            <Col className="d-flex flex-column justify-content-center text">
                                {lowestPrice} Kč
                            </Col>
                        </Row>
                    </Card.Body>
                </Card>
            </Link>
        );
    }
}

export default TripSmall;
