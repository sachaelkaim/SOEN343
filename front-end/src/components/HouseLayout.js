import React, { useContext } from "react";
import { Button, Form } from "react-bootstrap";
import { useState, useEffect } from "react";
import axios from "axios";
//import { RoomContext } from "./RoomProvider";

// Fetch house layout
const HouseLayout = () => {
  const [layout, setLayout] = useState([]);
  //const [room, setRoom] = useState([]);

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

  // retrieve list of all rooms
  const getRooms = async () => {
    const response = await axios
      .get("http://localhost:8080/api/rooms")
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

  //update Door(async version)
  const updateDoorState = async (roomName) => {
    console.log();
    const response = await axios
      .put(`http://localhost:8080/api/users/${layout[roomName]}` , { name: layout[roomName].name, windowState: layout[roomName].windowState, doorState: "LOCKED", lightOn: layout[roomName].lightOn, temperature: 0.00 })
      .catch((err) => console.log("Error", err)).then(console.log(`from updateDoorState, the name and id are ${roomName}`));
    // getRooms();
  };

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
            <li>
              <Button variant="primary" size="sm" onClick={updateDoorState(item.name)}>
                Edit doorState
              </Button>{" "}
            </li>
          </li>
        </ul>
      ))}
      <Button variant="primary" size="sm" onClick={updateRooms}>
        Edit All Rooms
      </Button>{" "}
    </>
  );
};

export default HouseLayout;
