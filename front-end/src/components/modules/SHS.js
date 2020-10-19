import React, { useContext } from "react";
import { Button, Form, DropdownButton, Dropdown } from "react-bootstrap";
import { useState, useEffect, useCallback } from "react";
import axios from "axios";
import { UserContext } from "../UserProvider";
import { LayoutContext } from "../LayoutProvider";

// SHS module
const SHS = () => {
  const [users, setUsers] = useState([]);
  const [formData, setFormData] = useState([]);
  const { currentUser, setCurrentUser } = useContext(UserContext);
  const [userChosen, setUserChosen] = useState([]);
  const [newLocation, setNewLocation] = useState("");
  const [tempCurrent, setTempCurrent] = useState([]);
  const { layout, setLayout } = useContext(LayoutContext);
  const [newTemperature, setNewTemperature] = useState([]);

  // retrieve list of all profiles
  const getUsers = async () => {
    console.log("IM in get users");
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

  // retrieve list of all rooms
  const getRooms = async () => {
    const response = await axios
      .get("http://localhost:8080/api/rooms")
      .catch((err) => console.log("Error", err));
    if (response && response.data) setLayout(response.data);
    console.log(response.data);
  };

  const handleSelect = (e) => {
    setNewLocation(e);
    setTempCurrent(currentUser);
  };

  // when selecting a new location from the dropdown, will trigger use effect
  useEffect(() => {
    if (tempCurrent == undefined || tempCurrent == "") {
      return console.log("bad");
    }
    const putNewLocation = async () => {
      const response = await axios
        .put(`http://localhost:8080/api/users/${tempCurrent.id}`, {
          id: tempCurrent.id,
          name: tempCurrent.name,
          location: newLocation,
          privilege: tempCurrent.privilege,
        })
        .catch((err) => console.log("Error", err));
      Update();
    };
    putNewLocation();
  }, [newLocation, tempCurrent]);

  // update info after changing location
  const Update = async () => {
    const id = formData.id;
    const response = await axios
      .get(`http://localhost:8080/api/users/${id}`)
      .catch((err) => console.log("Error", err));
    if (response && response.data) setCurrentUser(response.data);
    console.log(currentUser);
    getUsers();
  };

  // retrieve temperature info
  const handleChange1 = (e) => {
    setNewTemperature({ ...newTemperature, [e.target.name]: e.target.value });
  };

  // update temperature
  const updateTemperature = async () => {
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
      <Form onSubmit={logIn} style={{ display: "inline" }}>
        <Form.Group controlId="formBasicPassword">
          <input
            name="id"
            type="text"
            placeholder="Enter ID"
            style={{ width: "20%", display: "inline" }}
            onChange={handleChange}
          />
          &nbsp;
          <Button
            variant="primary"
            size="sm"
            type="submit"
            style={{ display: "inline" }}
          >
            Log in
          </Button>
        </Form.Group>
      </Form>
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
          <div key={item.id}>
            <span style={{ fontWeight: "600" }}>ID= </span>
            {item.id}
            <span style={{ fontWeight: "600" }}> Name= </span>
            {item.name}
            <span style={{ fontWeight: "600" }}> Location=</span>{" "}
            {item.location}
            <span style={{ fontWeight: "600" }}> Privilege=</span>
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
            <Button variant="light" size="sm" style={{ fontSize: "10px" }}>
              Edit
            </Button>
          </div>
        ))}
      </div>
      <br />
      <Form>
      <Button variant="primary" size="sm" onClick={addUser}>
        Add Profile
      </Button>{" "}
        <div>
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
      >
        {layout.map((item) => (
          <div key={item.id}>
            <Dropdown.Item eventKey={item.name}>{item.name}</Dropdown.Item>
          </div>
        ))}
      </DropdownButton>
      <br />
      <span style={{ fontWeight: "600" }}>Outside Temperature</span>
      <div>
        <Form onSubmit={logIn}>
          <Form.Group
            controlId="formBasicPassword"
            style={{ display: "inline" }}
          >
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
            variant="primary"
            size="sm"
            type="submit"
            style={{ display: "inline" }}
            onClick={updateTemperature}
          >
            Apply
          </Button>
        </Form>
      </div>
    </>
  );
};

export default SHS;
