import React from "react";
import { Button, Form } from "react-bootstrap";
import { useState, useEffect } from "react";
import axios from "axios";

// Fetch house layout
const HouseLayout = () => {
  const [layout, setLayout] = useState([]);

  useEffect(() => {
    axios 
      .get("http://localhost:8080/api/rooms")
      .then((res) => {
        console.log(res);
        setLayout(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  // retrieve list of all rooms
  const getRooms = async () => {
    const response = await axios
      .get("http://localhost:8080/api/rooms")
      .catch((err) => console.log("Error", err));
    console.log(response);
    if (response && response.data) setLayout(response.data);
  };

  //add a room
  const addRoom = async () => {
    const response = await axios
      .post("http://localhost:8080/api/rooms", {}, {
        headers: {
        }
      })
      .catch((err) => console.log("Error", err));
    getRooms();
  };

  return (
    <>
      {layout.map((item) => (
        <ul key={item.name}>
          <li>
            <li>Name: {item.name}</li>
            <li>Window: {item.windowState}</li>
            <li>Door: {item.doorState}</li>
            <li>Lights:{item.lightOn}</li>
            <li>Temperature:{item.temperature}</li>
          </li>
        </ul>
      ))}
      <Button variant="primary" size="sm" onClick={addRoom}>
        Add Room
      </Button>{" "}
    </>
  );
};

export default HouseLayout;
