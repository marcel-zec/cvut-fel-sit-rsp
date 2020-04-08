import React from "react";
import { Form, Col, Button } from "react-bootstrap";

export class SessionInput extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            index: this.props.session.index,
            date_from: this.props.session.date_from,
            date_to: this.props.session.date_to,
            price: this.props.session.price,
        };
    }

    inputUpdateHandler = (event, nameOfInput) => {
        const newState = { ...this.state };
        newState[nameOfInput] = event.target.value;
        this.setState(newState);
        console.log(this.state);
        this.props.onChangeMethod(this.state);
    };

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

                <Button
                    variant="danger"
                    onClick={() => this.props.forDeleteSession(this.state)}
                >
                    Danger
                </Button>
            </Form.Row>
        );
    }
}
export default SessionInput;
