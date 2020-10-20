import React, { useContext } from "react";
import { LayoutContext } from "./LayoutProvider";
import { AllUsersContext } from "./AllUsersProvider";
import { Navbar } from "react-bootstrap";
import lightBulbOn from "../images/lightbulbon.png";
import lightBulbOff from "../images/lightbulboff.png";
import door from "../images/door.png";

// Fetch house layout
const HouseLayout  = () => {
  const { layout, setLayout } = useContext(LayoutContext);
  const { users, setUsers } = useContext(AllUsersContext);

  return (
    <>
      <Navbar bg="light">
        <Navbar.Brand
          href="#home"
          style={{ marginLeft: "40%", color: "black", fontWeight: "600" }}
        >
          House view
        </Navbar.Brand>
      </Navbar>
      <div style={{ textAlign: "center", marginLeft: "1%", marginTop: "20px"}}>
        {layout.map((room) => (
          <div
            key={room.name}
            style={{
              display: "inline-block",
            }}
          >
            <div
              style={{
                fontStyle: "italic",
                fontWeight: "600",
                border: "1px solid black",
                fontSize: "16px",
                width: "250px",
                height: "250px",
                display: "inline-block",
              }}
            >
              <span style={{display:"inline-block", border: "1px solid black", width:"200px"}}>
              {room.name}
              <br />
              <br />
              {room.name !== "Outside" && (
                <img
                  src={lightBulbOn}
                  style={{ height: "50px", width: "50px" }}
                ></img>
              )}
              {room.name == "Outside" && (
                <img
                  src={lightBulbOn}
                  style={{ height: "50px", width: "50px", opacity: "0%" }}
                ></img>
              )}
              <br/><br/>
              {room.windowState == "BLOCKED" && (
                <img
                  src={lightBulbOn}
                  style={{ height: "50px", width: "50px", opacity: "100%" }}
                ></img>
              )}
              <br />
              {room.name !== "Outside" && (
                <img
                  src={door}
                  style={{ height: "50px", width: "50px", marginTop:""}}
                ></img>
              )}
              {room.name == "Outside" && (
                <img
                  src={door}
                  style={{
                    height: "50px",
                    width: "50px",
                    marginTop: "",
                    opacity: "0%",
                    marginTop:""
                  }}
                ></img>
              )}
            </span>
            </div>
            <div style={{width:"250px", height:"50px"}}>
              {users.map((user) => (
                <div key={user.id} style={{ display: "inline-block" }}>         
                  {user.location === room.name && (
                    <span style={{ color: "black", fontSize:"20px" }}>{user.id}, </span>
                  )}
                </div>
              ))}
              </div>
          </div>
        ))}
      </div>
    </>
  );
};

export default HouseLayout;
