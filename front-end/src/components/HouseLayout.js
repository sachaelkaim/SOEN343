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
  const { users, setUsers } = useContext(AllUsersContext);

  // retrieve list of all rooms
  const getRooms = async () => {
    const response = await axios
      .get("http://localhost:8080/api/rooms")
      .catch((err) => console.log("Error", err));
    console.log(response);
    if (response && response.data) setLayout(response.data);
  };

  //Retrieve Specific Room
  const getRoom = async (roomName) => {
    const response = await axios
      .get(`http://localhost:8080/api/rooms/${roomName}`)
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

  //foreach async
  async function asyncForEach(array, callback) {
    for (let index = 0; index < array.length; index++) {
      await callback(array[index], index, array);
    }
  }


  async function updateRooms(){
    fetch('http://localhost:8080/api/rooms')
.then(function(response){ return response.json(); })
.then(function(data) {
    const items = data;
    console.log(items)
    items.forEach(element => {
      element["windowState"] = "edited"
    });
    console.log("new edited:")
    console.log(items)
})
}

  //update window of room (async version)
  const editRooms = async () => {
    asyncForEach(new Array(getRooms().data), async (element)=>{
      console.log(element)
    });
    // getRooms().data.foreach(element =>{
    //   const response = await axios.put(`http://localhost:8080/api/rooms/${element.name}`,
    //   {
    //     "name": element.name,
    //     "windowState": element.windowState,
    //     "doorState": element.windowState,
    //     "lightOn": true,
    //     "temperature": 100.00
    //   })
    // });
    console.log("HEY");
  };

  const [updateWindowState, setUpdateWindowState] = useState([]);

  // check if we retrieved value 
   const updateWindow = async () => {
      console.log(updateWindowState);

      // const response = await axios
      // .put(`http://localhost:8080/api/users/${layout[updateWindowState]}` , { name: layout[updateWindowState].name, windowState: layout[updateWindowState].windowState, doorState: "LOCKED", lightOn: layout[updateWindowState].lightOn, temperature: 0.00 })
      // .catch((err) => console.log("Error", err)).then(console.log(`from updateDoorState, the name and id are ${updateWindowState}`));
    getRooms();
  };

  //update Door(async version)
  // async function updateDoorState(roomName){
  //   console.log();
    // const response = await axios
    //   .put(`http://localhost:8080/api/users/${layout[roomName]}` , { name: layout[roomName].name, windowState: layout[roomName].windowState, doorState: "LOCKED", lightOn: layout[roomName].lightOn, temperature: 0.00 })
    //   .catch((err) => console.log("Error", err)).then(console.log(`from updateDoorState, the name and id are ${roomName}`));
    // getRooms();
  // };

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
                fontSize: "16px",
                width: "150px",
                height: "150px",
                textAlign: "center",
              }}
            >
              {room.name}
              <br />
              <br />
              {room.name !== "Outside" && (
                <img
                  src={lightBulbOn}
                  style={{ height: "35px", width: "35px" }}
                ></img>
              )}
              {room.name == "Outside" && (
                <img
                  src={lightBulbOn}
                  style={{ height: "35px", width: "35px", opacity: "0%" }}
                ></img>
              )}
              <br /> 
              {users.map((user) => (
                <div key={user.id} style={{ display: "inline-block" }}>
                  {user.location == room.name && (
                    <span style={{ color: "black" }}>{user.id}, </span>
                  )}
                </div>
              ))}
              <br />
              {room.name !== "Outside" && (
               
                <img
                  src={door}
                  style={{ height: "12px", width: "50px", marginTop:"35px"}}
                ></img>
               
              )}
              {room.name == "Outside" && (
              
                <img
                  src={door}
                  style={{
                    height: "12px",
                    width: "50px",
                    marginTop: "15px",
                    opacity: "0%",
                    marginTop:"35px"
                  }}
                ></img>
              
              )}
            </div>
          </div>
        ))}
      </div>
    </>
  );
};


export default HouseLayout;
