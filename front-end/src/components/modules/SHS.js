import React, { useContext } from "react";
import { Button, Form, DropdownButton, Dropdown } from "react-bootstrap";
import { useState, useEffect } from "react";
import axios from "axios";
import { UserContext } from "../UserProvider";

// SHS module
const SHS = () => {
  const [users, setUsers] = useState([]);
  const [formData, setFormData] = useState([]);
  const { currentUser, setCurrentUser } = useContext(UserContext);
  const [userChosen, setUserChosen] = useState([]);
  const [rooms, setRooms] = useState([]);
  const [newLocation, setNewLocation] = useState('');
  
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
  };

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
    console.log(response);
    if (response && response.data) setRooms(response.data);
  };

  // change logged User Location
  const changeUserLocation = async (e) => {
    handleSelect(e);
    const response = await axios
      .put(`http://localhost:8080/api/users/${currentUser.id}` , { id: currentUser.id, name: currentUser.name, location: newLocation, privilege: currentUser.privilege })
      .catch((err) => console.log("Error", err));
   getUsers();
   updateSimulationProfile();
  };

   // update simulation profile
   const updateSimulationProfile = async () => {
    const id = formData.id;
    const response = await axios
      .get(`http://localhost:8080/api/users/${id}`)
      .catch((err) => console.log("Error", err));
    if (response && response.data) setCurrentUser(response.data);
  };

  useEffect(() => {
    console.log(newLocation)
  }, [newLocation]);

  const handleSelect=(e)=>{
    console.log(e);
    setNewLocation(e)
    console.log(newLocation)
   
    
    
  }

  return (
    <>
      <Form onSubmit={logIn}>
        <Form.Group controlId="formBasicPassword">
          <input
            name="id"
            type="text"
            placeholder="Enter ID"
            style={{ width: "20%" }}
            onChange={handleChange}
          />
        </Form.Group>
        <Button variant="primary" type="submit">
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
        <br />
      </Form>
      <Button variant="primary" size="sm" onClick={addUser}>
        Add Profile
      </Button>{" "}
      <br /> <br />
      <DropdownButton id="dropdown-basic-button" title="Set location" size="sm" onSelect={changeUserLocation}>
        {rooms.map((item) => (
          <div key={item.id}>
            <Dropdown.Item eventKey={item.name} >{item.name}</Dropdown.Item>
          </div>
        ))}
      </DropdownButton>

    </>
  );
};

export default SHS;
