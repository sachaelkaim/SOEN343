import React, { useContext } from "react";
import BootstrapSwitchButton from "bootstrap-switch-button-react";
import { Button, Image, Container } from "react-bootstrap";
import profileImage from "../images/profile.png";
import SHS from "./modules/SHS";
import { UserContext } from "./UserProvider";
//import { RoomContext } from "./RoomProvider";

// Simulation to turn on/off simulation, edit user, display time/date/location
const Simulation = () => {
  const { currentUser } = useContext(UserContext);
  console.log(currentUser)
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
        <BootstrapSwitchButton checked={false} width={100} />
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

        <div>
          {currentUser ? (
            <div>
              <div style={{ fontWeight: "600", textDecoration: "underline" }}>
                ID
              </div>
              {currentUser.id}
              <br />
              <br />
              <div style={{ fontWeight: "600", textDecoration: "underline" }}>
                User
              </div>
              {currentUser.name}
              <br />
              <br />
              <div style={{ fontWeight: "600", textDecoration: "underline" }}>
                Location
              </div>
              {currentUser.location}
              <br />
              <br />
              &nbsp;
            </div>
          ) : (
            <div></div>
          )}
        </div>
      </Container>
    </>
  );
};

export default Simulation;
