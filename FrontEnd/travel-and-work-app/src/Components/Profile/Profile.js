import React from "react";
import { Container, Row, Col } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Link } from "react-router-dom";
import ProgressBar from "react-bootstrap/ProgressBar";
import Image from "react-bootstrap/Image";

class Profile extends React.Component {
    render() {
        const flexRow = "d-flex justify-content-center";
        const marginBt = "mb-5";
        const marginTop = "mt-5";
        const flexRowWithMgBtn = flexRow + " " + marginBt;

        const now = 60;
        const progressInstance = (
            <ProgressBar
                now={now}
                label="Just 15 more XP to next level!"
            />
        );

        console.log("profile");

        return (
            <Container>
                <Row className={[flexRowWithMgBtn, marginTop]}>
                    <h1>Travel Journal</h1>
                </Row>

                <Row className={flexRowWithMgBtn}>
                    <Col>
                        <Image
                            src="https://blog.pravda.sk/avatar/blog-1166-256.png"
                            roundedCircle
                        />
                        <h4>2. level</h4>
                    </Col>
                    <Col>
                        <h2>The Work-Traveler</h2>
                        <h2>Mike Peterson</h2>
                    </Col>
                </Row>
                <Container fluid className={marginBt}>
                    <Row>
                        <Col>{progressInstance}</Col>
                    </Row>
                </Container>
                <Row className={flexRowWithMgBtn}>
                    <Col>
                        <Link to="/profile/details">
                            <FontAwesomeIcon icon="cog" size="4x" />
                            <h4>Profile</h4>
                        </Link>
                    </Col>
                    <Col>
                        <Link to="/profile/achievments">
                            <FontAwesomeIcon icon="trophy" size="4x" />
                            <h4>My acheievments</h4>
                        </Link>
                    </Col>
                    <Col>
                        <Link to="/profile/trips">
                            <FontAwesomeIcon icon="suitcase" size="4x" />
                            <h4>My trips</h4>
                        </Link>
                    </Col>
                </Row>
            </Container>
        );
    }
}

export default Profile;
