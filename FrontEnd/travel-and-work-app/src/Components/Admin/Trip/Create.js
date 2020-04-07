import React from "react";
import Form from "react-bootstrap/Form";
import { Col, Button, Row } from "react-bootstrap";
import { Container } from "react-bootstrap";
import AchievementFormGroup from "./UI/AchievementFormGroup";
import SessionGroup from "./SessionGroup";

class Create extends React.Component {
    state = {
        achievements: null,
        show: true,
        categories: null,
        form: {
            isValid: false,
            elements: {
                name: {
                    touched: false,
                    valid: false,
                    validationRules: [],
                },
                short_name: {
                    touched: false,
                    valid: false,
                    validationRules: [],
                },
                deposit: {
                    touched: false,
                    valid: false,
                    validationRules: [],
                },
                required_level: {
                    touched: false,
                    valid: false,
                    validationRules: [],
                },
                possible_xp_reward: {
                    touched: false,
                    valid: false,
                    validationRules: [],
                },
                category: {
                    touched: false,
                    valid: false,
                    validationRules: [],
                },
                location: {
                    touched: false,
                    valid: false,
                    validationRules: [],
                },
                description: {
                    touched: false,
                    valid: false,
                    validationRules: [],
                },
                required_achievements: {
                    touched: false,
                    valid: false,
                    validationRules: [],
                },
                gain_achievements: {
                    touched: false,
                    valid: false,
                    validationRules: [],
                },
            },
        },
        trip: {
            name: null,
            short_name: null,
            deposit: null,
            required_level: null,
            possible_xp_reward: null,
            category: null,
            location: null,
            description: null,
            required_achievements: [],
            gain_achievements: [],
            sessions: [],
        },
    };

    /**
     * Update state from input.
     * @param {event} event
     * @param {String} nameOfFormInput
     * @param {Boolean} arrayToPush - if want push to array
     * @param {Boolean} checkbox
     */
    inputUpdateHandler(event, nameOfFormInput) {
        const stringProperties = [
            "name",
            "short_name",
            "deposit",
            "required_level",
            "possible_xp_reward",
            "category",
            "location",
            "description",
        ];
        const checkboxProperties = [
            "required_achievements",
            "gain_achievements",
        ];
        const newState = { ...this.state.trip };

        //string inputs
        if (stringProperties.includes(nameOfFormInput)) {
            newState[nameOfFormInput] = event.target.value;
        } else if (checkboxProperties.includes(nameOfFormInput)) {
            let found = newState[nameOfFormInput].find((object) => {
                return object.id == event.target.id;
            });
            //if found element, that means user unchecked element
            if (found) {
                let index = newState[nameOfFormInput].indexOf(found);
                newState[nameOfFormInput].splice(index, 1);
            }
            //if not found element, that means user checked element
            else {
                newState[nameOfFormInput].push({ id: event.target.id });
            }
        }
        this.setState({ trip: newState });
        console.log(this.state.trip);
    }

    sessionDeleteHandler = (session) => {
        let newState = [...this.state.trip.sessions];
        const found = newState.findIndex((element) => {
            return element.index == session.index;
        });
        if (found > -1) {
            newState.splice(found, 1);
        }
        this.setState((oldState) => ({
            trip: {
                ...oldState.trip,
                sessions: newState,
            },
        }));
    };

    inputSessionUpdateHandler = (session) => {
        console.log(this.state.trip);
        let newState = [...this.state.trip.sessions];
        const found = newState.findIndex((element) => {
            return element.index == session.index;
        });
        console.log("found: " + found);
        if (found > -1) {
            console.log("if in inputSessionUpdate");
            for (let property in newState[found]) {
                newState[found][property] = session[property];
            }
        } else {
            console.log("else in inputSessionUpdate");
            session.index = this.state.trip.sessions.length;
            newState.push(session);
        }
        this.setState((oldState) => ({
            trip: {
                ...oldState.trip,
                sessions: newState,
            },
        }));
        console.log("bavi?");
        console.log(this.state.trip.sessions);
    };

    fetchAchievementsHandler = async () => {
        const response = await fetch(`http://localhost:8080/achievement`);
        const data = await response.json();
        console.log(data);
        //show: false -> add class name to button and hide it
        this.setState({ achievements: data, show: false });
    };

    async componentDidMount() {
        const response = await fetch(`http://localhost:8080/category`);
        const data = await response.json();
        console.log(data);
        this.setState({ categories: data });
    }

    submitHandler = (event) => {
        event.preventDefault();
        console.log(this.state.trip);
    };

    render() {
        let possibleXPrewardOptions = [];
        for (let i = 0; i < 25; i++) {
            possibleXPrewardOptions.push(<option>{i + 1}</option>);
        }

        let categoryOptions = null;
        if (this.state.categories !== null) {
            if (this.state.categories.length > 0) {
                let categoriesArray = [];

                this.state.categories.forEach((element) => {
                    categoriesArray.push(<option>{element.name}</option>);
                });
                categoryOptions = (
                    <Form.Control
                        as="select"
                        onChange={(event) =>
                            this.inputUpdateHandler(event, "category")
                        }
                    >
                        {categoriesArray}
                    </Form.Control>
                );
            }
        }

        let requiredAchievements = null;
        let gainAchievements = null;

        if (this.state.achievements !== null) {
            if (this.state.achievements.length > 0) {
                requiredAchievements = (
                    <AchievementFormGroup
                        label="Required achievements"
                        items={this.state.achievements}
                        formInputName="requeired_achievements"
                        onChangeMethod={(event) =>
                            this.inputUpdateHandler(
                                event,
                                "required_achievements"
                            )
                        }
                    />
                );
                gainAchievements = (
                    <AchievementFormGroup
                        label="Gain achievements"
                        items={this.state.achievements}
                        formInputName="gain_achievements"
                        onChangeMethod={(event) =>
                            this.inputUpdateHandler(event, "gain_achievements")
                        }
                    />
                );
            }
        }

        let sessions = [];
        /*       this.state.sessions.forEach(() => {
            sessions.push(<SessionInput />);
        });
*/
        return (
            <Container>
                <Form className="mt-3 mb-5" onSubmit={this.submitHandler}>
                    <h1>Create trip</h1>
                    <Form.Row>
                        <Form.Group as={Col} controlId="formGridName">
                            <Form.Label>Name of trip</Form.Label>
                            <Form.Control
                                placeholder="Enter name"
                                onChange={(event) =>
                                    this.inputUpdateHandler(event, "name")
                                }
                            />
                        </Form.Group>

                        <Form.Group as={Col} controlId="formGridShortName">
                            <Form.Label>Identificatation name</Form.Label>
                            <Form.Control
                                placeholder="Enter unique key for trip"
                                onChange={(event) =>
                                    this.inputUpdateHandler(event, "short_name")
                                }
                            />
                        </Form.Group>
                    </Form.Row>
                    <Form.Row>
                        <Form.Group as={Col} controlId="formGridDeposit">
                            <Form.Label>Deposit</Form.Label>
                            <Form.Control
                                placeholder="Enter deposite price"
                                onChange={(event) =>
                                    this.inputUpdateHandler(event, "deposit")
                                }
                            />
                        </Form.Group>

                        <Form.Group as={Col} controlId="formGridExperience">
                            <Form.Label>Required level</Form.Label>
                            <Form.Control
                                placeholder="Enter minimum reqiured level"
                                onChange={(event) =>
                                    this.inputUpdateHandler(
                                        event,
                                        "required_level"
                                    )
                                }
                            />
                        </Form.Group>
                    </Form.Row>
                    <Form.Row>
                        <Form.Group
                            as={Col}
                            controlId="exampleForm.ControlSelect1"
                        >
                            <Form.Label>Possible XP reward</Form.Label>
                            <Form.Control
                                as="select"
                                onChange={(event) =>
                                    this.inputUpdateHandler(
                                        event,
                                        "possible_xp_reward"
                                    )
                                }
                            >
                                {possibleXPrewardOptions}
                            </Form.Control>
                        </Form.Group>

                        <Form.Group
                            as={Col}
                            controlId="exampleForm.ControlSelect1"
                        >
                            <Form.Label>Category</Form.Label>
                            {categoryOptions}
                        </Form.Group>
                    </Form.Row>
                    <Form.Group controlId="formGridLocation">
                        <Form.Label>Location</Form.Label>
                        <Form.Control
                            placeholder="Hotel Super, London Street 12, Manchester, England"
                            onChange={(event) =>
                                this.inputUpdateHandler(event, "location")
                            }
                        />
                    </Form.Group>
                    <Form.Group controlId="exampleForm.ControlTextarea1">
                        <Form.Label>Description</Form.Label>
                        <Form.Control
                            as="textarea"
                            rows="5"
                            onChange={(event) =>
                                this.inputUpdateHandler(event, "description")
                            }
                        />
                    </Form.Group>
                    <SessionGroup
                        onChangeMethod={this.inputSessionUpdateHandler}
                        sessions={this.state.trip.sessions}
                        forDeleteSession={this.sessionDeleteHandler}
                    />
                    <Button
                        variant="primary"
                        type="button"
                        onClick={() => this.fetchAchievementsHandler()}
                        className={this.state.show ? "" : "d-none"}
                    >
                        Achievements
                    </Button>
                    <Form.Row>
                        {requiredAchievements}

                        {gainAchievements}
                    </Form.Row>
                    <Button variant="primary" type="submit">
                        Submit
                    </Button>
                </Form>
            </Container>
        );
    }
}

export default Create;
