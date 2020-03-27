import React from "react";
import Card from "react-bootstrap/Card";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faStar as fullStar,
    faStarHalf
} from "@fortawesome/free-solid-svg-icons";
import { faStar as emptyStar } from "@fortawesome/free-regular-svg-icons";
import { Button, Row, Col } from "react-bootstrap";
import { Link } from "react-router-dom";

class Trip extends React.Component {
    render() {
        const reviewStars = [];
        for (let i = 0; i < 5; i++) {
            console.log(i - this.props.trip.stars);
            if (i + 1 <= this.props.trip.stars) {
                reviewStars.push(<FontAwesomeIcon icon={fullStar} />);
            } else if (
                i - this.props.trip.stars < 0 &&
                i - this.props.trip.stars > -1
            ) {
                reviewStars.push(<FontAwesomeIcon icon={faStarHalf} />);
            } else {
                reviewStars.push(<FontAwesomeIcon icon={emptyStar} />);
            }
        }

        return (
            <Link>
                <Card className="p-3">
                    <Card.Img
                        variant="top"
                        src="https://images.unsplash.com/profile-1446404465118-3a53b909cc82?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=27a346c2362207494baa7b76f5d606e5"
                    />
                    <Card.ImgOverlay className="d-flex flex-column justify-content-start align-items-start">
                        <Card.Header>{this.props.trip.xp} xp</Card.Header>
                        <Card.Title className="ml-3">
                            {this.props.trip.name}
                        </Card.Title>
                    </Card.ImgOverlay>
                    <Card.Body>
                        <Row>
                            <Col className="d-flex flex-column align-items-center">
                                <Row>
                                    {this.props.trip.dateFrom}{" "}
                                    {this.props.trip.dateTo}
                                </Row>
                                <Row>{reviewStars}</Row>
                            </Col>
                            <Col className="d-flex flex-column justify-content-center">
                                {this.props.trip.price}
                            </Col>
                        </Row>
                    </Card.Body>
                </Card>
            </Link>
        );
    }
}

export default Trip;
