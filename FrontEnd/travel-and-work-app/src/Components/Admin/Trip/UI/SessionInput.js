import React from "react";
import { Form, Col, Button } from "react-bootstrap";
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";

class SessionInput extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            index: this.props.session.index,
            from_date: this.props.session.date_from,
            to_date: this.props.session.date_to,
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

    dateChangeHandler = (date, stateName) => {
        this.setState({
            [stateName]: date,
        });
    };

    render() {
        return (
            <Form.Row>
                <Form.Group as={Col} controlId="formGridName">
                    <Form.Label>Date from</Form.Label>
                    <DatePicker
                        selected={this.state.from_date}
                        dateFormat="dd-MM-yyyy"
                        onChange={(date) =>
                            this.dateChangeHandler(date, "from_date")
                        }
                    />
                </Form.Group>

                <Form.Group as={Col} controlId="formGridShortName">
                    <Form.Label>Date to</Form.Label>
                    <DatePicker
                        selected={this.state.to_date}
                        dateFormat="dd-MM-yyyy"
                        onChange={(date) =>
                            this.dateChangeHandler(date, "to_date")
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
