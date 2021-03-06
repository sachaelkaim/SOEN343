import React, { useState, createContext } from "react";

export const LayoutContext = createContext();

// this is how we will share the rooms
const LayoutProvider = (props) => {
  const [layout, setLayout] = useState([]);

  return (
    <LayoutContext.Provider value={{ layout, setLayout }}>
      {props.children}
    </LayoutContext.Provider>
  );
};

export default LayoutProvider;
