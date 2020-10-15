import React from "react";
import BootstrapSwitchButton from "bootstrap-switch-button-react";
import { Button, Image, Container } from "react-bootstrap";
import profileImage from "../images/profile.png";

// Simulation to turn on/off simulation, edit user, display time/date/location
const Simulation = () => {
  

  return (
    <>
      <Container
        style={{
          border: "1px solid black",
          height: "45rem",
          borderRadius: "1rem",
          margin: "1rem",
          textAlign: "center",
        }}
      >
        <h4>Simulation</h4>
        <br />
        <BootstrapSwitchButton checked={false} width={100}  />
        <br />
        <br />
        <Button variant="secondary" size="sm">
          Edit
        </Button>
        <br />
        <br />
        <Image
          src={profileImage}
          roundedCircle
          style={{
            width: "105px",
            height: "100px",
            border: "1px solid grey",
            opacity: "90%",
          }}
        />
        <br />
        <br />
        <Container style={{ fontWeight: "600" }}>User</Container>
        <br />
        <Container style={{ fontWeight: "600" }}>Location</Container>
      </Container>
    </>
  );
};

export default Simulation;
