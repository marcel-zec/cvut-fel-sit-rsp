import React from "react";
import { Container, Spinner } from "react-bootstrap";

class Logout extends React.Component {
    async componentDidMount() {
        const response = await fetch(`http://localhost:8080/logout`);
        const data = await response.json();
        console.log("logout");
        console.log(data);
        console.log(response.status);
    }

    render() {
        return (
            <Container className="p-5 mt-5">
                <Spinner animation="border" role="status">
                    <span className="sr-only">Loading...</span>
                </Spinner>
            </Container>
        );
    }
}

export default Logout;
