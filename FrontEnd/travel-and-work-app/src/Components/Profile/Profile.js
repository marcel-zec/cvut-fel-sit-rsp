import React from "react";
import { Container, Row, Col } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Link } from "react-router-dom";
import ProgressBar from "react-bootstrap/ProgressBar";
import Image from "react-bootstrap/Image";

class Profile extends React.Component {
    render() {
        const flexRow = "d-flex justify-content-center";
        const marginTop = "mt-5";
        const flexRowWithMgBtn = flexRow;

        const now = 50;
        const progressInstance = (
            <ProgressBar now={now} label="Just 15 more XP to next level!" />
        );

        console.log("profile");

        return (
            <Container id="userProfileMain">
                <Row className={[flexRowWithMgBtn, marginTop]}>
                    <h3>Your travel Journal</h3>
                </Row>
                <Row className={flexRowWithMgBtn}>
                    <Col xs={4}>
                        <div className="userPhoto window radius">
                            <Image src="https://blog.pravda.sk/avatar/blog-1166-256.png" className="radius"/>
                        </div>
                        <div className="userLevel">
                            <h4>2. level</h4>
                        </div>
                    </Col>
                    <Col>
                        <div className="window radius">
                            <Row>
                                <Col className="user_info">
                                    <div>
                                        <label>Name</label>
                                        <h5>Mike Peterson</h5>
                                    </div>
                                    <div>
                                        <label>Nationality</label>
                                        <h5>Slovakia</h5>
                                    </div>
                                </Col>
                                <Col className="user_adress">
                                    <div>
                                        <label>Adress</label>
                                        <p>Svätoplukova 3232/10</p>
                                        <p>080 01 Prešov</p>
                                    </div>
                                    <div>
                                        <label>Registrate from</label>
                                        <p>24.4.2020</p>
                                    </div>
                                </Col>
                            </Row>
                            <h5>Your progress</h5>
                            <Row>
                                <Col xs={2}><h6>2.Level</h6></Col>
                                <Col><div className="progressInstance">{progressInstance}</div></Col>
                                <Col xs={2}><h6>3.Level</h6></Col>
                            </Row>
                        
                        </div>
                        
                    </Col>
                </Row>
                <Row className="userMenu">
                    <Col>
                        <Link to="/profile/details">
                            <FontAwesomeIcon icon="cog" size="3x" />
                            <h4>User settings</h4>
                        </Link>
                    </Col>
                    <Col>
                        <Link to="/profile/achievments">
                            <FontAwesomeIcon icon="trophy" size="3x" />
                            <h4>My achievements</h4>
                        </Link>
                    </Col>
                    <Col>
                        <Link to="/profile/reviews">
                            <FontAwesomeIcon icon="star" size="3x" />
                            <h4>My reviews</h4>
                        </Link>
                    </Col>
                    <Col>
                        <Link to="/profile/trips">
                            <FontAwesomeIcon icon="suitcase" size="3x" />
                            <h4>My trips</h4>
                        </Link>
                    </Col>
                </Row>
            </Container>
        );
    }
}

export default Profile;
