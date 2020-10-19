import React, { useContext, useState } from "react";
import { useEffect } from "react";
import axios from "axios";
import { LayoutContext } from "./LayoutProvider";
import { AllUsersContext } from "./AllUsersProvider";
import { Navbar } from "react-bootstrap";
import lightBulbOn from "../images/lightbulbon.png";
import lightBulbOff from "../images/lightbulboff.png";
import door from "../images/door.png";


// Fetch house layout
const HouseLayout = () => {
  const { layout, setLayout } = useContext(LayoutContext);
  const {users, setUsers} = useContext(AllUsersContext);
  

  
  return (
    <>
      <Navbar bg="light" >
    <Navbar.Brand href="#home" style={{marginLeft:"40%",  color:'black', fontWeight:"600"}}>House view</Navbar.Brand>
  </Navbar>
    <div style={{ textAlign: "center", marginLeft: "1%", marginTop:"20px"}}>
      {layout.map((room) => (
        <div key={room.name}  style={{
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
      
            {room.name}
            <br/><br/>
            
            {room.name !== "Outside" && <img src={lightBulbOn} style={{height:"30px", width:"30px"}}></img>}
            {room.name == "Outside" &&  <img src={lightBulbOn} style={{height:"30px", width:"30px", opacity:"0%"}}></img>}
           
            <br/>  <br/>
            {users.map((user) => (
          <div key={user.id} style={{ display:"inline-block"}}>
            {user.location == room.name && <span style={{color:"black"}}>{user.id}, </span>}
          </div>
             ))}
             <br/>
            {room.name !== "Outside" && <img src={door}  style={{height:"12px", width:"50px", marginTop:"28px"}}></img>}
            {room.name == "Outside" && <img src={door}  style={{height:"12px", width:"50px", marginTop:"28px", opacity:"0%"}}></img>}
         
     
          </div>
          
        </div>
            
      ))}
        </div>
        
    </>
  );
};

export default HouseLayout;
