import React, { useState, createContext } from "react";

export const RoomContext = createContext();

// this is how we will share the current logged in user with other components
const RoomProvider = (props) => {
  const [room, setRoom] = useState();
  
  return (
    <RoomContext.Provider value={{ room, setRoom }}>
      {props.children}
    </RoomContext.Provider>
  );
};

export default RoomProvider;
