import React from "react";
import Form from "react-bootstrap/Form";
import { Col, Button, Row } from "react-bootstrap";
import { Container } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Spinner from "react-bootstrap/Spinner";
import { getElementError } from "@testing-library/react";

class Edit extends React.Component {
    state = { trip: null, achievements: null, categories: null };

    async componentDidMount() {
        const response1 = await fetch(
            `http://localhost:8080/trip/` + this.props.match.params.id
        );
        const data1 = await response1.json();
        console.log(data1);
        this.setState({ trip: data1 });

        const response2 = await fetch(`http://localhost:8080/category`);
        const data2 = await response2.json();
        console.log(data2);
        this.setState({
            categories: data2
        });

        const response3 = await fetch(`http://localhost:8080/achievement`);
        const data3 = await response3.json();
        console.log(data3);
        this.setState({ achievements: data3 });
    }

    achievementElementCompleted = (checkedInput, element) => {
        let formInput = checkedInput ? (
            <Form.Check.Input type="checkbox" checked />
        ) : (
            <Form.Check.Input type="checkbox" />
        );
        return (
            <>
                <Form.Check.Label>
                    {formInput}
                    <FontAwesomeIcon icon={element.icon} size="lg" />
                    {element.name}
                </Form.Check.Label>
            </>
        );
    };

    render() {
        if (
            this.state.categories === null ||
            this.state.trip === null ||
            this.state.achievements === null
        ) {
            return (
                <Container className="p-5">
                    <Spinner animation="border" role="status">
                        <span className="sr-only">Loading...</span>
                    </Spinner>
                </Container>
            );
        } else {
            let possibleXPrewardOptions = [];
            for (let i = 0; i < 25; i++) {
                let option =
                    i + 1 == this.state.trip.possible_xp_reward ? (
                        <option selected>{i + 1}</option>
                    ) : (
                        <option>{i + 1}</option>
                    );
                possibleXPrewardOptions.push(option);
            }

            let categoryOptions = null;

            if (this.state.categories.length > 0) {
                let categoriesArray = [];

                this.state.categories.forEach(element => {
                    let option = <option>{element.name}</option>;
                    if (this.state.trip.category) {
                        if (this.state.trip.category.name == element.name)
                            option = <option selected>{element.name}</option>;
                    }
                    categoriesArray.push(option);
                });
                categoryOptions = (
                    <Form.Control as="select">
                        <option>Choose category..</option>
                        {categoriesArray}
                    </Form.Control>
                );
            }

            let requiredAchievements = null;
            let requiredAchievementsArray = [];
            let gainAchievements = null;
            let gainAchievementsArray = [];

            if (this.state.achievements.length > 0) {
                this.state.achievements.forEach(element => {
                    if (
                        this.state.trip.required_achievements.some(
                            item => item.name == element.name
                        )
                    ) {
                        requiredAchievementsArray.push(
                            this.achievementElementCompleted(true, element)
                        );
                    } else {
                        requiredAchievementsArray.push(
                            this.achievementElementCompleted(false, element)
                        );
                    }
                });
                requiredAchievements = (
                    <Form.Group>
                        <Form.Label>Required achievements</Form.Label>
                        <div className="d-flex flex-column align-items-start ml-5">
                            {requiredAchievementsArray}
                        </div>
                    </Form.Group>
                );

                this.state.achievements.forEach(element => {
                    if (
                        this.state.trip.gain_achievements.some(
                            item => item.name == element.name
                        )
                    ) {
                        gainAchievementsArray.push(
                            this.achievementElementCompleted(true, element)
                        );
                    } else {
                        gainAchievementsArray.push(
                            this.achievementElementCompleted(false, element)
                        );
                    }
                });

                gainAchievements = (
                    <Form.Group>
                        <Form.Label>Gain achievements</Form.Label>
                        <div className="d-flex flex-column align-items-start ml-5">
                            {gainAchievementsArray}
                        </div>
                    </Form.Group>
                );
            }

            return (
                <Container>
                    <Form className="mt-3 mb-5">
                        <h1>Edit trip</h1>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formGridName">
                                <Form.Label>Name of trip</Form.Label>
                                <Form.Control value={this.state.trip.name} />
                            </Form.Group>

                            <Form.Group as={Col} controlId="formGridShortName">
                                <Form.Label>Identificatation name</Form.Label>
                                <Form.Control
                                    value={this.state.trip.short_name}
                                />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formGridDeposit">
                                <Form.Label>Deposit</Form.Label>
                                <Form.Control value={this.state.trip.deposit} />
                            </Form.Group>

                            <Form.Group as={Col} controlId="formGridExperience">
                                <Form.Label>Required level</Form.Label>
                                <Form.Control
                                    value={this.state.trip.requiered_level}
                                />
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
                            <Form.Control value={this.state.trip.location} />
                        </Form.Group>
                        <Form.Group controlId="exampleForm.ControlTextarea1">
                            <Form.Label>Description</Form.Label>
                            <Form.Control
                                as="textarea"
                                rows="5"
                                value={this.state.trip.description}
                            />
                        </Form.Group>
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
}

export default Edit;
