import React, { useContext, useState } from "react";
import { useEffect } from "react";
import axios from "axios";
import { LayoutContext } from "./LayoutProvider";
import { Navbar } from "react-bootstrap";

// Fetch house layout
const HouseLayout = () => {
  const { layout, setLayout } = useContext(LayoutContext);

  // generate all the layout info in layout variable
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
      <Navbar bg="light" >
    <Navbar.Brand href="#home" style={{marginLeft:"40%",  color:'black'}}>House view</Navbar.Brand>
  </Navbar>
    <div style={{ textAlign: "center", marginLeft: "1%", marginTop:"20px"}}>
      {layout.map((item) => (
        <div key={item.name}  style={{
          display:"inline-block"}}>
          <div
            style={{
              fontStyle: "italic",
              fontWeight:"600",
              border: "1px solid black",
              fontSize: "14px",
              width: "150px",
              height: "150px",
              textAlign: "center",
            }}
          >
            {item.name}
          </div>
        </div>
      
      ))}
        </div>
    </>
  );
};

export default HouseLayout;
