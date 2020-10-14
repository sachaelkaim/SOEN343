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
            Name: {item.name}
            <br />
            Windows: {item.windows}
            <br />
            Lights:{item.lights}
          </li>
        </ul>
      ))}
    </>
  );
};

export default HouseLayout;
