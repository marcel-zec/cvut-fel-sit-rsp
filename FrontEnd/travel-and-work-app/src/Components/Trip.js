import React from "react";
import Card from "react-bootstrap/Card";

import { Button } from "react-bootstrap";

class Trip extends React.Component {
    render() {
        return (
            <Card className="p-3">
                <Card.Header>{this.props.trip.xp}</Card.Header>
                <Card.Body>
                    <Card.Title>{this.props.trip.name}</Card.Title>
                    <Card.Img variant="top" src="xxx" />
                    <Card.Text>{this.props.trip.price}</Card.Text>
                    <Button variant="primary">Go somewhere</Button>
                </Card.Body>
                <Card.Footer className="text-muted">2 days ago</Card.Footer>
            </Card>
        );
    }
}

export default Trip;
