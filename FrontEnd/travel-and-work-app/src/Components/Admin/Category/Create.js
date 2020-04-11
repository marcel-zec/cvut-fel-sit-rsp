import React from "react";
import Form from "react-bootstrap/Form";
import { Col, Button, Row } from "react-bootstrap";
import { Container } from "react-bootstrap";
import ButtonInRow from "../../SmartGadgets/ButtonInRow";

class Create extends React.Component {
    state = {
        category: { name: null },
        form: {
            isValid: false,
            elements: {
                name: {
                    touched: false,
                    valid: false,
                    validationRules: {
                        required: true,
                        minLength: 3,
                        maxLength: 5,
                    },
                },
            },
        },
    };

    submitHandler = (event) => {
        event.preventDefault();
        console.log(this.state.category);
        this.validateForm();
        if (this.state.form.isValid) {
            fetch("http://localhost:8080/category", {
                method: "POST",
                mode: "cors",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(this.state.category),
            }).then((response) => {
                if (response.ok) this.props.history.push("/category");
                //TODO - osetrenie vynimiek
                else console.log("Error: somethhing goes wrong");
            });
        }
    };

    /**
     * Update state from input.
     * @param {event} event
     * @param {String} nameOfFormInput,
     */
    inputUpdateHandler = async (event, nameOfFormInput) => {
        const newState = { ...this.state.category };
        newState[nameOfFormInput] = event.target.value;
        await this.setState({ category: newState });
        if (this.state.form.elements[nameOfFormInput].touched) {
            this.validateForm();
        }
    };

    validateForm = async () => {
        console.log("in validation");
        const newState = { ...this.state.form };
        for (let inputName in this.state.form.elements) {
            const input = this.state.category[inputName];
            const rules = newState.elements[inputName].validationRules;
            newState.elements[inputName].touched = true;
            newState.elements[inputName].valid = true;
            newState.valid = true;
            if (rules.hasOwnProperty("required") && rules.required) {
                if (!input || input.trim() === "") {
                    newState.elements[inputName].valid = false;
                    newState.valid = false;
                    console.log("required check: INVALID");
                } else {
                    console.log("required check:  valid");
                }
            }
            if (rules.hasOwnProperty("minLength")) {
                if (!input || input.trim().length < rules.minLength) {
                    newState.elements[inputName].valid = false;
                    newState.valid = false;
                    console.log(
                        "min length (" + rules.minLength + ") check: INVALID"
                    );
                } else {
                    console.log(
                        "min length (" + rules.minLength + ") check: valid"
                    );
                }
            }
            if (rules.hasOwnProperty("maxLength")) {
                if (!input || input.trim().length > rules.maxLength) {
                    newState.elements[inputName].valid = false;
                    newState.valid = false;
                    console.log(
                        "max length (" + rules.maxLength + ") check: INVALID"
                    );
                } else {
                    console.log(
                        "max length (" + rules.maxLength + ") check: valid"
                    );
                }
            }
            newState.isValid = newState.elements[inputName].valid;
        }
        console.log("Form valid: " + newState.isValid);
        await this.setState({ form: newState });
        console.log("Form valid after setState: " + this.state.form.isValid);
    };

    /**
     * Return class name for form input after validation(touched is true).
     */
    validationClassName = (inputName) => {
        const elements = this.state.form.elements;
        if (elements[inputName].touched) {
            if (elements[inputName].valid) return "is-valid";
            return "is-invalid";
        } else {
            return "";
        }
    };

    /**
     * Set text of validation feedback by rules of input.
     */
    validationFeedback = (inputName) => {
        const rules = this.state.form.elements[inputName].validationRules;
        let text = "";
        if (rules.hasOwnProperty("required") && rules.required) {
            text += this.capitalizeFirstLetter(inputName) + " is required. ";
        }
        if (rules.hasOwnProperty("minLength")) {
            if (text.length > 0) {
                text += "Minimal length is " + rules.minLength + ". ";
            } else {
                text +=
                    "Minimal length of " +
                    this.capitalizeFirstLetter(inputName) +
                    " is " +
                    rules.minLength +
                    ". ";
            }
        }
        if (rules.hasOwnProperty("maxLength")) {
            if (text.length > 0) {
                text += "Maximal length is " + rules.maxLength + ". ";
            } else {
                text +=
                    "Maximal length of " +
                    this.capitalizeFirstLetter(inputName) +
                    " is " +
                    rules.maxLength +
                    ". ";
            }
        }
        return text;
    };

    capitalizeFirstLetter = (string) => {
        return string.charAt(0).toUpperCase() + string.slice(1);
    };

    render() {
        return (
            <Container>
                <ButtonInRow
                    variant="danger"
                    link="/category"
                    side="left"
                    label=""
                    back={true}
                />

                <Form className="mt-3 mb-5" onSubmit={this.submitHandler}>
                    <h1>Create category</h1>

                    <Form.Group as={Col} controlId="formGridName">
                        <Form.Label>Name of category</Form.Label>
                        <Form.Control
                            className={this.validationClassName("name")}
                            placeholder="Enter name"
                            onChange={(event) =>
                                this.inputUpdateHandler(event, "name")
                            }
                        />
                        <div class="invalid-feedback">
                            {this.validationFeedback("name")}
                        </div>
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
