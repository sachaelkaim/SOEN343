import React, { useContext } from "react";
import { useEffect } from "react";
import axios from "axios";
import { LayoutContext } from "./LayoutProvider";


// Fetch house layout
const HouseLayout = () => {
  const {layout, setLayout} = useContext(LayoutContext);

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
        <div key={item.name}>
          <div style={{border:"1px solid black", fontSize:"14px", width:"100px", height:"100px",    textAlign: "center",}}>{item.name}</div>
        </div>
      ))}
    </>
  );
};

export default HouseLayout;
