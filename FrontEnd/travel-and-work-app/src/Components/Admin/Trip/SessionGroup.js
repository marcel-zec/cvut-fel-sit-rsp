import React from "react";
import { Form, Button } from "react-bootstrap";
import SessionInput from "./UI/SessionInput";

class SessionGroup extends React.Component {
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
            console.log(this.props.sessions);
            const sessions = this.props.sessions;
            for (let i = 0; i < sessions.length; i++) {
                arraySessions.push(
                    <Form.Row key={sessions[i].index}>
                        <SessionInput
                            onChangeMethod={this.props.onChangeMethod}
                            forDeleteSession={this.props.forDeleteSession}
                            session={sessions[i]}
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
