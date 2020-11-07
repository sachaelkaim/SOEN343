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
  const [doorLock, setdoorLock] = useState([]);
  const [windowOpen, setWindowOpen] = useState([]);
  const [location, setLocation] = useState([]);
  const [location1, setLocation1] = useState([]);
  const [location2, setLocation2] = useState([]);
  const [toggle, setToggle] = useState(true);
  const [state, setAutoMode] = useState();
  
  const getRooms = async () => {
    const response = await axios
      .get("http://localhost:8080/api/rooms")
      .catch((err) => console.log("Error", err));
    if (response) setLayout(response.data);
  };
  //Toggle option for auto mode button
const toggleAutoMode = () => {
    if (toggle == false) setToggle(true);
    else setToggle(false);
    axios
      .post("http://localhost:8080/api/state", { on: toggle })
      .then((response) => setAutoMode(response.data.id));
    console.log(toggle);
  };
  //
  useEffect(() => {
    if (
      location == "Select location" ||
      location == undefined ||
      currentUser == undefined ||
      lightOn == null
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
            lightOn: lightOn,
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
      setLightOn(null);
    };
    lights();
  }, [lightOn]);

  useEffect(() => {
    if (
      location1 == "Select location" ||
      location1 == undefined ||
      currentUser == undefined ||
      doorLock == ""
    ) {
      return console.log("cannot process");
    }
    const doors = async () => {
      const response = await axios
        .put(
          `http://localhost:8080/api/core/door`,
          {
            privilege: currentUser.privilege,
            location: location1,
            doorLock: doorLock,
          },
          {
            data: {
              privilege: currentUser.privilege,
              location: location1,
              doorLock: doorLock,
            },
          }
        )
        .catch((err) => console.log("Error", err));
      getRooms();
      setdoorLock("");
    };
    doors();
  }, [doorLock]);

  useEffect(() => {
    if (
      location2 == "Select location" ||
      location2 == undefined ||
      currentUser == undefined ||
      windowOpen == ""
    ) {
      return console.log("cannot process");
    }
    const windows = async () => {
      const response = await axios
        .put(
          `http://localhost:8080/api/core/window`,
          {
            userLocation: currentUser.location,
            privilege: currentUser.privilege,
            location: location2,
            windowOpen: windowOpen,
          },
          {
            data: {
              userLocation: currentUser.location,
              privilege: currentUser.privilege,
              location: location2,
              windowOpen: windowOpen,
            },
          }
        )
        .catch((err) => console.log("Error", err));
      getRooms();
      setWindowOpen("");
    };
    windows();
  }, [windowOpen]);

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
              onChange={(e) => setLocation1(e.target.value)}
            >
              <option>Select location</option>
              {layout.map((newlocation) => (
                <option key={newlocation.id} value={newlocation.name}>
                  {newlocation.name}
                </option>
              ))}
            </Form.Control>
            <Button
              variant="dark"
              className="my-1"
              onClick={() => setdoorLock("LOCKED")}
            >
              Lock Door
            </Button>
            &nbsp;
            <Button
              variant="dark"
              className="my-1"
              onClick={() => setdoorLock("UNLOCKED")}
            >
              Unlock Door
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
              onChange={(e) => setLocation2(e.target.value)}
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
              onClick={() => setWindowOpen("OPEN")}
            >
              Open Window
            </Button>
            &nbsp;
            <Button
              variant="dark"
              className="my-1"
              onClick={() => setWindowOpen("CLOSED")}
            >
              Close Window
            </Button>
          </Form>
        </Form.Group>
      </Form>
	   <br />
        
        <br />
        Auto Mode
        <br />
        <BootstrapSwitchButton width={100} onChange={toggleAutoMode} />
        <br />
        <br />
    </>
    
  );
};

export default SHC;
