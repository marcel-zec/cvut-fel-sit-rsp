import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Card, Button, Col, Row, Image } from "react-bootstrap";
import {
    faTrophy,
    faSwimmer,
    faMedal,
    faAward,
    faCommentMedical,
    faCommentDots
} from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import OverlayTrigger from "react-bootstrap/OverlayTrigger";
import Tooltip from "react-bootstrap/Tooltip";

class TripHistory extends React.Component {
    render() {
        const achievments = [];
        const achievmentClass = "ml-2 mr-2";
        this.props.trip.achievments.forEach(element => {
            if (element.id == 1)
                achievments.push(
                    <FontAwesomeIcon
                        className={achievmentClass}
                        icon={faTrophy}
                        size="2x"
                    />
                );
            if (element.id == 2)
                achievments.push(
                    <FontAwesomeIcon
                        className={achievmentClass}
                        icon={faAward}
                        size="2x"
                    />
                );
            if (element.id == 3)
                achievments.push(
                    <FontAwesomeIcon
                        className={achievmentClass}
                        icon={faMedal}
                        size="2x"
                    />
                );
            if (element.id == 4)
                achievments.push(
                    <FontAwesomeIcon icon={faSwimmer} size="2x" />
                );
        });

        let placement = 0;

        let commentButton = null;
        if (this.props.trip.comment) {
            commentButton = (
                <OverlayTrigger
                    placement="right"
                    overlay={
                        <Tooltip id={`tooltip-${++placement}`}>
                            Add review of this trip.
                        </Tooltip>
                    }
                >
                    <Link>
                        <FontAwesomeIcon icon={faCommentMedical} size="2x" />
                    </Link>
                </OverlayTrigger>
            );
        } else {
            commentButton = (
                <OverlayTrigger
                    placement="right"
                    overlay={
                        <Tooltip id={`tooltip-${++placement}`}>
                            Show or update your review of this trip.
                        </Tooltip>
                    }
                >
                    <Link>
                        <FontAwesomeIcon icon={faCommentDots} size="2x" />
                    </Link>
                </OverlayTrigger>
            );
        }
        return (
            <Card className="mb-3">
                <Card.Body className="d-flex flex-row">
                    <Col>
                        <Image
                            src="https://images.unsplash.com/profile-1446404465118-3a53b909cc82?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=27a346c2362207494baa7b76f5d606e5"
                            rounded
                        />
                    </Col>
                    <Col>
                        <Card.Title className="mb-2 text-muted">
                            Name
                        </Card.Title>
                        <Card.Text>{this.props.trip.name}</Card.Text>

                        <Card.Title className="mb-2 text-muted">
                            Date
                        </Card.Title>
                        <Card.Text>{this.props.trip.tripDate}</Card.Text>
                    </Col>
                    <Col>
                        <Card.Title className="mb-2 text-muted">
                            Recieved XP
                        </Card.Title>
                        <Card.Text>{this.props.trip.xp}</Card.Text>
                        <Card.Title className="mb-2 text-muted">
                            Achievments
                        </Card.Title>
                        <Card.Text>{achievments}</Card.Text>
                    </Col>
                    <Col
                        xs
                        lg="1"
                        className="d-flex flex-column justify-content-center"
                    >
                        {commentButton}
                    </Col>
                </Card.Body>
            </Card>
        );
    }
}

export default TripHistory;
