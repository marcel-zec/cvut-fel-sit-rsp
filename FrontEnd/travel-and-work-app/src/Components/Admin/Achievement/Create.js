import React from "react";
import Form from "react-bootstrap/Form";
import { Col, Button, Row } from "react-bootstrap";
import { Container } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import icons from "../../../Files/icons.json";
import ButtonInRow from "../../SmartGadgets/ButtonInRow";
import rules from "../../../Files/validationRules.json";
import { withRouter } from "react-router-dom";
import {
    formValidation,
    validationFeedback,
    validationClassName,
} from "../../../Validator";

class Create extends React.Component {
    state = {
        achievement: { name: null, description: null, icon: null },
        form: {
            isValid: false,
            elements: {
                icon: {
                    idForUpdate: true,
                    touched: false,
                    valid: false,
                    validationRules: rules.achievement.icon,
                },
                name: {
                    touched: false,
                    valid: false,
                    validationRules: rules.achievement.name,
                },
                description: {
                    touched: false,
                    valid: false,
                    validationRules: rules.achievement.description,
                },
            },
        },
    };

    /**
     * Update state from input.
     * @param {event} event
     * @param {String} nameOfFormInput,
     */
    inputUpdateHandler = async (event, nameOfFormInput) => {
        const newState = { ...this.state.achievement };
        if (this.state.form.elements[nameOfFormInput].idForUpdate)
            newState[nameOfFormInput] = event.target.id;
        else newState[nameOfFormInput] = event.target.value;
        await this.setState({ achievement: newState });
        if (this.state.form.elements[nameOfFormInput].touched) {
            this.validateForm();
        }
        console.log(this.state.achievement);
    };

    submitHandler = async (event) => {
        event.preventDefault();
        console.log(this.state.achievement);
        await this.validateForm();
        if (this.state.form.isValid) {
            fetch("http://localhost:8080/achievement", {
                method: "POST",
                mode: "cors",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(this.state.achievement),
            }).then((response) => {
                if (response.ok) this.props.history.push("/achievement");
                //TODO - osetrenie vynimiek
                else console.log("Error: somethhing goes wrong");
            });
        }
    };

    validateForm = async () => {
        const newState = { ...this.state.form };
        formValidation(newState, this.state.achievement);
        await this.setState({ form: newState });
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
                <ButtonInRow
                    variant="danger"
                    link="/achievement"
                    side="left"
                    label=""
                    back={true}
                />

                <Form className="mt-3 mb-5" onSubmit={this.submitHandler}>
                    <h1>Create achievement</h1>

                    <Form.Group as={Col} controlId="formGridName">
                        <Form.Label>Name of trip</Form.Label>
                        <Form.Control
                            className={validationClassName(
                                "name",
                                this.state.form
                            )}
                            placeholder="Enter name"
                            onChange={(event) =>
                                this.inputUpdateHandler(event, "name")
                            }
                        />
                        <div class="invalid-feedback">
                            {validationFeedback("name", this.state.form)}
                        </div>
                    </Form.Group>

                    <Form.Group controlId="exampleForm.ControlTextarea1">
                        <Form.Label>Description</Form.Label>
                        <Form.Control
                            as="textarea"
                            rows="5"
                            className={validationClassName(
                                "description",
                                this.state.form
                            )}
                            onChange={(event) =>
                                this.inputUpdateHandler(event, "description")
                            }
                        />
                        <div class="invalid-feedback">
                            {validationFeedback("description", this.state.form)}
                        </div>
                    </Form.Group>

                    <Form.Group
                        className={
                            "d-flex flex-row flex-wrap " +
                            validationClassName("icon", this.state.form)
                        }
                    >
                        {iconsToForm}
                    </Form.Group>
                    <div class="invalid-feedback">
                        {validationFeedback("icon", this.state.form)}
                    </div>

                    <Button variant="primary" type="submit">
                        Submit
                    </Button>
                </Form>
            </Container>
        );
    }
}

export default withRouter(Create);
