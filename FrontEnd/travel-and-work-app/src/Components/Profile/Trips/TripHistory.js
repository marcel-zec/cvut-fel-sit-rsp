import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Card, Button, Col, Row, Image } from "react-bootstrap";
import { Link } from "react-router-dom";
import OverlayTrigger from "react-bootstrap/OverlayTrigger";
import Tooltip from "react-bootstrap/Tooltip";
import AchievmentModal from "../../SmartGadgets/AchievementModal";

class TripHistory extends React.Component {
    state = {enroll:null,review_exists:null};
    async componentDidMount() {
        this.setState({enroll:this.props.trip});
        this.setState({review_exists:this.props.reviewExists})
    }
    render() {
        const achievments = [];

        this.props.trip.gain_achievements_special.forEach(element => {
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
                    key={element.title}
                />
            );
        });
        //incrementing number for tooltips ID
        let placement = 0;
        let commentButton = null;
        //pokud user jeste nenapsal review 
        if (this.state.review_exists) {
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
                    <Link className="submit" to="/">
                        Add review<FontAwesomeIcon icon="comment-medical" />
                    </Link>
                </OverlayTrigger>
            );
        } else {
            //comment icons for updating
            commentButton = "";
        }

        return (
            <Card className="mb-3 userTrip window radius">
                <Card.Body className="d-flex flex-row">
                    <Col xs={2}>
                        <div className="tripImage">
                            <Image src="https://www.transparency.cz/wp-content/uploads/Jablonec-nad-Nisou-621x466.jpg" rounded/>
                        </div>
                    </Col>
                    <Col xs={4}>
                        <Card.Title className="mb-2 text-muted">
                            Name
                        </Card.Title>
                        <Card.Text>{this.props.trip.name}</Card.Text>

                        <Card.Title className="mb-2 text-muted">
                            Location
                        </Card.Title>
                        <Card.Text>{this.props.trip.location}</Card.Text>
                    </Col>
                    <Col>
                        <Card.Title className="mb-2 text-muted">
                            Trip session
                        </Card.Title>
                        <Card.Text>{this.props.trip.tripDate}</Card.Text>

                        <Card.Title className="mb-2 text-muted">
                            Reward XP and achievements
                        </Card.Title>
                        <Card.Text>{this.props.trip.xp} XP - {achievments}</Card.Text>
                    </Col>
                    <Col>
                        <Card.Title className="mb-2 text-muted">
                            Added to travel journal on
                        </Card.Title>
                        <Card.Text>{this.state.enroll.enrollDate}</Card.Text>
                        <Card.Title className="mb-2 text-muted">
                            Action
                        </Card.Title>
                        <Card.Text>{commentButton}</Card.Text>
                    </Col>
                </Card.Body>
            </Card>
        );
    }
}

export default TripHistory;
