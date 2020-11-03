import React, { useContext, useEffect, useState } from "react";
import BootstrapSwitchButton from "bootstrap-switch-button-react";
import {
  Button,
  Image,
  Container,
  Modal,
  DropdownButton,
  Dropdown,
  Navbar,
  Form,
} from "react-bootstrap";
import profileImage from "../images/profile.png";
import { UserContext } from "./UserProvider";
import { LayoutContext } from "./LayoutProvider";
import { AllUsersContext } from "./AllUsersProvider";
import axios from "axios";
import DateTimePicker from "react-datetime-picker";

// Simulation to turn on/off simulation, edit user, display time/date/location
const Simulation = () => {
  const { currentUser, setCurrentUser } = useContext(UserContext);
  const { layout, setLayout } = useContext(LayoutContext);
  const [ toggle, setToggle] = useState(true);
  const [ state, setState] = useState();
  const [ changeUserId, setchangeUserId] = useState("");
  const [ changeUserLocation, setChangeUserLocation] = useState("");
  const { users, setUsers } = useContext(AllUsersContext);
  const [ blockLocation, setBlockLocation] = useState([]);
  const [ value, onChange] = useState(new Date());
  const [ show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  // tell the system if the system is on or off
  const changeState = () => {
    if (toggle == false) setToggle(true);
    else setToggle(false);
    axios
      .post("http://localhost:8080/api/state", { on: toggle })
      .then((response) => setState(response.data.id));
      console.log(toggle)
    getRooms();
  };

  //update profile
  const UpdateProfile = async () => {
    const response = await axios
      .get(`http://localhost:8080/api/users/${currentUser.id}`)
      .catch((err) => console.log("Error", err));
    if (response && response.data) setCurrentUser(response.data);
  };

  // retrieve list of all profiles
  const getUsers = async () => {
    const response = await axios
      .get("http://localhost:8080/api/users")
      .catch((err) => console.log("Error", err));
    if (response && response.data) setUsers(response.data);
  };

  // update rooms after block, to display block
  const getRooms = async () => {
    const response = await axios
      .get("http://localhost:8080/api/rooms")
      .catch((err) => console.log("Error", err));
    if (response) setLayout(response.data);
  };

  // handle block location
  const handleSelect = (e) => {
    setBlockLocation(e);
  };

  // triggers when blocklocation state changes to block a window
  useEffect(() => {
    const blockWindow = async () => {
      const response1 = await axios
        .put(
          `http://localhost:8080/api/rooms/blockLocation/`,
          { windowState: "BLOCKED", location: blockLocation },
          {
            data: {
              windowState: "BLOCKED",
              location: blockLocation,
            },
          }
        )
        .catch((err) => console.log("Error", err));
      getRooms();
    };
    blockWindow();
  }, [blockLocation]);

  // set id
  const setId = (e) => {
    setchangeUserId(e);
  };

  // set location
  const setLocation = (e) => {
    setChangeUserLocation(e);
  };

  // change user location
  const handleChangeLocation = async (e) => {
    e.preventDefault(); // prevent refresh on submit
    const response = await axios
      .put(
        `http://localhost:8080/api/users/changeLocation/${changeUserId}`,
        { location: changeUserLocation },
        {
          data: {
            location: changeUserLocation,
          },
        }
      )
      .catch((err) => console.log("Error", err));
    getUsers();
    if (currentUser !== undefined) UpdateProfile();
  };

  return (
    <>
      <Container
        style={{
          border: "1px solid black",
          height: "48rem",
          borderRadius: "1rem",
          marginTop: "2rem",
          textAlign: "center",
        }}
      >
        <br />
        <div
          style={{
            textAlign: "center",
            color: "black",
            fontWeight: "600",
            fontSize: "20px",
          }}
        >
          Smart Home Simulator
        </div>
        <br />
        <BootstrapSwitchButton width={100} onChange={changeState} />
        <br />
        <br />
        <Button variant="dark" onClick={handleShow}>
          Edit
        </Button>
        <Modal show={show} onHide={handleClose}>
          <Modal.Header closeButton>
            <Modal.Title>Edit</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <h4>Place house inhabitants</h4>
            <Form>
              <Form.Group>
                <Form inline>
                  <Form.Label className="my-1 mr-2"></Form.Label>
                  <Form.Control
                    as="select"
                    className="my-1 mr-sm-2"
                    id="selectBox"
                    custom
                    onChange={(e) => setId(e.target.value)}
                  >
                    <option>Select ID</option>
                    {users.map((allusers) => (
                      <option key={allusers.id} value={allusers.id}>
                        ID:{allusers.id}
                      </option>
                    ))}
                  </Form.Control>
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
                    type="submit"
                    variant="dark"
                    className="my-1"
                    onClick={handleChangeLocation}
                  >
                    Submit
                  </Button>
                </Form>
              </Form.Group>
            </Form>
          </Modal.Body>
          <Modal.Body>
            <h4>Block windows movement</h4>
            <DropdownButton
              id="dropdown-basic-button"
              title="Set location"
              size="md"
              variant="dark"
              onSelect={handleSelect}
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
            <Button variant="dark" onClick={handleClose}>
              Close
            </Button>
          </Modal.Footer>
        </Modal>
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
          <span style={{ color: "#1E90FF" }}>
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
          <div>
            <br />
            <DateTimePicker onChange={onChange} value={value} />
          </div>
        </div>
      </Container>
    </>
  );
};

export default Simulation;
