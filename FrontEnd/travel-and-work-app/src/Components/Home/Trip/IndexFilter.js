import React from "react";
import { Container, Card, Form } from "react-bootstrap";
import TripSmall from "./TripSmall";
import CardColumns from "react-bootstrap/CardColumns";
import Spinner from "react-bootstrap/Spinner";
import Cookies from "universal-cookie";
import DatePicker from "react-datepicker";
import RangeSlider from "react-bootstrap-range-slider";
import "react-bootstrap-range-slider/dist/react-bootstrap-range-slider.css";

class IndexFilter extends React.Component {
    state = {
        trips: null,
        filter: {
            from: null,
            to: null,
            price: null,
        },
    };

    componentDidUpdate() {
        console.log("aaaaaaaaaaaaaaaaaaaaaaaa");
        console.log("bbbbbbbbbbbbbb");
    }

    async componentDidMount() {
        console.log("ta so?");
        console.log(this.props.location.search);
        await fetch(
            `http://localhost:8080/trip/filter` +
                (this.props.location.search != null)
                ? this.props.location.search
                : "",
            {
                method: "GET",
                mode: "cors",
                credentials: "include",
                headers: {
                    "Content-Type": "application/json",
                },
            }
        )
            .then((response) => {
                console.log("response?");
                console.log(response);
                if (response.ok) return response.json();
                else console.error(response.status);
            })
            .then((data) => {
                console.log("response!");
                console.log(data);
                this.setState({ trips: data });
            })
            .catch((error) => {
                console.error(error);
            });

        console.log("ta nic");
    }

    render() {
        if (false) {
            return (
                <Container className="p-5">
                    <Spinner animation="border" role="status">
                        <span className="sr-only">Loading...</span>
                    </Spinner>
                </Container>
            );
        } else {
            return (
                <Container className="trips">
                    <Card>
                        <Form>
                            <Form.Group>
                                <Form.Label>Date</Form.Label>
                                <Card.Body className="d-flex">
                                    <Form.Label>from </Form.Label>
                                    <DatePicker
                                        className="form-control"
                                        dateFormat="dd. MM. yyyy"
                                        selected={this.state.filter.from}
                                    />
                                    <Form.Label>to </Form.Label>
                                    <DatePicker
                                        className="form-control ml-3"
                                        dateFormat="dd. MM. yyyy"
                                        selected={this.state.filter.to}
                                    />
                                </Card.Body>
                            </Form.Group>
                            <Form.Group>
                                <Card.Body className="d-flex">
                                    <Form.Label>Price </Form.Label>
                                    <RangeSlider
                                        value={this.state.filter.price}
                                        min={0}
                                        max={5000}
                                        step={200}
                                        tooltip="off"
                                    />
                                </Card.Body>
                            </Form.Group>
                        </Form>
                    </Card>
                    {this.props.location.search == null
                        ? null
                        : this.props.location.search}
                    {/*<CardColumns>
                        {this.state.trips.map((trip) => {
                            return (
                                <TripSmall key={trip.short_name} trip={trip} />
                            );
                        })}
                    </CardColumns>*/}
                </Container>
            );
        }
    }
}

export default IndexFilter;
