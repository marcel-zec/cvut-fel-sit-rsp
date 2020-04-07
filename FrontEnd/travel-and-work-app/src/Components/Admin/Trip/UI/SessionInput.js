import React from "react";
import { Form, Col } from "react-bootstrap";

class SessionInput extends React.Component {
    state = {
        date_to: null,
        date_from: null,
        price: null,
    };

    inputUpdateHandler(event, nameOfInput) {
        const newState = { ...this.state };
        newState[nameOfInput] = event.target.value;
        this.setState(newState);
        console.log(this.state);
    }

    checkForUpdate() {
        let full = true;
        for (let property in this.state) {
            if (this.state[property] == null) full = false;
        }
        console.log("update check: " + full);
        if (full) {
            this.props.onChangeMethod(this.state);
        }
    }

    render() {
        return (
            <Form.Row>
                <Form.Group as={Col} controlId="formGridName">
                    <Form.Label>Date from</Form.Label>
                    <Form.Control
                        placeholder=""
                        value={this.state.date_from}
                        onChange={(event) =>
                            this.inputUpdateHandler(event, "date_from")
                        }
                    />
                </Form.Group>

                <Form.Group as={Col} controlId="formGridShortName">
                    <Form.Label>Date to</Form.Label>
                    <Form.Control
                        placeholder=""
                        value={this.state.date_to}
                        onChange={(event) =>
                            this.inputUpdateHandler(event, "date_to")
                        }
                    />
                </Form.Group>

                <Form.Group as={Col} controlId="formGridShortName">
                    <Form.Label>Price</Form.Label>
                    <Form.Control
                        placeholder=""
                        value={this.state.price}
                        onChange={(event) =>
                            this.inputUpdateHandler(event, "price")
                        }
                    />
                </Form.Group>
            </Form.Row>
        );
    }
}
export default SessionInput;
