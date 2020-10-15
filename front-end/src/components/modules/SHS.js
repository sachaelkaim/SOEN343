import React from "react";
import { Button, Form } from "react-bootstrap";
import { useState, useEffect } from "react";
import axios from "axios";

// SHS module
const SHS = () => {
  const [profile, setProfile] = useState([]);
  const [formData, setFormData] = useState([]);

  // retrieve list of all profiles
  const getprofiles = async () => {
    const response = await axios
      .get("http://localhost:8080/api/users")
      .catch((err) => console.log("Error", err));
    if (response && response.data) setProfile(response.data);
  };

  useEffect(() => {
    getprofiles();
  }, []);

   // get user login request info
   const logIn = async () => {
    const id = formData
    const response = await axios
      .get(`http://localhost:8080/api/users/${id}`)
      .catch((err) => console.log("Error", err));
    ;
  };

  const handleChange = (e) => {
      setFormData({...formData, [e.target.name]: e.target.value});
  }

  // add a profile
  const addProfile = async () => {
    const response = await axios
      .post("http://localhost:8080/api/users", {
        id: "4",
        name: "parent",
        location: "outside",
        priority: "0",
      })
      .catch((err) => console.log("Error", err));
      getprofiles();
  };

  // delete a profile
  const deleteProfile = async (id) => {
    const response = await axios
      .delete(`http://localhost:8080/api/users/${id}`)
      .catch((err) => console.log("Error", err));
      console.log(id);
    if (response)
      getprofiles();
  };

  
  return (
    <>

      <Form onSubmit={logIn}>
        <Form.Group controlId="formBasicPassword">
          <input name="id" type="text" placeholder="Enter ID" style={{width:"20%"}} onChange={handleChange}/>
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
        {profile.map((item) => (
          <div key={item.id}>
            <span style={{ fontWeight: "600" }}>ID= </span>
            {item.id}
            <span style={{ fontWeight: "600" }}> Name= </span>
            {item.name}
            <span style={{ fontWeight: "600" }}> Location=</span>{" "}
            {item.location}
            <span style={{ fontWeight: "600" }}> Priority=</span>
            {item.priority}
            &nbsp;
            <Button variant="light" size="sm" style={{ fontSize: "10px" }} 
           onClick={ () => deleteProfile(item.id)}
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
            value="option1"
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
            value="option2"
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
            value="option3"
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
            value="option3"
          />
          <label class="form-check-label" for="inlineradio3">
            stranger
          </label>
        </div>
        <br />
      </Form>
      <Button variant="primary" size="sm" onClick={addProfile}>
        Add Profile
      </Button>{" "}
     
    </>
  );
};

export default SHS;
