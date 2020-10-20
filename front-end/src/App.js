import React, { useState } from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import Dashboard from "./components/Dashboard";
import UserProvider from "./components/UserProvider";
import LayoutProvider from "./components/LayoutProvider";

function App() {
  return (
    <>
      <UserProvider>
        <LayoutProvider>
        <div>
          <Dashboard />
        </div>
        </LayoutProvider>
      </UserProvider>
    </>
  );
}

export default App;
