import React from "react";
import Form from "react-bootstrap/Form";
import { Col, Button, Row } from "react-bootstrap";
import { Container } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import icons from "../../../Files/icons.json";

class Create extends React.Component {
    state = {
        achievement: { name: null, description: null, icon: null },
        form: {
            isValid: false,
            elements: {
                icon: {
                    keyForUpdate: "icon",
                    idForUpdate: true,
                    touched: false,
                    valid: false,
                    validationRules: [],
                },
                name: {
                    keyForUpdate: "name",
                    idForUpdate: false,
                    touched: false,
                    valid: false,
                    validationRules: [],
                },
                description: {
                    keyForUpdate: "description",
                    idForUpdate: false,
                    touched: false,
                    valid: false,
                    validationRules: [],
                },
            },
        },
    };

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
    /*
    validateForm(inputs = {}) {
        console.log("in valdiation");
        const formInputs = Object.keys(inputs);
        formInputs.forEach((el, index) => {
            let isValid = true;
            let rules = this.state.form.element[index].validationRules;
            console.log(rules);
            if (rules.hasOwnProperty("required")) {
                if (rules.required) {
                    this.state.form.element[index].touched = true;
                    console.log("bfr " + this.state.form.element[index].valid);
                    this.state.form.element[index].valid = el.trim() !== "";
                    console.log("aft " + this.state.form.element[index].valid);
                }
            }
        });
    }
    */

    submitHandler = (event) => {
        event.preventDefault();
        console.log(this.state.achievement);
        //this.validateForm(this.state.achievement);

        fetch("http://localhost:8080/achievement", {
            method: "POST",
            mode: "cors",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(this.state.achievement),
        }).then((response) => {
            if (response.ok) this.props.history.push("/achievement");
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
                <Form className="mt-3 mb-5" onSubmit={this.submitHandler}>
                    <h1>Create achievement</h1>

                    <Form.Group as={Col} controlId="formGridName">
                        <Form.Label>Name of trip</Form.Label>
                        <Form.Control
                            placeholder="Enter name"
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
                            onChange={(event) =>
                                this.inputUpdateHandler(event, "description")
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
