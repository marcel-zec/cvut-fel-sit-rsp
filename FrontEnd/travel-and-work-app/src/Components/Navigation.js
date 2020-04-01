import React from "react";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import NavDropdown from "react-bootstrap/NavDropdown";
import Form from "react-bootstrap/Form";
import FormControl from "react-bootstrap/FormControl";
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";
import { Row, Col } from "react-bootstrap";
import { NavLink } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

class Navigation extends React.Component {
    render() {
        return (
            <Container className="navigation">
                <Navbar bg="light" expand="lg">
                    <Col>
                        <Navbar.Brand>
                            <NavLink to="/">Travel&Work</NavLink>
                        </Navbar.Brand>
                    </Col>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Col xs={9}>
                            <Form inline>
                                <FormControl
                                    type="text"
                                    placeholder="Search"
                                    className="mr-sm-2"
                                />
                                <Button variant="outline-success">
                                    Search
                                </Button>
                            </Form>
                        </Col>
                        <Col>
                            <Nav className="mr-auto">
                                <Col>
                                    <Nav.Link>
                                        <NavLink to="/profile">Home</NavLink>
                                    </Nav.Link>
                                </Col>
                                <Col>
                                    <NavDropdown
                                        title=""
                                        id="basic-nav-dropdown"
                                    >
                                        <NavDropdown.Item>XP</NavDropdown.Item>
                                        <NavDropdown.Divider />
                                        <NavDropdown.Item>
                                            <NavLink to="/profile/achievments">
                                                My achievments{" "}
                                                <FontAwesomeIcon icon="trophy" />
                                            </NavLink>
                                        </NavDropdown.Item>
                                        <NavDropdown.Item>
                                            <NavLink to="/profile/trips">
                                                My trips
                                                <FontAwesomeIcon icon="suitcase" />
                                            </NavLink>
                                        </NavDropdown.Item>
                                        <NavDropdown.Item>
                                            <NavLink to="/profile/details">
                                                Settings
                                                <FontAwesomeIcon icon="cog" />
                                            </NavLink>
                                        </NavDropdown.Item>
                                        <NavDropdown.Item>
                                            <NavLink to="/trip/create">
                                                Create trip
                                            </NavLink>
                                        </NavDropdown.Item>
                                        <NavDropdown.Item>
                                            <NavLink to="/trip">
                                                List of trips
                                            </NavLink>
                                        </NavDropdown.Item>
                                        <NavDropdown.Item>
                                            <NavLink to="/achievement">
                                                List of achievements
                                            </NavLink>
                                        </NavDropdown.Item>
                                        <NavDropdown.Item>
                                            <NavLink to="/achievement/create">
                                                Create achievement
                                            </NavLink>
                                        </NavDropdown.Item>
                                        <NavDropdown.Item>
                                            <NavLink to="/">
                                                Log out
                                                <FontAwesomeIcon icon="power-off" />
                                            </NavLink>
                                        </NavDropdown.Item>
                                    </NavDropdown>
                                </Col>
                            </Nav>
                        </Col>
                    </Navbar.Collapse>
                </Navbar>
                <Row></Row>
            </Container>
        );
    }
}

export default Navigation;
