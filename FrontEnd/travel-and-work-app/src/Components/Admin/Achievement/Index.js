import React from "react";
import Form from "react-bootstrap/Form";
import { Col, Button, Row, Table, Tab, Tabs } from "react-bootstrap";
import { Container } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Spinner from "react-bootstrap/Spinner";
import { Link } from "react-router-dom";
import ButtonInRow from "../../SmartGadgets/ButtonInRow";

class Index extends React.Component {
    state = {
        categorized: null,
        certificate: null,
        special: null,
    };

    async componentDidMount() {
        const response = await fetch(
            `http://localhost:8080/achievement/categorized`
        );
        const data = await response.json();
        console.log(data);
        this.setState({ categorized: data });

        const response1 = await fetch(
            `http://localhost:8080/achievement/certificate`
        );
        const data1 = await response1.json();
        console.log(data1);
        this.setState({ certificate: data1 });

        const response2 = await fetch(
            `http://localhost:8080/achievement/special`
        );
        const data2 = await response2.json();
        console.log(data2);
        this.setState({ special: data2 });
    }

    render() {
        if (
            this.state.categorized === null ||
            this.state.certificate === null ||
            this.state.special === null
        ) {
            return (
                <Container className="mt-5 p-5">
                    <Spinner animation="border" role="status">
                        <span className="sr-only">Loading...</span>
                    </Spinner>
                </Container>
            );
        } else {
            let categorizedRows = [];
            if (this.state.categorized.length > 0) {
                this.state.categorized.forEach((element) => {
                    categorizedRows.push(
                        <tr>
                            <td>{element.name}</td>
                            <td>
                                <FontAwesomeIcon
                                    icon={element.icon}
                                    size="3x"
                                />
                            </td>
                            <td>{element.category.name}</td>
                            <td>{element.limit}</td>
                            <td>
                                <Link
                                    className="p-3"
                                    to={"achievement/" + element.id + "/edit"}
                                >
                                    <FontAwesomeIcon icon="cog" />
                                </Link>

                                <Link className="p-3">
                                    <FontAwesomeIcon icon="trash-alt" />
                                </Link>
                            </td>
                        </tr>
                    );
                });
            }

            let specialRows = [];
            if (this.state.special.length > 0) {
                this.state.special.forEach((element) => {
                    specialRows.push(
                        <tr>
                            <td>{element.name}</td>
                            <td>
                                <FontAwesomeIcon
                                    icon={element.icon}
                                    size="3x"
                                />
                            </td>
                            <td>
                                <Link
                                    className="p-3"
                                    to={"achievement/" + element.id + "/edit"}
                                >
                                    <FontAwesomeIcon icon="cog" />
                                </Link>

                                <Link className="p-3">
                                    <FontAwesomeIcon icon="trash-alt" />
                                </Link>
                            </td>
                        </tr>
                    );
                });
            }

            let certificateRows = [];
            if (this.state.certificate.length > 0) {
                this.state.certificate.forEach((element) => {
                    certificateRows.push(
                        <tr>
                            <td>{element.name}</td>
                            <td>
                                <FontAwesomeIcon
                                    icon={element.icon}
                                    size="3x"
                                />
                            </td>
                            <td>
                                <Link
                                    className="p-3"
                                    to={"achievement/" + element.id + "/edit"}
                                >
                                    <FontAwesomeIcon icon="cog" />
                                </Link>

                                <Link className="p-3">
                                    <FontAwesomeIcon icon="trash-alt" />
                                </Link>
                            </td>
                        </tr>
                    );
                });
            }

            return (
                <Container>
                    <ButtonInRow
                        variant="success"
                        link="/achievement/create"
                        side="right"
                        label="Add achievement"
                    />

                    <Tabs
                        defaultActiveKey="special"
                        id="uncontrolled-tab-example"
                    >
                        <Tab eventKey="special" title="Special">
                            <Table striped bordered hover>
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Icon</th>
                                        <th>Settings</th>
                                    </tr>
                                </thead>
                                <tbody>{specialRows}</tbody>
                            </Table>
                        </Tab>
                        <Tab eventKey="categories" title="Categories">
                            <Table striped bordered hover>
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Icon</th>
                                        <th>Category</th>
                                        <th>Limit level</th>
                                        <th>Settings</th>
                                    </tr>
                                </thead>
                                <tbody>{categorizedRows}</tbody>
                            </Table>
                        </Tab>
                        <Tab eventKey="certificate" title="Certificate">
                            <Table striped bordered hover>
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Icon</th>
                                        <th>Settings</th>
                                    </tr>
                                </thead>
                                <tbody>{certificateRows}</tbody>
                            </Table>
                        </Tab>
                    </Tabs>
                </Container>
            );
        }
    }
}

export default Index;
