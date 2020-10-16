import React, { useContext } from "react";
import { Button, Form } from "react-bootstrap";
import { useState, useEffect } from "react";
import axios from "axios";
import { UserContext } from "../UserProvider";

// SHS module
const SHS = () => {
  const [users, setUsers] = useState([]);
  const [formData, setFormData] = useState([]);
  const { currentUser, setCurrentUser } = useContext(UserContext);
  const [userChosen, setUserChosen] = useState([])

  // retrieve list of all profiles
  const getUsers = async () => {
    const response = await axios
      .get("http://localhost:8080/api/users")
      .catch((err) => console.log("Error", err));
    console.log(response);
    if (response && response.data) setUsers(response.data);
  };

  useEffect(() => {
    getUsers();
  }, []);

  // get user login request info
  const logIn = async (e) => {
    e.preventDefault(); // prevent refresh on submit
    const id = formData.id;
    console.log(id);
    const response = await axios
      .get(`http://localhost:8080/api/users/${id}`)
      .catch((err) => console.log("Error", err));
    console.log(response);
    if (response && response.data) setCurrentUser(response.data);
    console.log(response);
  };

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  // add a profile
  const addUser = async () => {
    const response = await axios
      .post("http://localhost:8080/api/users", {name: userChosen}, {
        headers: {
          'name': userChosen
        }
      })
      .catch((err) => console.log("Error", err));
    getUsers();
  };

  const handleChangeRadio = (e) => {
    setUserChosen(e.value);
  };

  // delete a profile
  const deleteUser = async (id) => {
    const response = await axios
      .delete(`http://localhost:8080/api/users/${id}`)
      .catch((err) => console.log("Error", err));
    console.log(id);
    if (response) getUsers();
  };

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
        <div class="form-check form-check-inline">
          <input
            class="form-check-input"
            type="radio"
            name="profile"
            value="parent"
            onClick= {() => setUserChosen('parent')}
          />
          <label class="form-check-label" for="inlineradio1">
            parent
          </label>
        </div>
        <div class="form-check form-check-inline">
          <input
            class="form-check-input"
            type="radio"
            name="profile"
            value="child"
            onClick= {() => setUserChosen('child')}
          />
          <label class="form-check-label" for="inlineradio2">
            child
          </label>
        </div>
        <div class="form-check form-check-inline">
          <input
            class="form-check-input"
            type="radio"
            name="profile"
            value="guest"
            onClick= {() => setUserChosen('guest')}
          />
          <label class="form-check-label" for="inlineradio3">
            guest
          </label>
        </div>
        <div class="form-check form-check-inline">
          <input
            class="form-check-input"
            type="radio"
            name="profile"
            value="stranger"
            onClick= {() => setUserChosen('stranger')}
          />
          <label class="form-check-label" for="inlineradio3">
            stranger
          </label>
        </div>
        <br />
      </Form>
      <Button variant="primary" size="sm" onClick={addUser}>
        Add Profile
      </Button>{" "}
    </>
  );
};

export default SHS;
