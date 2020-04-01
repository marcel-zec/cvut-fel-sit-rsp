import React from "react";
import Form from "react-bootstrap/Form";
import { Col, Button, Row } from "react-bootstrap";
import { Container } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import icons from "../../../Files/icons.json";

class Create extends React.Component {
    render() {
        let iconsToForm = [];
        icons.icons.forEach(element => {
            iconsToForm.push(
                <div className="m-5">
                    <Form.Check.Label>
                        <Form.Check type="radio" name="formHorizontalRadios" />
                        <FontAwesomeIcon icon={element.icon} size="3x" />
                    </Form.Check.Label>
                </div>
            );
        });

        return (
            <Container>
                <Form className="mt-3 mb-5">
                    <h1>Create achievement</h1>

                    <Form.Group as={Col} controlId="formGridName">
                        <Form.Label>Name of trip</Form.Label>
                        <Form.Control placeholder="Enter name" />
                    </Form.Group>

                    <Form.Group controlId="exampleForm.ControlTextarea1">
                        <Form.Label>Description</Form.Label>
                        <Form.Control as="textarea" rows="5" />
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
