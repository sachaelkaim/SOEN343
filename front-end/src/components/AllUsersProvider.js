import React, { useState, createContext } from "react";

export const AllUsersContext = createContext();

// this is how we will share all users
const AllUsersProvider = (props) => {
  const [users, setUsers] = useState([]);

  return (
    <AllUsersContext.Provider value={{ users, setUsers }}>
      {props.children}
    </AllUsersContext.Provider>
  );
};

export default AllUsersProvider;
