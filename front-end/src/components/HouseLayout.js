import React, { useContext } from "react";
import { LayoutContext } from "./LayoutProvider";
import { AllUsersContext } from "./AllUsersProvider";
import { Navbar } from "react-bootstrap";
import lightBulbOn from "../images/lightbulbon.png";
import lightBulbOff from "../images/lightbulboff.png";
import doorOpen from "../images/door.png";
import doorClosed from "../images/doorclosed.png";
import block from "../images/block.png";
import windowOpen from "../images/windowopen.png";
import windowClose from "../images/windowclosed.png";

// Fetch house layout
const HouseLayout = () => {
  const { layout, setLayout } = useContext(LayoutContext);
  const { users, setUsers } = useContext(AllUsersContext);

  return (
    <>
      <Navbar bg="light">
        <Navbar.Brand
          style={{ marginLeft: "40%", color: "black", fontWeight: "600" }}
        >
          House view
        </Navbar.Brand>
      </Navbar>
      <div style={{ textAlign: "center", marginLeft: "1%", marginTop: "20px" }}>
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
                fontSize: "18px",
                width: "350px",
                height: "350px",
              }}
            >
              {room.name} <br/>
              <div style={{fontSize:"15px"}}>temperature: {room.temperature}C</div>
              <br />
              <br />
              <div
                style={{ display: "table", width: "200px", marginLeft: "80px" }}
              >
                  {room.lightOn == false && (
                  <img
                    src={lightBulbOff}
                    style={{
                      height: "60px",
                      width: "60px",
                      opacity: "100%",
                      float: "left",
                    }}
                  ></img>
                )}
                 {room.lightOn == true && (
                  <img
                    src={lightBulbOn}
                    style={{
                      height: "60px",
                      width: "60px",
                      opacity: "100%",
                      float: "left",
                    }}
                  ></img>
                )}
                {room.windowState == "BLOCKED" && (
                  <img
                    src={block}
                    style={{
                      height: "60px",
                      width: "60px",
                      opacity: "100%",
                      float: "left",
                    }}
                  ></img>
                )}
                {room.windowState == "OPEN" && (
                  <img
                    src={windowOpen}
                    style={{
                      height: "60px",
                      width: "60px",
                      opacity: "100%",
                      float: "left",
                    }}
                  ></img>
                )}
                {room.windowState == "CLOSED" && (
                  <img
                    src={windowClose}
                    style={{
                      height: "60px",
                      width: "60px",
                      opacity: "100%",
                      float: "left",
                    }}
                  ></img>
                )}
                {room.doorState == "UNLOCKED" && (
                  <img
                    src={doorOpen}
                    style={{
                      height: "60px",
                      width: "60px",
                      marginTop: "",
                      float: "left",
                    }}
                  ></img>
                )}
                  {room.doorState == "LOCKED" && (
                  <img
                    src={doorClosed}
                    style={{
                      height: "60px",
                      width: "60px",
                      marginTop: "",
                      float: "left",
                    }}
                  ></img>
                )}
                <br /> <br />
              </div>
              <span>
                <br /> <br />
                <div style={{ width: "350px", height: "210px" }}>
                  {users.map((user) => (
                    <div key={user.id} style={{ display: "inline-block" }}>
                      {user.location === room.name && (
                        <span style={{ color: "#1E90FF", fontSize: "20px" }}>
                          {user.id},{" "}
                        </span>
                      )}
                    </div>
                  ))}
                </div>
              </span>
            </div>
          </div>
        ))}
      </div>
    </>
  );
};

export default HouseLayout;
