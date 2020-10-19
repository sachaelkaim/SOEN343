import React, { useContext, useEffect, useState } from "react";
import BootstrapSwitchButton from "bootstrap-switch-button-react";
import {
  Button,
  Image,
  Container,
  Modal,
  DropdownButton,
  Dropdown,
} from "react-bootstrap";
import profileImage from "../images/profile.png";
import { UserContext } from "./UserProvider";
import { LayoutContext } from "./LayoutProvider";

// Simulation to turn on/off simulation, edit user, display time/date/location
const Simulation = () => {
  const { currentUser } = useContext(UserContext);
  const { layout, setLayout } = useContext(LayoutContext);
  const [modalShow, setModalShow] = React.useState(false);

  // edit popup
  const EditModal = (props) => {
    return (
      <Modal
        {...props}
        size="md"
        aria-labelledby="contained-modal-title-vcenter"
        centered
      >
        <Modal.Header closeButton>
          <Modal.Title id="contained-modal-title-vcenter">Edit</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <h4>Place house inhabitants in specific rooms, or outside home</h4>
          <p>--------------</p>
        </Modal.Body>
        <Modal.Body>
          <h4>Block windows movement</h4>
          <DropdownButton
            id="dropdown-basic-button"
            title="Set location"
            size="sm"
          >
            {layout.map((item) => (
              <div key={item.id}>
                {item.name !== "Outside" && (
                  <Dropdown.Item eventKey={item.name}>
                    {item.name} Window
                  </Dropdown.Item>
                )}
              </div>
            ))}
          </DropdownButton>
        </Modal.Body>
        <Modal.Footer>
          <Button onClick={props.onHide}>Close</Button>
        </Modal.Footer>
      </Modal>
    );
  };

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
        <Button
          variant="secondary"
          size="sm"
          onClick={() => setModalShow(true)}
        >
          Edit
        </Button>
        <EditModal show={modalShow} onHide={() => setModalShow(false)} />
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
        <div style={{ fontWeight: "600" }}>
          Outside Temperature.{" "}
          <span style={{ color: "blue" }}>
            {" "}
            {layout.map((item) => (
              <span key={item.name}>
                <span
                  style={{
                    fontSize: "14px",
                    width: "100px",
                    height: "100px",
                    textAlign: "center",
                  }}
                >
                  {item.name == "Outside" && item.temperature + "C"}
                </span>
              </span>
            ))}{" "}
          </span>
        </div>
      </Container>
    </>
  );
};

export default Simulation;
