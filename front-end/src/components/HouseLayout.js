import React from "react";
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
    </>
  );
};

export default HouseLayout;
