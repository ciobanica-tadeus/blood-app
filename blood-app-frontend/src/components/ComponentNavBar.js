import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import classes from "./Components.module.css";
import "bootstrap/dist/css/bootstrap.min.css";

function ComponentNavBar(props) {
  return (
    <Navbar bg="light" variant="black">
       <h3 className={classes.title}>{props.text} </h3>
      <Container>
        <Navbar.Brand>
         
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />  
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="me-auto">
          </Nav>
          <Nav>
          <Nav.Link href="/">Log Out</Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default ComponentNavBar;
