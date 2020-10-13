import React from "react";
import BootstrapSwitchButton from "bootstrap-switch-button-react";

// Simulation to turn on/off simulation, edit user, display time/date/location
const Simulation = () => {
  return (
    <>
      <div
        style={{
          border: "1px solid black",
          height: "45rem",
          borderRadius: "1rem",
          margin: "1rem",
          textAlign: "center",
        }}
      >
        <h4>Simulation</h4>
        <br />
        <BootstrapSwitchButton checked={true} width={100} />
      </div>
    </>
  );
};

export default Simulation;
