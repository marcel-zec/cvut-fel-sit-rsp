import React from "react";
import Form from "react-bootstrap/Form";
import image from "../../Files/images/background_login.jpg";
import { Container,Col, Button, Row, Spinner } from "react-bootstrap";

class Login extends React.Component {
    state = {
        form: {
            isValid: false,
            elements: {
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
                checkbox: {
                    touched: false,
                    valid: false,
                    validationRules: [],
                }
            }
        },
        user: {
            email: null,
            password: null,
            checkbox: false
        }
    };
    inputUpdateHandler = (event, nameOfFormInput) => {
        const stringProperties = [
            "email",
            "password"
        ];
        let newState = { ...this.state.user };
        //string inputs
        if (stringProperties.includes(nameOfFormInput)) {
            newState[nameOfFormInput] = event.target.value;
        } 
        this.setState({ user: newState });
        console.log(this.state.user);
    };
    submitHandler = (event) => {
        event.preventDefault();
        let checkbox = document.getElementById("checkbox_check");
        this.state.user.checkbox = checkbox.checked;
        console.log(this.state.user);
    };
    render() {
        return (

            <Container className="login_container">
                    
                <Row>
                    <Form className="window radius login_form" onSubmit={this.submitHandler}>
                        <Form.Group controlId="formBasicEmail">
                            <Form.Label>Email address</Form.Label>
                            <Form.Control type="email" placeholder="Enter email" onChange={(event) => this.inputUpdateHandler(event, "email")} required/>
                            <Form.Text className="text-muted">
                                We'll never share your email with anyone else.
                            </Form.Text>
                        </Form.Group>

                        <Form.Group controlId="formBasicPassword">
                            <Form.Label>Password</Form.Label>
                            <Form.Control type="password" placeholder="Password" onChange={(event) => this.inputUpdateHandler(event, "password")} required />
                        </Form.Group>
                        <Form.Group controlId="formBasicCheckbox">
                            <label className="checkbox">Check me out <Form.Check type="checkbox" id="checkbox_check" /></label>
                        </Form.Group>
                        <Button variant="primary" type="submit" className="submit">
                            Login
                        </Button>
                    </Form>
                </Row>
                <div className="login_background">
                        <img src={image} />
                    </div>
            </Container>
        );
    }
}

export default Login;
