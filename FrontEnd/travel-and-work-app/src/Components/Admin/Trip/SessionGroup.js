import React from "react";
import { Form, Button } from "react-bootstrap";
import SessionInput from "./UI/SessionInput";

class SessionGroup extends React.Component {
    state = { index: null };
    callbackFunction = (childData) => {
        console.log(childData);
    };

    addNewSessionHandler = () => {
        let session = {
            index: null,
            date_from: null,
            date_to: null,
            price: null,
        };
        this.props.onChangeMethod(session);
    };

    render() {
        let arraySessions = null;
        if (this.props !== null) {
            arraySessions = [];
            for (let session in this.props.sessions) {
                arraySessions.push(
                    <Form.Row>
                        <SessionInput
                            onChangeMethod={this.props.onChangeMethod}
                            forDeleteSession={this.props.forDeleteSession}
                            session={session}
                        />
                    </Form.Row>
                );
            }
        }

        return (
            <>
                <Form.Row>
                    <Button
                        variant="primary"
                        type="button"
                        onClick={() => this.addNewSessionHandler()}
                    >
                        Add trip session
                    </Button>
                </Form.Row>
                {arraySessions}
            </>
        );
    }
}
export default SessionGroup;
