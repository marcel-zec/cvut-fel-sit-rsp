import React from "react";
import Form from "react-bootstrap/Form";
import { Container, Col, Button, Row, Spinner } from "react-bootstrap";
import rules from "../../Files/validationRules.json";
import {
    formValidation,
    validationFeedback,
    validationClassName,
} from "../../Validator";
import MyAlert from "../SmartGadgets/MyAlert";
import { withRouter } from "react-router-dom";
import { appContext } from "../../appContext";
import Cookies from "js-cookie";

class Login extends React.Component {
    static contextType = appContext;

    state = {
        form: {
            loginTry: false,
            loginUnsuccess: false,
            isValid: false,
            elements: {
                email: {
                    touched: false,
                    valid: false,
                    validationRules: rules.login.email,
                },
                password: {
                    touched: false,
                    valid: false,
                    validationRules: rules.login.password,
                },
            },
        },
        user: {
            email: null,
            password: null,
        },
    };

    inputUpdateHandler = async (event, nameOfFormInput) => {
        const stringProperties = ["email", "password"];
        let newState = { ...this.state.user };
        //string inputs
        if (stringProperties.includes(nameOfFormInput)) {
            newState[nameOfFormInput] = event.target.value;
        }
        await this.setState({ user: newState });
        if (
            this.state.form.elements.hasOwnProperty(nameOfFormInput) &&
            this.state.form.elements[nameOfFormInput].touched
        ) {
            this.validateForm();
        }
        console.log(this.state.user);
    };

    submitHandler = async (event) => {
        event.preventDefault();
        console.log(this.state.user);
        await this.validateForm();
        if (this.state.form.isValid) {
            fetch("http://localhost:8080/login", {
                method: "POST",
                mode: "cors",
                credentials: "include",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(this.state.user),
            })
                .then((response) => {
                    //TODO - osetrenie vynimiek
                    if (response.ok) {
                        return response.json();
                    } else {
                        this.loginNotSuccessHandler();
                        return null;
                    }
                })
                .then((data) => {
                    this.context.login(data);
                });
        }
    };

    loginNotSuccessHandler = () => {
        const newState = { ...this.state.form };
        newState.loginTry = true;
        newState.loginUnsuccess = true;
        this.setState({ form: newState });
    };

    validateForm = async () => {
        console.log("in validation");
        const newState = { ...this.state.form };
        formValidation(newState, this.state.user);
        await this.setState({ form: newState });
    };

    render() {
        let loginNotSuccess = null;
        if (this.state.form.loginTry) {
            if (this.state.form.loginUnsuccess) {
                loginNotSuccess = (
                    <MyAlert
                        variant="danger"
                        text="Email or password is invalid."
                        heading="Login not successful!"
                    />
                );
            }
        }
        return (
            <Container className="login_container">
                <Row>
                    <Form
                        className="window radius login_form"
                        onSubmit={this.submitHandler}
                    >
                        <Form.Group controlId="formBasicEmail">
                            <Form.Label>Email address</Form.Label>
                            <Form.Control
                                type="email"
                                placeholder="Enter email"
                                onChange={(event) =>
                                    this.inputUpdateHandler(event, "email")
                                }
                                className={validationClassName(
                                    "email",
                                    this.state.form
                                )}
                            />
                            <div class="invalid-feedback">
                                {validationFeedback("email", this.state.form)}
                            </div>
                            <Form.Text className="text-muted">
                                We'll never share your email with anyone else.
                            </Form.Text>
                        </Form.Group>

                        <Form.Group controlId="formBasicPassword">
                            <Form.Label>Password</Form.Label>
                            <Form.Control
                                type="password"
                                placeholder="Password"
                                onChange={(event) =>
                                    this.inputUpdateHandler(event, "password")
                                }
                                className={validationClassName(
                                    "password",
                                    this.state.form
                                )}
                            />
                            <div class="invalid-feedback">
                                {validationFeedback(
                                    "password",
                                    this.state.form
                                )}
                            </div>
                        </Form.Group>
                        <Button
                            variant="primary"
                            type="submit"
                            className="submit"
                        >
                            Login
                        </Button>
                    </Form>
                </Row>
                {loginNotSuccess}
            </Container>
        );
    }
}

export default withRouter(Login);
