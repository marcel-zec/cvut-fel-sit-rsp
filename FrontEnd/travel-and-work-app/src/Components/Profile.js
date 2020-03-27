import React from "react";
import { Container, Row, Col } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrophy, faCog, faSuitcase } from "@fortawesome/free-solid-svg-icons";
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
                animated
                label="Just 15 more XP to next level!"
            />
        );

        return (
            <Container>
                <Row className={[flexRowWithMgBtn, marginTop]}>
                    <h1>Travel Journal</h1>
                </Row>

                <Row className={flexRowWithMgBtn}>
                    <Col>
                        <Image
                            src="https://images.unsplash.com/profile-1446404465118-3a53b909cc82?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=27a346c2362207494baa7b76f5d606e5"
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
                        <Link>
                            <FontAwesomeIcon icon={faCog} size="4x" />
                            <h4>Profile</h4>
                        </Link>
                    </Col>
                    <Col>
                        <Link>
                            <FontAwesomeIcon icon={faTrophy} size="4x" />
                            <h4>My acheievments</h4>
                        </Link>
                    </Col>
                    <Col>
                        <Link>
                            <FontAwesomeIcon icon={faSuitcase} size="4x" />
                            <h4>My trips</h4>
                        </Link>
                    </Col>
                </Row>
            </Container>
        );
    }
}

export default Profile;
