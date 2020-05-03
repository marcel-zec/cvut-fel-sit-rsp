import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Card, Button, Col, Row, Image } from "react-bootstrap";


class ActiveTrips extends React.Component {
    constructor(props){
        super();
        this.state = {
            depositWasPaided:props.trip.depositWasPaided,
            deposit: props.trip.deposit,
            isActive:true
        };
        this.paided = null;
        this.notPaided = null;
        this.buttonToPay = null;
        this.shortName = props.trip.shortName;
       
    }
    render() {
        console.log(this.state);
        this.paided = <span style={{color:"#28a745"}}>Paided <FontAwesomeIcon icon="check-circle"/></span>
        this.buttonToPay = <Button className="submit" onClick={price => this.props.funcToPay(this.props.trip.deposit,this)}>Pay deposit <FontAwesomeIcon icon={"money-bill-alt"}/></Button>;
        this.notPaided = <span style={{color:"#ce3131"}}>Not paided <FontAwesomeIcon icon="minus-circle"/></span>
        this.actionElement = <Button className="cancel" onClick={event => this.props.funcToCancel(this)} style={{float:"right"}}>Cancel<FontAwesomeIcon icon={"trash-alt"}/></Button>;
        return (
            <Card className="mb-3 userTrip window radius">
                <Card.Body className="d-flex flex-row">
                    <Col xs={2}>
                        <div className="tripImage">
                            <Image src="https://specials-images.forbesimg.com/imageserve/5db15891616a45000704761f/960x0.jpg?fit=scale" rounded/>
                        </div>
                    </Col>
                    <Col xs={4}>
                        <Card.Title className="mb-2 text-muted">
                            Name
                        </Card.Title>
                        <Card.Text>{this.props.trip.name}</Card.Text>

                        <Card.Title className="mb-2 text-muted">
                            Location
                        </Card.Title>
                        <Card.Text>{this.props.trip.location}</Card.Text>
                    </Col>
                    <Col>
                        <Card.Title className="mb-2 text-muted">
                            Trip session
                        </Card.Title>
                        <Card.Text>{this.props.trip.tripDate}</Card.Text>

                        <Card.Title className="mb-2 text-muted">
                            Deposit
                        </Card.Title>
                        <Card.Text>{this.state.deposit},- {(this.state.depositWasPaided) ? this.paided : this.notPaided}</Card.Text>
                    </Col>
                    <Col>
                        <Card.Title className="mb-2 text-muted">
                            Added to travel journal on
                        </Card.Title>
                        <Card.Text>{this.props.trip.enrollmentDate}</Card.Text>
                        <Card.Title className="mb-2 text-muted">
                            Action
                        </Card.Title>
                        <Card.Text>

                            { (!this.state.isActive) ? "Trip was canceled" : ((!this.state.depositWasPaided) ? this.buttonToPay: '')}
                            { (!this.state.isActive) ? "" : this.actionElement}
                        </Card.Text>
                    </Col>
                </Card.Body>
            </Card>
        );
    }
}

export default ActiveTrips;
