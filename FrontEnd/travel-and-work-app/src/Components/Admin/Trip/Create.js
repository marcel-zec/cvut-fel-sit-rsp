import React from "react";
import Form from "react-bootstrap/Form";
import { Col, Button } from "react-bootstrap";
import { Container } from "react-bootstrap";

class ProfileDetails extends React.Component {
    render() {
        return (
            <Container>
                <Form className="mt-5">
                    <Form.Row>
                        <Form.Group as={Col} controlId="formGridName">
                            <Form.Label>Name of trip</Form.Label>
                            <Form.Control placeholder="Enter name" />
                        </Form.Group>

                        <Form.Group as={Col} controlId="formGridShortName">
                            <Form.Label>Identificatation name</Form.Label>
                            <Form.Control placeholder="Enter unique key for trip" />
                        </Form.Group>
                    </Form.Row>

                    <Form.Row>
                        <Form.Group as={Col} controlId="formGridDeposit">
                            <Form.Label>Deposit</Form.Label>
                            <Form.Control placeholder="Enter deposite price" />
                        </Form.Group>

                        <Form.Group as={Col} controlId="formGridExperience">
                            <Form.Label>Required level</Form.Label>
                            <Form.Control placeholder="Enter minimum reqiured level" />
                        </Form.Group>
                    </Form.Row>

                    <Form.Group controlId="formGridLocation">
                        <Form.Label>Location</Form.Label>
                        <Form.Control placeholder="Hotel Super, London Street 12, Manchester, England" />
                    </Form.Group>

                    <Form.Group controlId="exampleForm.ControlTextarea1">
                        <Form.Label>Description</Form.Label>
                        <Form.Control as="textarea" rows="5" />
                    </Form.Group>

                    <Button variant="primary" type="submit">
                        Submit
                    </Button>
                </Form>
            </Container>
        );
    }
}

export default ProfileDetails;
