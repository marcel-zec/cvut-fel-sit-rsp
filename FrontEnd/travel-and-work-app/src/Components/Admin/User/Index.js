import React from "react";
import Form from "react-bootstrap/Form";
import { Col, Button, Row, Table } from "react-bootstrap";
import { Container } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Spinner from "react-bootstrap/Spinner";
import { Link } from "react-router-dom";
import ButtonInRow from "../../SmartGadgets/ButtonInRow";

class Index extends React.Component {
  state = { users: null };
  async componentDidMount() {
    const response = await fetch(`http://localhost:8080/user`);
    const data = await response.json();
    console.log(data);
    this.setState({ users: data });
  }

  render() {
    if (this.state.users === null) {
      return (
        <Container className="mt-5 p-5">
          <Spinner animation="border" role="status">
            <span className="sr-only">Loading...</span>
          </Spinner>
        </Container>
      );
    } else {
      let tableRows = [];
      if (this.state.users > 0) {
        this.state.users.forEach((element) => {
          tableRows.push(
            <tr>
              <td>{element.firstName}</td>
              <td>{element.lastName}</td>
              <td>{element.email}</td>
              <td>{element.address.country}</td>
              <td>{element.address.city}</td>
              <td>
                <Link className="p-3">
                  <FontAwesomeIcon icon="trash-alt" />
                </Link>
              </td>
            </tr>
          );
        });
      }

      return (
        <Container>
          <Table striped bordered hover>
            <thead>
              <tr>
                <th>First name</th>
                <th>Last name</th>
                <th>E-mail</th>
                <th>Country</th>
                <th>City</th>
                <th>Settings</th>
              </tr>
            </thead>
            <tbody>{tableRows}</tbody>
          </Table>
        </Container>
      );
    }
  }
}

export default Index;
