import React from "react";
import { Form, Col } from "react-bootstrap";

function SessionInput(props) {
    const [dateFrom, setDateFrom] = React.useState(props.dateFrom);
    const [dateTo, setDateTo] = React.useState(props.dateTo);
    const [price, setPrice] = React.useState(props.price);

    return (
        <Form.Row>
            <Form.Group as={Col} controlId="formGridName">
                <Form.Label>Date from</Form.Label>
                <Form.Control placeholder="" value={dateFrom} />
            </Form.Group>

            <Form.Group as={Col} controlId="formGridShortName">
                <Form.Label>Date to</Form.Label>
                <Form.Control placeholder="" value={dateTo} />
            </Form.Group>

            <Form.Group as={Col} controlId="formGridShortName">
                <Form.Label>Price</Form.Label>
                <Form.Control placeholder="" value={price} />
            </Form.Group>
        </Form.Row>
    );
}
export default SessionInput;
