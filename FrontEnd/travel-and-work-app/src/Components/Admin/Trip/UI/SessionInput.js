import React from "react";
import { Form, Col, Button } from "react-bootstrap";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

class SessionInput extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            index: this.props.session.index,
            from_date: Date.parse(this.props.session.from_date),
            to_date: Date.parse(this.props.session.to_date),
            price: this.props.session.price,
        };
        console.log(this.props.session.from_date);
    }

    inputUpdateHandler = async (event, nameOfInput) => {
        const newState = { ...this.state };
        newState[nameOfInput] = event.target.value;
        console.log([nameOfInput] + ":" + event.target.value);
        await this.setState(newState);
        console.log(this.state);
        this.props.onChangeMethod(this.state);
    };

    dateChangeHandler = (date, stateName) => {
        let newDate = new Date(date);
        newDate.setTime(
            newDate.getTime() - new Date().getTimezoneOffset() * 60 * 1000
        );
        this.setState({
            [stateName]: newDate,
        });
    };

    render() {
        return (
            <Form.Row key={this.state.index}>
                <Form.Group
                    as={Col}
                    controlId="formGridName"
                    className="d-flex flex-column"
                >
                    <Form.Label>Date from</Form.Label>
                    <DatePicker
                        className="form-control"
                        selected={this.state.from_date}
                        dateFormat="dd-MM-yyyy"
                        onChange={(date) =>
                            this.dateChangeHandler(date, "from_date")
                        }
                    />
                </Form.Group>

                <Form.Group
                    as={Col}
                    controlId="formGridShortName"
                    className="d-flex flex-column"
                >
                    <Form.Label>Date to</Form.Label>
                    <DatePicker
                        className="form-control"
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
                <Form.Group className="d-flex flex-column justify-content-end mb-4">
                    <Button
                        variant="danger"
                        size="sm"
                        onClick={() => this.props.forDeleteSession(this.state)}
                    >
                        <FontAwesomeIcon
                            icon="times"
                            size="lg"
                            onClick={() =>
                                this.props.forDeleteSession(this.state)
                            }
                        />
                    </Button>
                </Form.Group>
            </Form.Row>
        );
    }
}
export default SessionInput;
