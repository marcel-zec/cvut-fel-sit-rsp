import React from "react";
import Form from "react-bootstrap/Form";
import { Col, Button, Row } from "react-bootstrap";
import { Container } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import icons from "../../../Files/icons.json";
import ErrorMessage from "../../SmartGadgets/ErrorMessage";

class Create extends React.Component {
    /*
    const data = { username: 'example' };

    fetch('https://example.com/profile', {
        method: 'POST', // or 'PUT'
        headers: {
        'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
        })
        .then((response) => response.json())
        .then((data) => {
        console.log('Success:', data);
        })
        .catch((error) => {
        console.error('Error:', error);
    });
    */

    state = {
        name: null,
        description: null,
        icon: null,
    };

    submitHandler = (event) => {
        event.preventDefault();
        console.log(this.state.name);
        console.log(this.state.description);
        console.log(this.state.icon);

        fetch("http://localhost:8080/achievement", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(this.state),
        })
            .then((response) => {
                if (!response.ok) console.log("nene");
                else console.log("okik");
            })
            .then((response) => response.json())
            .then((data) => {
                console.log("Success:", data);
            })
            .catch((error) => {
                console.error("Error:", error);
            });
    };

    render() {
        let iconsToForm = [];
        icons.icons.forEach((element) => {
            iconsToForm.push(
                <div className="m-5">
                    <Form.Check.Label>
                        <Form.Check
                            type="radio"
                            name="formHorizontalRadios"
                            id={element.icon}
                            onChange={(event) =>
                                this.setState({ icon: event.target.id })
                            }
                        />
                        <FontAwesomeIcon
                            className={
                                this.state.icon == element.icon
                                    ? "choosen-icon"
                                    : ""
                            }
                            icon={element.icon}
                            size="3x"
                        />
                    </Form.Check.Label>
                </div>
            );
        });

        return (
            <Container>
                <Form className="mt-3 mb-5" onSubmit={this.submitHandler}>
                    <h1>Create achievement</h1>

                    <Form.Group as={Col} controlId="formGridName">
                        <Form.Label>Name of trip</Form.Label>
                        <Form.Control
                            placeholder="Enter name"
                            onChange={(event) =>
                                this.setState({ name: event.target.value })
                            }
                        />
                    </Form.Group>

                    <Form.Group controlId="exampleForm.ControlTextarea1">
                        <Form.Label>Description</Form.Label>
                        <Form.Control
                            as="textarea"
                            rows="5"
                            onChange={(event) =>
                                this.setState({
                                    description: event.target.value,
                                })
                            }
                        />
                    </Form.Group>

                    <Form.Group className="d-flex flex-row flex-wrap">
                        {iconsToForm}
                    </Form.Group>

                    <Button variant="primary" type="submit">
                        Submit
                    </Button>
                </Form>
            </Container>
        );
    }
}

export default Create;
