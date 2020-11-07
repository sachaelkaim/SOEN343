import React, { useContext } from "react";
import { Button, Form, DropdownButton, Dropdown } from "react-bootstrap";
import { Modal } from 'react-bootstrap';
import { useState, useEffect } from "react";
import axios from "axios";
import { UserContext } from "../UserProvider";
import { LayoutContext } from "../LayoutProvider";
import { AllUsersContext } from "../AllUsersProvider";

//Permissions
const Permissions={
  0:["open/close windows","unlock doors","open/close garage"," turn on/off lights"],
  1:["turn on/off lights","open/close windows"],
  2:["turn on/off lights","open/close windows"],
  3:["All permissions are revoked"]
}
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
  const[userToEdit, setUserToEdit] = useState([]);
  const [idToEdit, setidToEdit] = useState();
  const [desiredName, setDesiredName] = useState();
  const [desiredPrivilege, setDesiredPrivilege] = useState();
  const [desiredLocation, setDesiredLocation] = useState();
  const [userToEditFormData, setUserToEditFormData] = useState([]);

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
      .get(`http://localhost:8080/api/users/login/${id}`)
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
  const handleShowUserMenu = async (e,item) => {
    e.preventDefault();
    console.log(e);
    console.log(item);
    setUserToEdit(item);
    setidToEdit(item.id);
    // const data = userToEditFormData;
    // console.log(data);
    if (showUserMenu == false){
      setShowUserMenu(true);
    }
  };

  //edit a profile
  const editUser = async (e) => {
    e.preventDefault();
    console.log(userToEdit);
    if (desiredName == undefined){
      setDesiredName(userToEdit.name);
    }
    if (desiredPrivilege == undefined){
      setDesiredPrivilege(userToEdit.privilege);
    }
    const response = await axios
      .put(`http://localhost:8080/api/users/${idToEdit}`,
      {
        id: idToEdit,
        name: desiredName,
        location: userToEdit.location,
        privilege: desiredPrivilege
      })
      .catch((err) => console.log("Error", err));
    if (response)
      getUsers();
    if (response && idToEdit == currentUser.id)
      UpdateProfile();
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

  // update permissions
  const getPermissions=(privilege,location)=>{

    if(privilege==0)
      return Permissions[privilege].join(",");
    else{
      if(location=="Outside")
      {
        return "All permissions are revoked"
      }
      if(privilege==1)
        return Permissions[privilege].join(",");
      if(privilege==2)
        return Permissions[privilege].join(",");
      if(privilege==3)
        return Permissions[privilege].join(",");
    }  
  }

  // update outdoor temperature
  const updateOutdoorTemperature = async (e) => {
    console.log(newTemperature.id)
    e.preventDefault();
    const response = await axios
      .put("http://localhost:8080/api/rooms/outdoorTemperature", 
      { temperature: newTemperature.id },
      {
        data: {
          temperature: newTemperature.id
        },
      }
      )
      .catch((err) => console.log("Error", err));
    getRooms();
  };


  return (
    <>
      <h4 style={{ textAlign: "center" }}>Simulator</h4>
      <br />
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
          <Button variant="dark" type="submit">
            Log in
          </Button>
          &nbsp;
          <DropdownButton
            id="dropdown-basic-button"
            title="Set location"
            onSelect={handleSelect}
            variant="dark"
            style={{ display: "inline" }}
          >
            {layout.map((item) => (
              <div key={item.id}>
                <Dropdown.Item eventKey={item.name}>{item.name}</Dropdown.Item>
              </div>
            ))}
          </DropdownButton>
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
              <Button variant="light" size="sm" style={{ fontSize: "10px" }} onClick={(e) => handleShowUserMenu(e,item)}>
                Edit {item.id}
              </Button>
          { <Modal show={showUserMenu} onHide={handleCloseUserMenu}>
          <Modal.Header closeButton>
            <Modal.Title>Edit</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <h4>Change User Details</h4>
            <Form inline>
              <Form.Group>
                  <Form.Label className="my-1 mr-2"></Form.Label>
                  <Form.Control
                    as="textarea"
                    placeholder="Enter new name"
                    onChange= {(e) => setDesiredName(e.target.value)}
                  >
                  </Form.Control>
                  <Form.Control
                    as="select"
                    className="my-1 mr-sm-2"
                    onChange = {(e) => setDesiredPrivilege(e.target.value)}
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
          <Button variant="dark" onClick={addUser}>
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
            <div key={item.id} style={{ fontSize: "15ypx", fontWeight: "600" }}>
              <span
                style={{
                  fontWeight: "600",
                  color: "#1E90FF",
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
              <div>Permissions:({getPermissions(item.privilege,item.location)})</div>
              <Button
                variant="light"
                style={{ fontSize: "10px" }}
                onClick={() => deleteUser(item.id)}
              >
                Delete
              </Button>{" "}
              <Button variant="light" style={{ fontSize: "10px" }}>
                Edit
              </Button>
            </div>
          ))}
        </div>

        <br />
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
              type="submit"
              style={{ display: "inline" }}
              onClick={updateOutdoorTemperature}
            >
              Submit
            </Button>
          </Form>
        </div>
      </div>
    </>
  );
};

export default SHS;
