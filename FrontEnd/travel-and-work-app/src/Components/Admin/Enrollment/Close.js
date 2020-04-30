import React from "react";
import Form from "react-bootstrap/Form";
import {
    Col,
    Button,
    Row,
    Spinner,
    Card,
    InputGroup,
    ListGroup,
    ListGroupItem,
} from "react-bootstrap";
import { Container } from "react-bootstrap";
import { withRouter } from "react-router-dom";
import {
    formValidation,
    validationFeedback,
    validationClassName,
} from "../../../Validator";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import MyAlert from "../../SmartGadgets/MyAlert";
import DatePicker from "react-datepicker";

class Close extends React.Component {
    state = {
        form: {
            isValid: false,
            elements: {},
        },
        enrollment: {
            state: "ACTIVE",
            actual_xp_reward: 0,
            recieved_achievements_special: [],
        },
        user: {
            firstName: "Peter",
            lastName: "Horák",
        },
        trip_session: {
            from_date: Date.parse("2020-05-20"),
            to_date: Date.parse("2020-06-08"),
        },
        trip: {
            name: "Kucharcok",
            location: "Blok 6, Olympijska 5, Praha 6, Cesko",
            description:
                "Bdajsoijioj dsajoidja daisojioiojeao dasiojioda jiodaijdoas",
            possible_xp_reward: 15,
            gain_achievements_special: [
                { id: 3, name: "Kuchticek 1. triedy", icon: "water" },
                { id: 8, name: "Odvazlivec", icon: "trophy" },
            ],
        },
    };

    /**
     * Update state from input.
     * @param {event} event
     * @param {String} nameOfFormInput
     * @param {Boolean} arrayToPush - if want push to array
     * @param {Boolean} checkbox
     */
    inputUpdateHandler = async (event, nameOfFormInput) => {
        /*const stringProperties = [
            "name",
            "short_name",
            "deposit",
            "required_level",
            "possible_xp_reward",
            "location",
            "description",
        ];
        const checkboxProperties = [
            "required_achievements_special",
            "required_achievements_certificate",
            "required_achievements_categorized",
            "gain_achievements_special",
        ];
        const newState = { ...this.state.trip };

        //string inputs
        if (stringProperties.includes(nameOfFormInput)) {
            newState[nameOfFormInput] = event.target.value;
        } else if (checkboxProperties.includes(nameOfFormInput)) {
            //if already checked
            let foundIndex = newState[nameOfFormInput].findIndex((object) => {
                return object.id == event.target.value;
            });
            //remove if alredy checked
            if (foundIndex > -1) {
                newState[nameOfFormInput].splice(foundIndex, 1);
                console.log("state after splice");
                console.log(newState[nameOfFormInput]);
            } else {
                let objectIndex = -1;
                if (nameOfFormInput == "required_achievements_special") {
                    objectIndex = this.state.achievements_special.findIndex(
                        (object) => object.id == event.target.value
                    );
                    if (objectIndex > -1) {
                        newState[nameOfFormInput].push(
                            this.state.achievements_special[objectIndex]
                        );
                    }
                } else if (
                    nameOfFormInput == "required_achievements_certificate"
                ) {
                    objectIndex = this.state.achievements_certificate.findIndex(
                        (object) => object.id == event.target.value
                    );
                    if (objectIndex > -1) {
                        newState[nameOfFormInput].push(
                            this.state.achievements_certificate[objectIndex]
                        );
                    }
                } else if (
                    nameOfFormInput == "required_achievements_categorized"
                ) {
                    objectIndex = this.state.achievements_categorized.findIndex(
                        (object) => object.id == event.target.value
                    );
                    if (objectIndex > -1) {
                        newState[nameOfFormInput].push(
                            this.state.achievements_categorized[objectIndex]
                        );
                    }
                } else if (nameOfFormInput == "gain_achievements_special") {
                    objectIndex = this.state.achievements_special.findIndex(
                        (object) => object.id == event.target.value
                    );
                    if (objectIndex > -1) {
                        newState[nameOfFormInput].push(
                            this.state.achievements_special[objectIndex]
                        );
                    }
                }
            }
        } else if (nameOfFormInput == "category") {
            console.log(event.target.value);
            let foundIndex = this.state.categories.findIndex(
                (category) => category.name == event.target.value
            );
            if (foundIndex > -1) {
                newState.category = this.state.categories[foundIndex];
            }
        }
        await this.setState({ trip: newState });
        if (
            this.state.form.elements.hasOwnProperty(nameOfFormInput) &&
            this.state.form.elements[nameOfFormInput].touched
        ) {
            this.validateForm();
        }*/
    };

    validateForm = async () => {
        const newState = { ...this.state.form };
        // formValidation(newState, this.state.trip);
        await this.setState({ form: newState });
    };

    submitHandler = async (event) => {
        event.preventDefault();
        await this.validateForm();
        /*if (this.state.form.isValid) {
            
            fetch("http://localhost:8080/trip/" + this.props.match.params.id, {
                method: "PATCH",
                credentials: "include",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(this.state.trip),
            }).then((response) => {
                if (response.ok) this.props.history.push("/trip");
                //TODO - osetrenie vynimiek
                else console.log("Error: somethhing goes wrong");
            });
        }*/
    };

    async componentDidMount() {
        /*const response = await fetch(
            `http://localhost:8080/trip/` + this.props.match.params.id
        );
        const data = await response.json();
        console.log(data);
        this.setState({ trip: data });*/
    }

    render() {
        let achievementArray = [];
        if (this.state.trip.gain_achievements_special.length > 0) {
            this.state.trip.gain_achievements_special.forEach((item) => {
                achievementArray.push(
                    <Form.Check.Label>
                        <Form.Check.Input
                            type="checkbox"
                            defaultChecked={true}
                        />
                        <FontAwesomeIcon
                            icon={item.icon}
                            size="lg"
                            className="mr-3 ml-3"
                        />
                        {item.name}
                    </Form.Check.Label>
                );
            });
        }
        let achievements = (
            <div className="d-flex flex-column align-items-start">
                {achievementArray}
            </div>
        );

        if (false) {
            return (
                <Container className="p-5 mt-5">
                    <Spinner animation="border" role="status">
                        <span className="sr-only">Loading...</span>
                    </Spinner>
                </Container>
            );
        } else {
            return (
                <Container>
                    <Card /*style={{ width: "18rem" }}*/>
                        <Card.Body>
                            <Card.Title>{this.state.trip.name}</Card.Title>
                            <Card.Text>
                                <FontAwesomeIcon
                                    icon="map-marker-alt"
                                    className="mr-3"
                                />
                                {this.state.trip.location}
                            </Card.Text>
                            <Card.Text>
                                <FontAwesomeIcon
                                    icon="calendar-alt"
                                    className="mr-3"
                                />
                                <DatePicker
                                    className="form-control"
                                    selected={this.state.trip_session.from_date}
                                    dateFormat="dd. MM. yyyy"
                                    disabled={true}
                                />
                                <DatePicker
                                    className="form-control ml-3"
                                    selected={this.state.trip_session.to_date}
                                    dateFormat="dd. MM. yyyy"
                                    disabled={true}
                                />
                            </Card.Text>
                            <Card.Text>{this.state.trip.description}</Card.Text>
                        </Card.Body>

                        <Form className="d-flex">
                            <Card.Body>
                                <Card.Title className="text-muted">
                                    Gain achievements
                                </Card.Title>
                                <Form.Group
                                    as={Col}
                                    className="d-flex flex-column align-items-center"
                                >
                                    {achievements}
                                </Form.Group>
                            </Card.Body>
                            <Card.Body>
                                <Form.Group as={Col}>
                                    <Form.Label>XP reward</Form.Label>
                                    <InputGroup className="mb-3">
                                        <InputGroup.Prepend>
                                            <InputGroup.Text id="basic-addon1">
                                                Max{" "}
                                                {
                                                    this.state.trip
                                                        .possible_xp_reward
                                                }
                                            </InputGroup.Text>
                                        </InputGroup.Prepend>
                                        <Form.Control
                                            type="number"
                                            placeholder="XP reward"
                                            defaultValue={
                                                this.state.trip
                                                    .possible_xp_reward
                                            }
                                            className={validationClassName(
                                                "",
                                                this.state.form
                                            )}
                                        />
                                        <div class="invalid-feedback">
                                            {validationFeedback(
                                                "",
                                                this.state.form
                                            )}
                                        </div>
                                    </InputGroup>
                                </Form.Group>
                            </Card.Body>
                        </Form>
                    </Card>
                </Container>
            );
        }
    }
}

export default withRouter(Close);
