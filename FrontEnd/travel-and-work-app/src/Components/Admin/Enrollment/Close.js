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
import Slider from "@material-ui/core/Slider";
import NumericInput from "react-numeric-input";

class Close extends React.Component {
    state = {
        form: {
            isValid: false,
            elements: {
                actual_xp_reward: {
                    more: false,
                    less: false,
                },
            },
        },
        enrollment: {
            state: "ACTIVE",
            actual_xp_reward: 15,
            recieved_achievements_special: [
                { id: 3, name: "Kuchticek 1. triedy", icon: "water" },
                { id: 8, name: "Odvazlivec", icon: "trophy" },
            ],
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
        review: {
            note: "",
            rating: 5,
        },
    };

    /**
     * Update state from input.
     * @param {event} event
     * @param {String} inputName
     * @param {String} stateName
     */
    inputUpdateHandler = async (event, inputName) => {
        console.log("Input validation ------------------------------------");
        console.log(inputName);
        //console.log(stateName);
        // console.log(this.state[stateName][inputName]);

        const newState = { ...this.state };
        if (inputName == "actual_xp_reward") {
            console.log(event.target.value);
            const newFormState = { ...this.state.form };
            const newEnrollmentState = { ...this.state.enrollment };
            newEnrollmentState["actual_xp_reward"] = Number(event.target.value);
            newFormState.elements.actual_xp_reward["more"] = false;
            newFormState.elements.actual_xp_reward["less"] = false;
            this.setState({
                form: newFormState,
                enrollment: newEnrollmentState,
            });

            if (event.target.value > this.state.trip.possible_xp_reward) {
                newFormState.elements.actual_xp_reward["more"] = true;
                newFormState.elements.actual_xp_reward["less"] = false;
                this.setState({ form: newFormState });
            } else if (event.target.value < 0) {
                newFormState.elements.actual_xp_reward["more"] = false;
                newFormState.elements.actual_xp_reward["less"] = true;
                this.setState({ form: newFormState });
            }
            console.log(this.state);
        } else if ((inputName = "recieved_achievements_special")) {
            const newEnrollmentState = { ...this.state.enrollment };
            //remove if found
            let foundIndex = newEnrollmentState.recieved_achievements_special.findIndex(
                (item) => item.id == event.target.value
            );
            if (foundIndex > -1) {
                newEnrollmentState.recieved_achievements_special.splice(
                    foundIndex,
                    1
                );
            } else {
                //add if not found
                let foundIndexGained = this.state.trip.gain_achievements_special.findIndex(
                    (item) => item.id == event.target.value
                );
                if (foundIndexGained > -1)
                    newEnrollmentState.recieved_achievements_special.push(
                        this.state.trip.gain_achievements_special[
                            foundIndexGained
                        ]
                    );
            }
            this.setState({
                enrollment: newEnrollmentState,
            });
            console.log(this.state.enrollment);
        }
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
                            value={item.id}
                            onChange={(event) =>
                                this.inputUpdateHandler(
                                    event,
                                    "recieved_achievements_special"
                                )
                            }
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

        let achievements_removed = [];
        if (this.state.trip.gain_achievements_special.length > 0) {
            this.state.trip.gain_achievements_special.forEach((shouldGain) => {
                if (
                    !this.state.enrollment.recieved_achievements_special.find(
                        (notCanceled) => notCanceled.id == shouldGain.id
                    )
                ) {
                    achievements_removed.push(shouldGain.name);
                }
            });
        }
        let achievement_alert =
            achievements_removed.length > 0 ? (
                <MyAlert
                    variant="warning"
                    text="Removed achievements"
                    list={achievements_removed}
                />
            ) : null;

        let xp_reward_alert = null;
        if (this.state.form.elements.actual_xp_reward.less) {
            xp_reward_alert = (
                <MyAlert variant="danger" text="Cannot be less than 0." />
            );
        } else if (this.state.form.elements.actual_xp_reward.more) {
            xp_reward_alert = (
                <MyAlert
                    variant="danger"
                    text={
                        "Cannot be more than " +
                        this.state.trip.possible_xp_reward +
                        "."
                    }
                />
            );
        } else if (
            this.state.trip.possible_xp_reward >
            this.state.enrollment.actual_xp_reward
        ) {
            xp_reward_alert = (
                <MyAlert
                    variant="warning"
                    text={
                        "Reward was decresed by " +
                        (this.state.trip.possible_xp_reward -
                            this.state.enrollment.actual_xp_reward) +
                        " points."
                    }
                />
            );
        }

        const sliderMarks = [
            {
                value: 0,
                label: "0",
            },
            {
                value: 0.5,
            },
            {
                value: 1,
                label: "1",
            },
            {
                value: 1.5,
            },
            {
                value: 2,
                label: "2",
            },
            {
                value: 2.5,
            },
            {
                value: 3,
                label: "3",
            },
            {
                value: 3.5,
            },
            {
                value: 4,
                label: "4",
            },
            {
                value: 4.5,
            },
            {
                value: 5,
                label: "5",
            },
        ];

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
                <>
                    <Container className="mt-3">
                        <Card /*style={{ width: "18rem" }}*/>
                            <Card.Body>
                                <Card.Subtitle className="text-muted">
                                    Trip
                                </Card.Subtitle>
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
                                        selected={
                                            this.state.trip_session.from_date
                                        }
                                        dateFormat="dd. MM. yyyy"
                                        disabled={true}
                                    />
                                    <DatePicker
                                        className="form-control ml-3"
                                        selected={
                                            this.state.trip_session.to_date
                                        }
                                        dateFormat="dd. MM. yyyy"
                                        disabled={true}
                                    />
                                </Card.Text>
                                <Card.Text>
                                    {this.state.trip.description}
                                </Card.Text>
                            </Card.Body>
                        </Card>
                    </Container>

                    <Container className="mt-3">
                        <Card>
                            <Card.Body>
                                <Card.Subtitle className="text-muted">
                                    User reward
                                </Card.Subtitle>
                                <Card.Text>
                                    <FontAwesomeIcon
                                        icon="user-alt"
                                        className="mr-3"
                                    />
                                    {this.state.user.firstName +
                                        " " +
                                        this.state.user.lastName}
                                </Card.Text>
                            </Card.Body>

                            <Form>
                                <Row className="d-flex">
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
                                        {achievement_alert}
                                    </Card.Body>
                                    <Card.Body>
                                        <Form.Group as={Col}>
                                            <Form.Label>XP</Form.Label>
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
                                                    onInput={(event) =>
                                                        this.inputUpdateHandler(
                                                            event,
                                                            "actual_xp_reward",
                                                            "enrollment"
                                                        )
                                                    }
                                                />
                                            </InputGroup>
                                            {xp_reward_alert}
                                        </Form.Group>
                                    </Card.Body>
                                </Row>
                                <Row>
                                    <Card.Body>
                                        <Form.Label>Review</Form.Label>
                                        <Form.Group>
                                            <Slider
                                                className="w-50"
                                                defaultValue={5}
                                                aria-labelledby="discrete-slider"
                                                valueLabelDisplay="auto"
                                                step={0.5}
                                                marks={sliderMarks}
                                                min={0}
                                                max={5}
                                            />
                                        </Form.Group>
                                        <Form.Group>
                                            <Form.Control
                                                as="textarea"
                                                rows="5"
                                                onChange={(event) =>
                                                    this.inputUpdateHandler(
                                                        event,
                                                        "note"
                                                    )
                                                }
                                            />
                                        </Form.Group>
                                    </Card.Body>
                                </Row>
                                <Button
                                    variant="primary"
                                    type="submit"
                                    className="mb-5"
                                >
                                    Submit
                                </Button>
                            </Form>
                        </Card>
                    </Container>
                </>
            );
        }
    }
}

export default withRouter(Close);
