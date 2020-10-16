import React, { useState } from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import Dashboard from "./components/Dashboard";
import UserProvider from "./components/UserProvider";

function App() {
  return (
    <>
      <UserProvider>
        <div>
          <Dashboard />
        </div>
      </UserProvider>
    </>
  );
}

export default App;
