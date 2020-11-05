import React, { useContext } from "react";
import { Button, Form, DropdownButton, Dropdown } from "react-bootstrap";
import { Modal } from 'react-bootstrap';
import { useState, useEffect } from "react";
import axios from "axios";
import { UserContext } from "../UserProvider";
import { LayoutContext } from "../LayoutProvider";
import { AllUsersContext } from "../AllUsersProvider";

// SHS module
const SHS = () => {
  const [formData, setFormData] = useState([]);
  const { currentUser, setCurrentUser } = useContext(UserContext); // import userProvider usestate, you can call these in any file, and everything will update/render together.
  const { users, setUsers } = useContext(AllUsersContext);
  const [userChosen, setUserChosen] = useState([]);
  const [newLocation, setNewLocation] = useState("");
  const { layout, setLayout } = useContext(LayoutContext);
  const [newTemperature, setNewTemperature] = useState([]);
  const [showUserMenu, setShowUserMenu] = useState(false);
  const handleCloseUserMenu = () => setShowUserMenu(false);
  // const id=0;
  const [userToEdit, setUserToEdit] = useState();
  const [desiredName, setDesiredName] = useState();
  const [desiredPrivilege, setDesiredPrivilege] = useState();
  const [desiredLocation, setDesiredLocation] = useState();

  // retrieve list of all profiles
  const getUsers = async () => {
    const response = await axios
      .get("http://localhost:8080/api/users")
      .catch((err) => console.log("Error", err));
    if (response && response.data) setUsers(response.data);
  };

  useEffect(() => {
    getUsers();
    getRooms();
  }, []);

  // get user login request info
  const logIn = async (e) => {
    e.preventDefault(); // prevent refresh on submit
    const id = formData.id;
    const response = await axios
      .get(`http://localhost:8080/api/users/${id}`)
      .catch((err) => console.log("Error", err));
    if (response && response.data) setCurrentUser(response.data);
    getUsers();
  };

  //login form handle change
  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  // add a profile
  const addUser = async () => {
    const response = await axios
      .post(
        "http://localhost:8080/api/users",
        { name: userChosen },
        {
          headers: {
            name: userChosen,
          },
        }
      )
      .catch((err) => console.log("Error", err));
    getUsers();
  };

  // delete a profile
  const deleteUser = async (id) => {
    const response = await axios
      .delete(`http://localhost:8080/api/users/${id}`)
      .catch((err) => console.log("Error", err));
    if (response) getUsers();
  };

  //edit a profile
  const editUser = async () => {
    const response = await axios
      .put(`http://localhost:8080/api/users/${userToEdit.id}`,
      {
        
      })
      .catch((err) => console.log("Error", err));
    if (response) getUsers();
  };

  // retrieve list of all rooms
  const getRooms = async () => {
    const response = await axios
      .get("http://localhost:8080/api/rooms")
      .catch((err) => console.log("Error", err));
    if (response && response.data) setLayout(response.data);
  };

  // handle setting new currentuser location
  const handleSelect = (e) => {
    setNewLocation(e);
  };

  //Pass id to editing user menu
  const handleShowUserMenu = async () => {
    if (showUserMenu == false){
      setShowUserMenu(true);
    }
  };

  // triggers when currentuser sets a new location and updates location
  useEffect(() => {
    if (currentUser == undefined || currentUser == "") {
      return console.log("cannot");
    }
    const putNewLocation = async () => {
      const response = await axios
        .put(
          `http://localhost:8080/api/users/changeLocation/${currentUser.id}`,
          { location: newLocation },
          {
            data: {
              location: newLocation,
            },
          }
        )
        .catch((err) => console.log("Error", err));
      getUsers();
      UpdateProfile();
    };
    putNewLocation();
  }, [newLocation]);

  // update profile
  const UpdateProfile = async () => {
    const response = await axios
      .get(`http://localhost:8080/api/users/${currentUser.id}`)
      .catch((err) => console.log("Error", err));
    if (response && response.data) setCurrentUser(response.data);
  };

  // retrieve temperature info
  const handleChange1 = (e) => {
    setNewTemperature({ ...newTemperature, [e.target.name]: e.target.value });
  };

  // update outdoor temperature
  const updateOutdoorTemperature = async (e) => {
    e.preventDefault();
    const response = await axios
      .put("http://localhost:8080/api/rooms/Outside", {
        name: "Outside",
        WindowState: null,
        DoorState: null,
        LightOn: false,
        temperature: newTemperature.id,
      })
      .catch((err) => console.log("Error", err));
    getRooms();
  };

  return (
    <>
      <div style={{ textAlign: "center" }}>
        <Form onSubmit={logIn}>
          <Form.Group controlId="formBasicPassword">
            <input
              name="id"
              type="text"
              placeholder="Enter ID"
              style={{ width: "20%", display: "inline", marginTop: "0px" }}
              onChange={handleChange}
            />
          </Form.Group>
          <Button variant="dark" size="" type="submit">
            Log in
          </Button>
        </Form>
        <br />
        <div
          style={{
            fontSize: "12px",
            overflowY: "scroll",
            height: "150px",
            borderTop: "1px solid black",
            borderBottom: "1px solid black",
          }}
        >
          {users.map((item) => (
            <div key={item.id} style={{ fontSize: "17px", fontWeight: "600" }}>
              <span
                style={{
                  fontWeight: "600",
                  color: "blue",
                  fontStyle: "italic",
                }}
              >
                ID {item.id}
              </span>
              <span> Name: </span>
              {item.name}
              &nbsp;
              <span> Location:</span> {item.location}
              &nbsp;
              <span s> Privilege:</span>
              &nbsp;
              {item.privilege}
              &nbsp;
              <Button
                variant="light"
                size="sm"
                style={{ fontSize: "10px" }}
                onClick={() => deleteUser(item.id)}
              >
                Delete
              </Button>{" "}
              <Button variant="light" size="sm" style={{ fontSize: "10px" }} onClick={handleShowUserMenu()}>
                Edit
              </Button>
          { <Modal show={showUserMenu} onHide={handleCloseUserMenu}>
          <Modal.Header closeButton>
            <Modal.Title>Edit</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <h4>Change User Details</h4>
            <Form>
              <Form.Group>
                <Form inline>
                  <Form.Label className="my-1 mr-2"></Form.Label>
                  <Form.Control
                    as="text"
                    onChange= {(e)=>setDesiredName(e.value)}
                  >
                  </Form.Control>
                  <Form.Control
                    as="select"
                    className="my-1 mr-sm-2"
                    id="selectBox"
                    onChange = {(e) => setDesiredPrivilege(e.value)}
                  >
                    <option value="0">0</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                  </Form.Control>
                  <Button
                    type="submit"
                    variant="dark"
                    className="my-1"
                    onClick={editUser}
                  >
                    Submit
                  </Button>
                </Form>
              </Form.Group>
            </Form>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="dark" onClick={handleCloseUserMenu}>
              Close
            </Button>
          </Modal.Footer>
        </Modal>}
            </div>
          ))}
        </div>
        <Form>
          <Button variant="dark" size="sm" onClick={addUser}>
            Add Profile
          </Button>{" "}
          <div style={{ fontWeight: "500" }}>
            <div className="form-check form-check-inline">
              <input
                className="form-check-input"
                type="radio"
                name="profile"
                value="parent"
                onClick={() => setUserChosen("parent")}
              />
              <label className="form-check-label">parent</label>
            </div>
            <div className="form-check form-check-inline">
              <input
                className="form-check-input"
                type="radio"
                name="profile"
                value="child"
                onClick={() => setUserChosen("child")}
              />
              <label className="form-check-label">child</label>
            </div>
            <div className="form-check form-check-inline">
              <input
                className="form-check-input"
                type="radio"
                name="profile"
                value="guest"
                onClick={() => setUserChosen("guest")}
              />
              <label className="form-check-label">guest</label>
            </div>
            <div className="form-check form-check-inline">
              <input
                className="form-check-input"
                type="radio"
                name="profile"
                value="stranger"
                onClick={() => setUserChosen("stranger")}
              />
              <label className="form-check-label">stranger</label>
            </div>
          </div>
        </Form>
        <br />
        <DropdownButton
          id="dropdown-basic-button"
          title="Set location"
          size="sm"
          onSelect={handleSelect}
          variant="dark"
        >
          {layout.map((item) => (
            <div key={item.id}>
              <Dropdown.Item eventKey={item.name}>{item.name}</Dropdown.Item>
            </div>
          ))}
        </DropdownButton>

        <span style={{ fontWeight: "600" }}>Outside Temperature</span>
        <div>
          <Form>
            <Form.Group style={{ display: "inline" }}>
              <input
                name="id"
                type="number"
                placeholder="Value"
                style={{ width: "20%" }}
                onChange={handleChange1}
              />
            </Form.Group>
            &nbsp;
            <Button
              variant="dark"
              size="sm"
              type="submit"
              style={{ display: "inline" }}
              onClick={updateOutdoorTemperature}
            >
              Apply
            </Button>
          </Form>
        </div>
      </div>
    </>
  );
};

export default SHS;
