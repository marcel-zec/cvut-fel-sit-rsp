import React from "react";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import NavDropdown from "react-bootstrap/NavDropdown";
import Form from "react-bootstrap/Form";
import FormControl from "react-bootstrap/FormControl";
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";
import { Row, Col } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { NavLink } from "react-router-dom";
import {
    faTrophy,
    faPowerOff,
    faCog,
    faSuitcase
} from "@fortawesome/free-solid-svg-icons";

class Navigation extends React.Component {
    render() {
        return (
            <Container className="navigation">
                <Navbar bg="light" expand="lg">
                    <Col>
                        <Navbar.Brand href="#home">
                            React-Bootstrap
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
                                        <NavDropdown.Item href="#action/3.2">
                                            My achievments{" "}
                                            <FontAwesomeIcon icon={faTrophy} />
                                        </NavDropdown.Item>
                                        <NavDropdown.Item href="#action/3.2">
                                            My trips
                                            <FontAwesomeIcon
                                                icon={faSuitcase}
                                            />
                                        </NavDropdown.Item>
                                        <NavDropdown.Item href="#action/3.3">
                                            Settings
                                            <FontAwesomeIcon icon={faCog} />
                                        </NavDropdown.Item>
                                        <NavDropdown.Item href="#action/3.4">
                                            Log out
                                            <FontAwesomeIcon
                                                icon={faPowerOff}
                                            />
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
