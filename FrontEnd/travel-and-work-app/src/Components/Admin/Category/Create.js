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
        const newState = { ...this.state.category };
        newState[nameOfFormInput] = event.target.value;
        this.setState({ category: newState });
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
        console.log(this.state.category);
        //this.validateForm(this.state.achievement);

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
                            placeholder="Enter name"
                            onChange={(event) =>
                                this.inputUpdateHandler(event, "name")
                            }
                        />
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
