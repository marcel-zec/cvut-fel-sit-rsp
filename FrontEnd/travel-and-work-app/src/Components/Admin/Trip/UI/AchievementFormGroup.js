import React from "react";
import { Form, Col } from "react-bootstrap";
import AchievementInput from "./AchievementInput";

function AchievementFormGroup(props) {
    let itemsArray = [];

    console.log("ACHIEVEMENT FORM GROUP");
    console.log(props);

    if (props.selected != null) {
        props.items.forEach((element) => {
            let found = props.selected.find(
                (item) => item.name == element.name
            );
            itemsArray.push(
                <AchievementInput
                    element={element}
                    selected={found}
                    onChangeMethod={(event) => props.onChangeMethod(event)}
                />
            );
        });
    }

    return (
        <Form.Group as={Col}>
            <Form.Label>{props.label}</Form.Label>
            <div className="d-flex flex-column align-items-start ml-5">
                {itemsArray}
            </div>
        </Form.Group>
    );
}
export default AchievementFormGroup;
