import React, { useContext } from "react";
import { Button, Form, DropdownButton, Dropdown } from "react-bootstrap";
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
              Apply
            </Button>
          </Form>
        </div>
      </div>
    </>
  );
};

export default SHS;
