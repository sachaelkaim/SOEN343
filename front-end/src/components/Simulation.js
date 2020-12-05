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

// Simulation to turn on/off simulation, edit user, display time/date/location
const Simulation = () => {
  const { currentUser, setCurrentUser } = useContext(UserContext);
  const { layout, setLayout } = useContext(LayoutContext);
  const [toggle, setToggle] = useState(true);
  const [state, setState] = useState();
  const [changeUserId, setchangeUserId] = useState("Select ID");
  const [changeUserLocation, setChangeUserLocation] = useState("");
  const { users, setUsers } = useContext(AllUsersContext);
  const [blockLocation, setBlockLocation] = useState([]);
  const [value, onChange] = useState(new Date());
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const [firstSummerMonth, setFirstSummerMonth] = useState("");
  const [lastSummerMonth, setLastSummerMonth] = useState("");
  
  const [time, setTime] = useState([""]);
  const [seconds, setSeconds] = useState(0);

  // set interval to fetch time
  useEffect(() => {
    const interval = setInterval(() => {
      setSeconds((seconds) => seconds + 1);
    }, 250);
    return () => clearInterval(interval);
  }, []);

  //call getTime to get time from backend
  useEffect(() => {
    getTime();
  }, [seconds]);

  const getTime = async () => {
    const response = await axios
      .get("http://localhost:8080/api/core/dateAndTime")
      .catch((err) => console.log("Error", err));
    if (response){
      setTime(response.data);
    } 
  };
  
  // tell the system if the system is on or off
  const changeState =  () => {
    if (toggle == false) setToggle(true);
    else setToggle(false);
    const response =  axios
      .post("http://localhost:8080/api/state", { on: toggle })
      .then((response) => setState(response.data.id));
      getRooms();
      getRooms();
  };

  const setSummerMonths = async (e) => {    
    postFirstSummerMonth(e);
    postLastSummerMonth(e);
  };

  const postFirstSummerMonth = async (e) => {
    e.preventDefault();
    if (currentUser == undefined){
      return console.log("current user selection is not valid");
    }

    if ((firstSummerMonth == null) || !(0 < firstSummerMonth < 13)){
      return console.log("cannot use this month value");
    }

    console.log("correct summer months flow, first: "+firstSummerMonth);
    const response = await axios
      .post(
        "http://localhost:8080/api/heating/setFirstSummerMonth",
        { userPrivilege: currentUser.privilege, firstSummerMonth: firstSummerMonth},
        {
          data: {
            userPrivilege: currentUser.privilege,
            firstSummerMonth: firstSummerMonth,
          },
        }
      )
      .catch((err) => console.log("Error", err));
  };

  const postLastSummerMonth = async (e) => {
    e.preventDefault();
    if (currentUser == undefined){
      return console.log("current user selection is not valid");
    }

    if ((lastSummerMonth == null) || !(0 < lastSummerMonth < 13)){
      return console.log("cannot use this month value");
    }
    
    console.log("correct summer months flow, last: "+lastSummerMonth);
    const response2 = await axios
      .post(
        "http://localhost:8080/api/heating/setLastSummerMonth",
        { userPrivilege: currentUser.privilege, lastSummerMonth: lastSummerMonth},
        {
          data: {
            userPrivilege: currentUser.privilege,
            lastSummerMonth: lastSummerMonth,
          },
        }
      )
      .catch((err) => console.log("Error", err));
  };

  const updateFirstSummerMonth = (e) => {
    setFirstSummerMonth(e);
    console.log("e is "+e);
    console.log("PRE: firstSummerMonth is "+firstSummerMonth);
    setFirstSummerMonth(e);
    console.log("POST: firstSummerMonth is "+firstSummerMonth);
    console.log("hi");
  };

  const updateLastSummerMonth = (e) => {
    setLastSummerMonth(e);
    console.log("e is "+e);
    console.log("PRE: lastSummerMonth is "+lastSummerMonth);
    setLastSummerMonth(e);
    console.log("POST: lastSummerMonth is "+lastSummerMonth);
    console.log("hi");
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
          `http://localhost:8080/api/rooms/blockLocation`,
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
      if(response){
        getUsers();
        getRooms();
      }
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
                  {item.name !== "Outside" && item.name !== "Backyard" &&(
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
        <div style={{fontWeight:"600", textDecoration: "underline"}}>
              Date and Time
            </div>
            <div>
            {time}
          </div>
          <br/><br/>
          <div>
      <Form>
        <Form.Group>
          <Form inline>
            <Form.Control
              as="select"
              className="my-1 mr-sm-2"
              id="selectBox1"
              custom
              onChange={(e) => updateFirstSummerMonth(e.target.value)}
            >
              <option>First Summer Month</option>
              <option value={1}>January</option>
              <option value={2}>February</option>
              <option value={3}>March</option>
              <option value={4}>April</option>
              <option value={5}>May</option>
              <option value={6}>June</option>
              <option value={7}>July</option>
              <option value={8}>August</option>
              <option value={9}>September</option>
              <option value={10}>October</option>
              <option value={11}>November</option>
              <option value={12}>December</option>
            </Form.Control>
            <Form.Control
              as="select"
              className="my-1 mr-sm-2"
              id="selectBox1"
              custom
              onChange={(e) => updateLastSummerMonth(e.target.value)}
            >
              <option>First Summer Month</option>
              <option value={1}>January</option>
              <option value={2}>February</option>
              <option value={3}>March</option>
              <option value={4}>April</option>
              <option value={5}>May</option>
              <option value={6}>June</option>
              <option value={7}>July</option>
              <option value={8}>August</option>
              <option value={9}>September</option>
              <option value={10}>October</option>
              <option value={11}>November</option>
              <option value={12}>December</option>
            </Form.Control>
            <Button size="ms" variant="dark" className="my-1"   onClick={setSummerMonths}>
              Submit Summer Months
            </Button>
          </Form>
        </Form.Group>
      </Form>
      <br/>
      </div>
          <br/><br/>
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
        </div>
      </Container>
    </>
  );
};

export default Simulation;
