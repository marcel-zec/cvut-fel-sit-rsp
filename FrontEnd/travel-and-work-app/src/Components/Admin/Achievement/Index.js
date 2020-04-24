import React from "react";
import Form from "react-bootstrap/Form";
import { Col, Button, Row, Table } from "react-bootstrap";
import { Container } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Spinner from "react-bootstrap/Spinner";
import { Link } from "react-router-dom";
import ButtonInRow from "../../SmartGadgets/ButtonInRow";

class Index extends React.Component {
    state = {
        achievements: null,
    };

    async componentDidMount() {
        const response = await fetch(`http://localhost:8080/achievement`);
        const data = await response.json();
        console.log(data);
        this.setState({ achievements: data });
    }

    render() {
        if (this.state.achievements === null) {
            return (
                <Container className="mt-5 p-5">
                    <Spinner animation="border" role="status">
                        <span className="sr-only">Loading...</span>
                    </Spinner>
                </Container>
            );
        } else {
            let tableRows = [];
            if (this.state.achievements.length > 0) {
                this.state.achievements.forEach((element) => {
                    tableRows.push(
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

                    <Table striped bordered hover>
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Icon</th>
                                <th>Settings</th>
                            </tr>
                        </thead>
                        <tbody>{tableRows}</tbody>
                    </Table>
                </Container>
            );
        }
    }
}

export default Index;
