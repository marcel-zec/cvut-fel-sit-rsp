import React from "react";
import Form from "react-bootstrap/Form";
import { Col, Button, Row } from "react-bootstrap";
import { Container } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import SessionInput from "./UI/SessionInput";

class Create extends React.Component {
    state = {
        achievements: null,
        show: true,
        categories: null,
        trip: null,
        sessions: [
            {
                date_from: null,
                date_to: null,
                price: null,
            },
        ],
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
                    <Form.Control as="select">{categoriesArray}</Form.Control>
                );
            }
        }

        let requiredAchievements = null;
        let gainAchievements = null;
        if (this.state.achievements !== null) {
            if (this.state.achievements.length > 0) {
                let achievementsArray = [];
                this.state.achievements.forEach((element) => {
                    let achievementElement = (
                        <>
                            <Form.Check.Label>
                                <Form.Check.Input type="checkbox" />
                                <FontAwesomeIcon
                                    icon={element.icon}
                                    size="lg"
                                />
                                {element.name}
                            </Form.Check.Label>
                        </>
                    );
                    achievementsArray.push(achievementElement);
                });
                requiredAchievements = (
                    <Form.Group>
                        <Form.Label>Required achievements</Form.Label>
                        <div className="d-flex flex-column align-items-start ml-5">
                            {achievementsArray}
                        </div>
                    </Form.Group>
                );
                gainAchievements = (
                    <Form.Group>
                        <Form.Label>Gain achievements</Form.Label>
                        <div className="d-flex flex-column align-items-start ml-5">
                            {achievementsArray}
                        </div>
                    </Form.Group>
                );
            }
        }

        let sessions = [];
        this.state.sessions.forEach(() => {
            sessions.push(<SessionInput />);
        });

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
                                    this.setState({
                                        trip: { name: event.target.value },
                                    })
                                }
                            />
                        </Form.Group>

                        <Form.Group as={Col} controlId="formGridShortName">
                            <Form.Label>Identificatation name</Form.Label>
                            <Form.Control
                                placeholder="Enter unique key for trip"
                                onChange={(event) =>
                                    this.setState({
                                        trip: {
                                            short_name: event.target.value,
                                        },
                                    })
                                }
                            />
                        </Form.Group>
                    </Form.Row>
                    {sessions}
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
                    <Form.Row>
                        <Form.Group
                            as={Col}
                            controlId="exampleForm.ControlSelect1"
                        >
                            <Form.Label>Possible XP reward</Form.Label>
                            <Form.Control as="select">
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
                        <Form.Control placeholder="Hotel Super, London Street 12, Manchester, England" />
                    </Form.Group>
                    <Form.Group controlId="exampleForm.ControlTextarea1">
                        <Form.Label>Description</Form.Label>
                        <Form.Control as="textarea" rows="5" />
                    </Form.Group>
                    <Button
                        variant="primary"
                        type="button"
                        onClick={() => this.fetchAchievementsHandler()}
                        className={this.state.show ? "" : "d-none"}
                    >
                        Achievements
                    </Button>
                    <Form.Row>
                        <Form.Group
                            as={Col}
                            className="pl-5"
                            controlId="formGridName"
                        >
                            {requiredAchievements}
                        </Form.Group>
                        <Form.Group
                            as={Col}
                            className="pl-5"
                            controlId="formGridName"
                        >
                            {gainAchievements}
                        </Form.Group>
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
