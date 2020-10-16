import React, { useState, createContext } from "react";

export const UserContext = createContext();

// this is how we will share the current logged in user with other components
const UserProvider = (props) => {
  const [currentUser, setCurrentUser] = useState();
  
  return (
    <UserContext.Provider value={{ currentUser, setCurrentUser }}>
      {props.children}
    </UserContext.Provider>
  );
};

export default UserProvider;
