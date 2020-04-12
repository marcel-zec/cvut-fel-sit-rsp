import React from "react";
import { Form, Col } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

function AchievementInput(props) {
    return (
        <>
            <Form.Check.Label>
                <Form.Check.Input
                    type="checkbox"
                    key={props.element.id}
                    id={props.element.id}
                    onChange={props.onChangeMethod}
                    defaultChecked={props.selected}
                />
                <FontAwesomeIcon icon={props.element.icon} size="lg" />
                {props.element.name}
            </Form.Check.Label>
        </>
    );
}
export default AchievementInput;
