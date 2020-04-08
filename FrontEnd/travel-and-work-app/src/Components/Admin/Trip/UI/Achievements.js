import React from "react";
import { Form } from "react-bootstrap";
import AchievementFormGroup from "./AchievementFormGroup";

function Achievements(props) {
    let requiredAchievements = null;
    let gainAchievements = null;

    if (props.items !== null) {
        if (props.items.length > 0) {
            requiredAchievements = (
                <AchievementFormGroup
                    label="Required achievements"
                    items={props.items}
                    formInputName="requeired_achievements"
                    onChangeMethod={(event) =>
                        this.inputUpdateHandler(event, "required_achievements")
                    }
                />
            );
            gainAchievements = (
                <AchievementFormGroup
                    label="Gain achievements"
                    items={props.items}
                    formInputName="gain_achievements"
                    onChangeMethod={(event) =>
                        this.inputUpdateHandler(event, "gain_achievements")
                    }
                />
            );
        }
    }

    return (
        <Form.Row className="d-flex ml-5">
            {requiredAchievements}

            {gainAchievements}
        </Form.Row>
    );
}
export default Achievements;
