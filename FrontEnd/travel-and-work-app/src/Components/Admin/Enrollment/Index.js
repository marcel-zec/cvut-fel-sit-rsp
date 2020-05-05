import React from "react";
import { Table, Tooltip, OverlayTrigger } from "react-bootstrap";
import { Container } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Spinner from "react-bootstrap/Spinner";
import { Link } from "react-router-dom";
import ButtonInRow from "../../SmartGadgets/ButtonInRow";

class Index extends React.Component {
    state = {
        items: null,
        modal: {
            show: false,
            title: null,
            description: null,
            buttin: {
                title: "Close",
                onClick: null,
            },
        },
    };

    submitHandler = (event) => {
        event.preventDefault();
        console.log("submiting");
    };

    async componentDidMount() {
        const response = await fetch(`http://localhost:8080/enrollment/close`, {
            method: "GET",
            mode: "cors",
            credentials: "include",
            headers: {
                "Content-Type": "application/json",
            },
        });
        const data = await response.json();
        console.log(data);
        this.setState({ items: data });
    }

    render() {
        if (this.state.items === null) {
            return (
                <Container className="mt-5 p-5">
                    <Spinner animation="border" role="status">
                        <span className="sr-only">Loading...</span>
                    </Spinner>
                </Container>
            );
        } else {
            let tableRows = [];
            if (this.state.items.length > 0) {
                this.state.items.forEach((item) => {
                    tableRows.push(
                        <tr>
                            <td>{item.enrollmentDto.trip.name}</td>
                            <td>
                                {item.owner.firstName +
                                    " " +
                                    item.owner.lastName}
                            </td>
                            <td>{item.enrollmentDto.tripSession.to_date}</td>
                            <td>
                                <OverlayTrigger
                                    key="set-end"
                                    overlay={
                                        <Tooltip>
                                            Set reward and review.
                                        </Tooltip>
                                    }
                                >
                                    <Link
                                        className="p-3"
                                        to={"close/" + item.enrollmentDto.id}
                                    >
                                        <FontAwesomeIcon icon="cog" />
                                    </Link>
                                </OverlayTrigger>

                                <OverlayTrigger
                                    key="end"
                                    overlay={
                                        <Tooltip>
                                            End trip with full reward and 5
                                            stars review without notes.
                                        </Tooltip>
                                    }
                                >
                                    <Link className="p-3">
                                        <FontAwesomeIcon icon="check-circle" />
                                    </Link>
                                </OverlayTrigger>

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
                        link="/category/create"
                        side="right"
                        label="Add category"
                    />

                    <Table striped bordered hover>
                        <thead>
                            <tr>
                                <th>Trip</th>
                                <th>User</th>
                                <th>Date of end</th>
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
