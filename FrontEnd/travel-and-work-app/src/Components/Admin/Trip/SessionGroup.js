import React from "react";
import { Form, Button } from "react-bootstrap";
import SessionInput from "./UI/SessionInput";

class SessionGroup extends React.Component {
    send(el) {
        console.log("dsadsadsa");
    }

    render() {
        let sessions = [];
        this.props.sessions.forEach((element) => {
            <SessionInput
                onChangeMethod={(session) => this.props.onChangeMethod(session)}
            />;
        });
        return (
            <Form.Row>
                <Button variant="primary" type="button">
                    Add trip session
                </Button>
            </Form.Row>
        );
    }
}
export default SessionGroup;
