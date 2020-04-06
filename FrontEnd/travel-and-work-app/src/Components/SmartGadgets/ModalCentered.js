import React from "react";
import { Modal, Button } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

function ModalCentered(props) {
    /* TO USE SET THIS TO COMPOMENT WHEN THIS MODAL IS NEEDED more on https://react-bootstrap.github.io/components/modal/
    function App() {
        const [modalShow, setModalShow] = React.useState(false);

        return (
            <>
            <Button variant="primary" onClick={() => setModalShow(true)}>
                Launch vertically centered modal
            </Button>

            <MyVerticallyCenteredModal
                show={modalShow}
                onHide={() => setModalShow(false)}
            />
            </>
        );
    }
    */

    return (
        <Modal
            {...props}
            size="sm"
            aria-labelledby="contained-modal-title-vcenter"
            centered
            style={{
                textAlign: "center"
            }}
        >
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    {props.title}
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <FontAwesomeIcon icon={props.icon} size="6x" />
                <p>{props.description}</p>
            </Modal.Body>
        </Modal>
    );
}
export default ModalCentered;
