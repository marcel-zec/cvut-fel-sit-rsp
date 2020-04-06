import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Card, Button, Col, Row, Image } from "react-bootstrap";
import { Link } from "react-router-dom";
import OverlayTrigger from "react-bootstrap/OverlayTrigger";
import Tooltip from "react-bootstrap/Tooltip";
import AchievmentModal from "../../SmartGadgets/AchievementModal";

class TripHistory extends React.Component {
    render() {
        const achievments = [];

        this.props.trip.achievments.forEach(element => {
            //faking icons
            let iconName = "trophy";
            if (element.id === 2) iconName = "medal";
            if (element.id === 3) iconName = "swimmer";
            if (element.id === 4) iconName = "award";

            achievments.push(
                <AchievmentModal
                    icon={iconName}
                    title={element.title}
                    description={element.description}
                />
            );
        });

        console.log(this.state);

        //incrementing number for tooltips ID
        let placement = 0;
        let commentButton = null;
        if (this.props.trip.comment) {
            //comment icons for new comment
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
                        <FontAwesomeIcon icon="comment-medical" size="2x" />
                    </Link>
                </OverlayTrigger>
            );
        } else {
            //comment icons for updating
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
                        <FontAwesomeIcon icon="comment-dots" size="2x" />
                    </Link>
                </OverlayTrigger>
            );
        }

        return (
            <Card className="mb-3">
                <Card.Body className="d-flex flex-row">
                    <Col>
                        <Image
                            src="https://blog.pravda.sk/avatar/blog-1166-256.png"
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
