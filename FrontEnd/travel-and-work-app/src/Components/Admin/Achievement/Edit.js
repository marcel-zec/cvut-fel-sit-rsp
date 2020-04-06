import React from "react";
import Form from "react-bootstrap/Form";
import { Col, Button, Row } from "react-bootstrap";
import { Container } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import icons from "../../../Files/icons.json";
import Spinner from "react-bootstrap/Spinner";

class Edit extends React.Component {
    state = {
        achievement: { name: null, description: null, icon: null },
        form: {
            isValid: false,
            element: [
                {
                    keyForUpdate: "icon",
                    idForUpdate: true,
                    touched: false,
                    valid: false,
                    validationRules: [],
                },
                {
                    keyForUpdate: "name",
                    idForUpdate: false,
                    touched: false,
                    valid: false,
                    validationRules: [],
                },
                {
                    keyForUpdate: "description",
                    idForUpdate: false,
                    touched: false,
                    valid: false,
                    validationRules: [],
                },
            ],
        },
    };

    async componentDidMount() {
        const response = await fetch(
            `http://localhost:8080/achievement/` + this.props.match.params.id
        );
        const data = await response.json();
        console.log(data);
        this.setState({ achievement: data });
    }

    /**
     * Update state from input.
     * @param {event} event
     * @param {Object} formState
     */
    inputUpdateHandler(event, formState) {
        const newState = { ...this.state };
        if (formState.idForUpdate)
            newState.achievement[formState.keyForUpdate] = event.target.id;
        else newState.achievement[formState.keyForUpdate] = event.target.value;
        this.setState(newState);
    }

    submitHandler = (event) => {
        event.preventDefault();

        fetch(
            "http://localhost:8080/achievement/" + this.props.match.params.id,
            {
                method: "PATCH",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(this.state.achievement),
            }
        ).then((response) => {
            if (response.ok) this.props.history.push("/achievement");
        });
    };

    render() {
        if (this.state.achievement === null) {
            return (
                <Container className="p-5">
                    <Spinner animation="border" role="status">
                        <span className="sr-only">Loading...</span>
                    </Spinner>
                </Container>
            );
        } else {
            let iconsToForm = [];

            icons.icons.forEach((element) => {
                iconsToForm.push(
                    <div className="m-5">
                        <Form.Check.Label>
                            <Form.Check
                                type="radio"
                                name="formHorizontalRadios"
                                id={element.icon}
                                checked={
                                    this.state.achievement.icon == element.icon
                                        ? "checked"
                                        : ""
                                }
                                onChange={(event) =>
                                    this.inputUpdateHandler(
                                        event,
                                        this.state.form.element[0]
                                    )
                                }
                            />
                            <FontAwesomeIcon
                                className={
                                    this.state.achievement.icon == element.icon
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
                        <h1>Edit achievement</h1>

                        <Form.Group as={Col} controlId="formGridName">
                            <Form.Label>Name of trip</Form.Label>
                            <Form.Control
                                value={this.state.achievement.name}
                                onChange={(event) =>
                                    this.inputUpdateHandler(
                                        event,
                                        this.state.form.element[1]
                                    )
                                }
                            />
                        </Form.Group>

                        <Form.Group controlId="exampleForm.ControlTextarea1">
                            <Form.Label>Description</Form.Label>
                            <Form.Control
                                as="textarea"
                                rows="5"
                                value={this.state.achievement.description}
                                onChange={(event) =>
                                    this.inputUpdateHandler(
                                        event,
                                        this.state.form.element[2]
                                    )
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
}

export default Edit;
