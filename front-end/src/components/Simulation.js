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
import DateTimePicker from 'react-datetime-picker';

// Simulation to turn on/off simulation, edit user, display time/date/location
const Simulation = () => {
  const { currentUser } = useContext(UserContext);
  const { layout, setLayout } = useContext(LayoutContext);
  const [modalShow, setModalShow] = React.useState(false);
  const [toggle, setToggle] = useState(true);
  const [state, setState] = useState();
  const [changeUserId, setchangeUserId] = useState([]);
  const [changeUserLocation, setChangeUserLocation] = useState([]);
  const { users, setUsers } = useContext(AllUsersContext);
  const [blockLocation, setBlockLocation] = useState([]);
  const [blockRoomInfo, setBlockRoomInfo] = useState([]);
  const [value, onChange] = useState(new Date());

  // tell the system if the system is on or off
  const changeState = () => {
    toggle ? setToggle(false) : setToggle(true);
    axios
      .post("http://localhost:8080/api/state", { on: toggle })
      .then((response) => setState(response.data.id));
      console.log(true);
  };

  // update rooms after block, to display block
  const getRooms = async () => {
    const response = await axios
      .get("http://localhost:8080/api/rooms")
      .catch((err) => console.log("Error", err));
    if (response && response.data) setLayout(response.data);
  };

  useEffect(() => {
    const blockRoomInformation = async () => {
      const response = await axios
        .get(`http://localhost:8080/api/rooms/${blockLocation}`)
        .catch((err) => console.log("Error", err));
      setBlockRoomInfo(response.data);
    };
    blockRoomInformation();
  }, [blockLocation]);

  // triggers when blocklocation state changes
  useEffect(() => {
    const blockWindow = async () => {
      const response1 = await axios
        .put(`http://localhost:8080/api/rooms/${blockLocation}`, {
          name: blockRoomInfo.name,
          windowState: "BLOCKED",
          doorState: blockRoomInfo.doorState,
          lightOn: blockRoomInfo.lightOn,
          temperature: blockRoomInfo.temperature,
        })
        .catch((err) => console.log("Error", err));
      getRooms();
    };
    blockWindow();
  }, [blockRoomInfo]);

  // Set data
  const setData = (e) => {
    console.log(e);
    setChangeUserLocation(e);
  };

  const handleSelect = (e) => {
    console.log(e);
    setBlockLocation(e);
  };

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
                  onChange={(e) => setData(e.target.value)}
                >
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
                  onChange={(e) => setData(e.target.value)}
                >
                  {layout.map((newlocation) => (
                    <option key={newlocation.id} value={newlocation.name}>
                      {newlocation.name}
                    </option>
                  ))}
                </Form.Control>
                <Button
                  type="submit"
                  variant="primary"
                  className="my-1"
                  onClick={handleSelect}
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
            variant="primary"
            onSelect={handleSelect}
          >
            {layout.map((item) => (
              <div key={item.id}>
                {item.name !== "Outside" && (
                  <Dropdown.Item eventKey={item.name} onClick={props.onHide}>
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
        <BootstrapSwitchButton
          checked={false}
          width={100}
          onChange={changeState}
        />
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
        <div>
        <br/>
        
        
      <DateTimePicker
        onChange={onChange}
        value={value}
      />
    </div>
      </Container>
    </>
  );
};

export default Simulation;
