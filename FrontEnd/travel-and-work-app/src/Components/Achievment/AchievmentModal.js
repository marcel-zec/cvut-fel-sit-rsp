import React from "react";
import { Modal, Button } from "react-bootstrap";
import ModalCentered from "./ModalCentered";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import "./Modal.css";

function AchievmentModal(props) {
    const [modalShow, setModalShow] = React.useState(false);

    /* <Button variant="primary" onClick={() => setModalShow(true)}>
                Launch vertically centered modal
            </Button>*/

    const iconClass = "ml-2 mr-2";

    return (
        <>
            <FontAwesomeIcon
                className={iconClass + " modalButton"}
                icon={props.icon}
                onClick={() => setModalShow(true)}
                size="lg"
            />

            <ModalCentered
                show={modalShow}
                onHide={() => setModalShow(false)}
                title={props.title}
                description={props.description}
                icon={props.icon}
            />
        </>
    );
}
export default AchievmentModal;
