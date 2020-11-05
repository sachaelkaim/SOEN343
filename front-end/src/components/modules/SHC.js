import React, { useContext, useState, useEffect } from "react";
import { UserContext } from "../UserProvider";
import { LayoutContext } from "../LayoutProvider";
import axios from "axios";
import BootstrapSwitchButton from "bootstrap-switch-button-react";
import { Button, Form } from "react-bootstrap";

// SHC module
const SHC = () => {
  const { currentUser, setCurrentUser } = useContext(UserContext);
  const { layout, setLayout } = useContext(LayoutContext);
  const [lightOn, setLightOn] = useState([]);
  const [doorOpen, setDoorOpen] = useState([]);
  const [windowOpen, setWindowOpen] = useState([]);
  const [location, setLocation] = useState([]);
  const [location1, setLocation1] = useState([]);
  const [location2, setLocation2] = useState([]);

  const getRooms = async () => {
    const response = await axios
      .get("http://localhost:8080/api/rooms")
      .catch((err) => console.log("Error", err));
    if (response) setLayout(response.data);
  };

  //
  useEffect(() => {
    if (
      location == "Select location" ||
      location == undefined ||
      currentUser == undefined
    ) {
      return console.log("cannot process");
    }
    const lights = async () => {
      const response = await axios
        .put(
          `http://localhost:8080/api/core/light`,
          {
            userLocation: currentUser.location,
            privilege: currentUser.privilege,
            location: location,
            lightOn: lightOn
          },
          {
            data: {
            userLocation: currentUser.location,
              privilege: currentUser.privilege,
              location: location,
              lightOn: lightOn,
            },
          }
        )
        .catch((err) => console.log("Error", err));
      getRooms();
    };
    lights();
  }, [lightOn]);

  return (
    <>
      <h4 style={{ textAlign: "center" }}>Controls</h4>
      <br />
      <Form>
        <Form.Group>
          <Form inline>
            <Form.Control
              as="select"
              className="my-1 mr-sm-2"
              id="selectBox1"
              custom
              onChange={(e) => setLocation(e.target.value)}
            >
              <option>Select location</option>
              {layout.map((newlocation) => (
                <option key={newlocation.id} value={newlocation.name}>
                  {newlocation.name}
                </option>
              ))}
            </Form.Control>
            <Button
              size="ms"
              variant="dark"
              className="my-1"
              onClick={() => setLightOn(true)}
            >
              LightOn
            </Button>
            &nbsp;
            <Button
              variant="dark"
              className="my-1"
              onClick={() => setLightOn(false)}
            >
              LightOff
            </Button>
          </Form>
        </Form.Group>
        <Form.Group>
          <Form inline>
            <Form.Control
              as="select"
              className="my-1 mr-sm-2"
              id="selectBox1"
              custom
            >
              <option>Select location</option>
              {layout.map((newlocation) => (
                <option key={newlocation.id} value={newlocation.name}>
                  {newlocation.name}
                </option>
              ))}
            </Form.Control>
            <Button size="ms" type="submit" variant="dark" className="my-1">
              DoorOpen
            </Button>
            &nbsp;
            <Button type="submit" variant="dark" className="my-1">
              DoorClosed
            </Button>
          </Form>
        </Form.Group>
      </Form>
    </>
  );
};

export default SHC;
