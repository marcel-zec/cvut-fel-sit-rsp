import React from "react";
import { Form } from "react-bootstrap";
import AchievementInput from "./AchievementInput";

function AchievementFormGroup(props) {
    let itemsArray = [];
    props.items.forEach((element) => {
        itemsArray.push(
            <AchievementInput
                element={element}
                onChangeMethod={(event) => props.onChangeMethod(event)}
            />
        );
    });

    return (
        <Form.Group>
            <Form.Label>{props.label}</Form.Label>
            <div className="d-flex flex-column align-items-start ml-5">
                {itemsArray}
            </div>
        </Form.Group>
    );
}
export default AchievementFormGroup;
