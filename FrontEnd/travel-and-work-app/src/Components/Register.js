import React from 'react';
import Form from "react-bootstrap/Form";
import { Col, Button, Row, Spinner } from "react-bootstrap";
import { Container } from "react-bootstrap";
import image from '../Files/images/register.jpg'

class Home extends React.Component {
	state = {
        countries: ["Choose country","Czechia","Slovakia","Germany","Italy"],
        form: {
            isValid: false,
            elements: {
                firstName: {
                    touched: false,
                    valid: false,
                    validationRules: [],
                },
                lastName: {
                    touched: false,
                    valid: false,
                    validationRules: [],
                },
                email: {
                    touched: false,
                    valid: false,
                    validationRules: [],
                },
                password: {
                    touched: false,
                    valid: false,
                    validationRules: [],
                },
                city: {
                    touched: false,
                    valid: false,
                    validationRules: [],
                },
                street: {
                    touched: false,
                    valid: false,
                    validationRules: [],
                },
                houseNumber: {
                    touched: false,
                    valid: false,
                    validationRules: [],
                },
                zipCode: {
                    touched: false,
                    valid: false,
                    validationRules: [],
                },
                country: {
                    touched: false,
                    valid: false,
                    validationRules: [],
                }
            }
        },
        user: {
            firstName: null,
            lastName: null,
            username: "stylishslave",
            email: null,
            password: null,
            city: null,
            street: null,
            houseNumber: null,
            zipCode: [],
            country: [],
            role: 'ROLE_USER',
        }
    };
    inputUpdateHandler = (event, nameOfFormInput) => {
        const stringProperties = [
            "firstName",
            "lastName",
            "email",
            "password",
            "city",
            "street",
            "houseNumber",
            "zipCode",
            "country",
        ];
        let newState = { ...this.state.user };

        //string inputs
        if (stringProperties.includes(nameOfFormInput)) {
            newState[nameOfFormInput] = event.target.value;
        } 
        //TODO: zjistit proc to trva o interval vic
        //console.log(newState);
        this.setState({ user: newState });
        console.log(this.state.user);
    };
    submitHandler = (event) => {
        event.preventDefault();
        console.log(this.state.user);
        //this.validateForm(this.state.achievement);

        /*fetch("http://localhost:8080/trip", {
            method: "POST",
            mode: "cors",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(this.state.trip),
        }).then((response) => {
            if (response.ok) this.props.history.push("/trip");
            //TODO - osetrenie vynimiek
            else console.log("Error: somethhing goes wrong");
        });*/
    };
  render(){
  		let countryOptions = null;
            if (this.state.countries.length > 0) {
                let countriesArray = [];
                for (var i = 0; i < this.state.countries.length-1; i++) {
                	if (i != 0) {
						countriesArray.push(<option>{this.state.countries[i]}</option>);
                	}else{
                		countriesArray.push(<option defaultValue disabled>{this.state.countries[i]}</option>);
                	}
                	
                }
                countryOptions = (
                    <Form.Control
                        as="select"
                        placeholder="Enter name"
                        onChange={(event) =>
                            this.inputUpdateHandler(event, "country")
                        }
                    >
                        {countriesArray}
                    </Form.Control>
                );
            }
        return (
            <Container className="register_container">
	    	
                
                <Row>
                    <Col xs={5}>
                        <img src={image} />
                    </Col>
                    <Col>
                        <h3>Register new user</h3>
                        <Form className="registerUserForm mt-3 mb-5" onSubmit={this.submitHandler}>
                            <div className="window radius">
                                <h5>Account information</h5>
                                <Form.Row>
                                    <Form.Group as={Col}>
                                        <Form.Label>First name</Form.Label>
                                        <Form.Control maxLength="30" autoComplete="name" placeholder="Enter your first name" onChange={(event) => this.inputUpdateHandler(event, "firstName")} required></Form.Control>
                                    </Form.Group>
                                    <Form.Group as={Col}>
                                        <Form.Label>Last name</Form.Label>
                                        <Form.Control maxLength="30" placeholder="Enter your last name" onChange={(event) => this.inputUpdateHandler(event, "lastName")} required></Form.Control>
                                    </Form.Group>
                                </Form.Row>
                                <Form.Row>
                                    <Form.Group as={Col}>
                                        <Form.Label>E-mail</Form.Label>
                                        <Form.Control maxLength="50" name="email" autoComplete="email" type="email" placeholder="Enter your valid e-mail" onChange={(event) => this.inputUpdateHandler(event, "email")} required></Form.Control>
                                    </Form.Group>
                                    <Form.Group as={Col}>
                                        <Form.Label>Password</Form.Label>
                                        <Form.Control maxLength="30" minLength="8" maxLength="16" type="password" placeholder="Password" onChange={(event) => this.inputUpdateHandler(event, "password")} required></Form.Control>
                                    </Form.Group>
                                </Form.Row>
                            </div>
                            <br/>
                            <div className="window radius">
                                <h5>Adress</h5>
                                <Form.Row>
                                    <Form.Group as={Col}>
                                        <Form.Label>City</Form.Label>
                                        <Form.Control maxLength="100" placeholder="City" onChange={(event) => this.inputUpdateHandler(event, "city")} required></Form.Control>
                                    </Form.Group>
                                    <Form.Group as={Col}>
                                        <Form.Label>Street</Form.Label>
                                        <Form.Control maxLength="50" placeholder="Street" onChange={(event) => this.inputUpdateHandler(event, "street")} required></Form.Control>
                                    </Form.Group>
                                </Form.Row>
                                <Form.Row>
                                    <Form.Group as={Col}></Form.Group>
                                    <Form.Group as={Col}>
                                        <Form.Label>ZIP code</Form.Label>
                                        <Form.Control min="10000" max="99999" maxLength="5" type="number" placeholder="ZIP code" onChange={(event) => this.inputUpdateHandler(event, "zipCode")} required></Form.Control>
                                    </Form.Group>
                                    <Form.Group as={Col}>
                                        <Form.Label>Country</Form.Label>
                                        {countryOptions}
                                    </Form.Group>
                                    <Form.Group as={Col}></Form.Group>
                                </Form.Row>
                                <Form.Row>
                                    <Button className="submit" variant="primary" type="submit">
                                        Create new account
                                    </Button>
                                </Form.Row>
                            </div>
                        </Form>
                    </Col>
                </Row>
            </Container>
		);
    
  }
}

export default Home;
