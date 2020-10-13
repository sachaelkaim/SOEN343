import React from "react";
import {useState, useEffect} from "react";
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
    <div>
      <ul>
        {layout.map((l) => (
          <li key={l.id}>Name: {l.name}<br/>Windows: {l.windows}<br/>Lights:{l.lights}</li>
          
        ))}
      </ul>
    </div>
    </>
  );
};

export default HouseLayout;
