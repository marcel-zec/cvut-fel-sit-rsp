import React from "react";

/**
 *
 * @param {Object} formStateObject -
 *         form: {
 *           isValid: false,
 *           elements: {
 *               name: {
 *                   touched: false,
 *                   valid: false,
 *                   validationRules: rules
 *               },
 * @param {Object} inputsObject - you can acces to input with inputsObject[propName]
 */
export const formValidation = (formStateObject, inputsObject) => {
    console.log("in validation");
    for (let inputName in formStateObject.elements) {
        const input = inputsObject[inputName];
        if (
            formStateObject.hasOwnProperty("elements") &&
            formStateObject.elements.hasOwnProperty(inputName)
        ) {
            if (
                formStateObject.elements[inputName].hasOwnProperty(
                    "validationRules"
                ) &&
                formStateObject.elements[inputName].validationRules
            ) {
                const rules =
                    formStateObject.elements[inputName].validationRules;
                formStateObject.elements[inputName].touched = true;
                formStateObject.elements[inputName].valid = true;
                formStateObject.valid = true;
                console.log("I am validating..");
                console.log(inputName);
                if (rules.hasOwnProperty("required") && rules.required) {
                    if (!input || (isNaN(input) && input.trim() == "")) {
                        formStateObject.elements[inputName].valid = false;
                        formStateObject.valid = false;
                        console.log("required check: INVALID");
                    } else {
                        console.log("required check:  valid");
                    }
                }
                if (rules.hasOwnProperty("minLength")) {
                    if (!input || input.trim().length < rules.minLength) {
                        formStateObject.elements[inputName].valid = false;
                        formStateObject.valid = false;
                        console.log(
                            "min length (" +
                                rules.minLength +
                                ") check: INVALID"
                        );
                    } else {
                        console.log(
                            "min length (" + rules.minLength + ") check: valid"
                        );
                    }
                }
                if (rules.hasOwnProperty("maxLength")) {
                    if (!input || input.trim().length > rules.maxLength) {
                        formStateObject.elements[inputName].valid = false;
                        formStateObject.valid = false;
                        console.log(
                            "max length (" +
                                rules.maxLength +
                                ") check: INVALID"
                        );
                    } else {
                        console.log(
                            "max length (" + rules.maxLength + ") check: valid"
                        );
                    }
                }
                if (rules.hasOwnProperty("number") && rules.number) {
                    let parsed = parseFloat(input);
                    if (isNaN(parsed)) {
                        formStateObject.elements[inputName].valid = false;
                        formStateObject.valid = false;
                        console.log("is number check: INVALID");
                    } else {
                        console.log("is number check: valid");
                    }
                }
                if (rules.hasOwnProperty("number") && rules.number) {
                    let parsed = parseFloat(input);
                    if (isNaN(parsed)) {
                        formStateObject.elements[inputName].valid = false;
                        formStateObject.valid = false;
                        console.log("is number check: INVALID");
                    } else {
                        console.log("is number check: valid");
                        //minValue and maxValue is available only at numbers
                        if (rules.hasOwnProperty("minValue")) {
                            if (parsed < rules.minValue) {
                                formStateObject.elements[
                                    inputName
                                ].valid = false;
                                formStateObject.valid = false;
                                console.log(
                                    "min " +
                                        rules.minValue +
                                        " value check: INVALID"
                                );
                            } else {
                                console.log(
                                    "min " +
                                        rules.minValue +
                                        " value check: valid"
                                );
                            }
                            if (parsed > rules.maxValue) {
                                formStateObject.elements[
                                    inputName
                                ].valid = false;
                                formStateObject.valid = false;
                                console.log(
                                    "max " +
                                        rules.maxValue +
                                        " value check: INVALID"
                                );
                            } else {
                                console.log(
                                    "max " +
                                        rules.maxValue +
                                        " value check: valid"
                                );
                            }
                        }
                    }
                }

                formStateObject.isValid =
                    formStateObject.elements[inputName].valid;
            }
        }
    }
};

/**
 * Return class name for form input after validation(touched is true).
 */
export const validationClassName = (inputName, formStateObject) => {
    const elements = formStateObject.elements;
    if (elements[inputName].touched) {
        if (elements[inputName].valid) return "is-valid";
        return "is-invalid";
    } else {
        return "";
    }
};

/**
 * Set text of validation feedback by rules of input.
 */
export const validationFeedback = (inputName, formStateObject) => {
    if (formStateObject.elements.hasOwnProperty(inputName)) {
        const element = formStateObject.elements[inputName];
        if (element && element.validationRules) {
            const rules = element.validationRules;
            let text = "";
            if (rules.hasOwnProperty("required") && rules.required) {
                text += capitalizeFirstLetter(inputName) + " is required. ";
            }
            if (rules.hasOwnProperty("minLength")) {
                if (text.length > 0) {
                    text += "Minimal length is " + rules.minLength + ". ";
                } else {
                    text +=
                        "Minimal length of " +
                        capitalizeFirstLetter(inputName) +
                        " is " +
                        rules.minLength +
                        ". ";
                }
            }
            if (rules.hasOwnProperty("maxLength")) {
                if (text.length > 0) {
                    text += "Maximal length is " + rules.maxLength + ". ";
                } else {
                    text +=
                        "Maximal length of " +
                        capitalizeFirstLetter(inputName) +
                        " is " +
                        rules.maxLength +
                        ". ";
                }
            }

            if (rules.hasOwnProperty("number") && rules.number) {
                if (text.length > 0) {
                    text += "Needs to be number. ";
                } else {
                    text +=
                        capitalizeFirstLetter(inputName) +
                        " needs to be number. ";
                }

                if (rules.number && rules.hasOwnProperty("minValue")) {
                    if (text.length > 0) {
                        text += "Minimal value is " + rules.minValue + ". ";
                    } else {
                        text +=
                            capitalizeFirstLetter(inputName) +
                            " minimal value is " +
                            rules.minValue +
                            ". ";
                    }
                }

                if (rules.number && rules.hasOwnProperty("maxValue")) {
                    if (text.length > 0) {
                        text += "Maximal value is " + rules.maxValue + ". ";
                    } else {
                        text +=
                            capitalizeFirstLetter(inputName) +
                            " maximal value is " +
                            rules.maxValue +
                            ". ";
                    }
                }
            }

            return text;
        }
    }
};

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}
