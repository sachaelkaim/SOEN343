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
            Name: {item.windowState}
            <br />
            Windows: {item.doorState}
            <br />
            Lights:{item.lightOn}
          </li>
        </ul>
      ))}
    </>
  );
};

export default HouseLayout;
