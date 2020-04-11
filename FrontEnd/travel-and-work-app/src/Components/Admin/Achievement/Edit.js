import React from "react";
import Form from "react-bootstrap/Form";
import { Col, Button, Row } from "react-bootstrap";
import { Container } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import icons from "../../../Files/icons.json";
import Spinner from "react-bootstrap/Spinner";
import ButtonInRow from "../../SmartGadgets/ButtonInRow";

class Edit extends React.Component {
    state = {
        achievement: { name: null, description: null, icon: null },
        form: {
            isValid: false,
            elements: {
                icon: {
                    idForUpdate: true,
                    touched: false,
                    valid: false,
                    validationRules: [],
                },
                name: {
                    idForUpdate: false,
                    touched: false,
                    valid: false,
                    validationRules: [],
                },
                description: {
                    idForUpdate: false,
                    touched: false,
                    valid: false,
                    validationRules: [],
                },
            },
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
     * @param {String} nameOfFormInput,
     */
    inputUpdateHandler(event, nameOfFormInput) {
        const newState = { ...this.state.achievement };
        if (this.state.form.elements[nameOfFormInput].idForUpdate)
            newState[nameOfFormInput] = event.target.id;
        else newState[nameOfFormInput] = event.target.value;
        this.setState({ achievement: newState });
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
                <Container className="mt-5 p-5">
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
                                    this.inputUpdateHandler(event, "icon")
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
                    <ButtonInRow
                        variant="danger"
                        link="/achievement"
                        side="left"
                        label=""
                        back={true}
                    />

                    <Form className="mt-3 mb-5" onSubmit={this.submitHandler}>
                        <h1>Edit achievement</h1>

                        <Form.Group as={Col} controlId="formGridName">
                            <Form.Label>Name of trip</Form.Label>
                            <Form.Control
                                value={this.state.achievement.name}
                                onChange={(event) =>
                                    this.inputUpdateHandler(event, "name")
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
                                        "description"
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
