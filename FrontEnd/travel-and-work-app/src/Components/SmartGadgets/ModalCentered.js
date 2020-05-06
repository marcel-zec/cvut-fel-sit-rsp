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
    let footer = null;
    if (props && props.button) {
        footer = (
            <Modal.Footer>
                <Button
                    variant={
                        props.button.variant ? props.button.variant : "primary"
                    }
                    onClick={(event) =>
                        props.button.onClick(
                            event,
                            props.button.onClickParameter
                        )
                    }
                >
                    {props.button.title}
                </Button>
            </Modal.Footer>
        );
    }

    let description = null;
    if (props.description) {
        if (Array.isArray(props.description)) {
            description = [];
            props.description.forEach((item) => {
                description.push(<p>{item}</p>);
            });
        } else {
            description = props.description;
        }
    }

    return (
        <Modal
            {...props}
            size={props.size ? props.size : "sm"}
            aria-labelledby="contained-modal-title-vcenter"
            centered
            style={{
                textAlign: "center",
            }}
        >
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    {props.title}
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <FontAwesomeIcon icon={props.icon} size="6x" />
                <p>{description}</p>
            </Modal.Body>
            {footer}
        </Modal>
    );
}
export default ModalCentered;
