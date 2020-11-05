import React, { useContext, useState } from "react";
import { UserContext } from "../UserProvider";
import { LayoutContext } from "../LayoutProvider";
import axios from "axios";
import BootstrapSwitchButton from "bootstrap-switch-button-react";
import { Button } from "react-bootstrap"
const SHC = () => {
  const { currentUser, setCurrentUser } = useContext(UserContext);
  const { layout, setLayout } = useContext(LayoutContext);
  const [toggle, setToggle] = useState(true);
  const [state, setAutoMode] = useState();

  const getRooms = async () => {
    const response = await axios
      .get("http://localhost:8080/api/rooms")
      .catch((err) => console.log("Error", err));
    if (response) setLayout(response.data);
  };
  const toggleAutoMode = () => {
    if (toggle == false) setToggle(true);
    else setToggle(false);
    axios
      .post("http://localhost:8080/api/state", { on: toggle })
      .then((response) => setAutoMode(response.data.id));
    console.log(toggle);
  };
  return (
    <>
      <h4 style={{ textAlign: "center" }}>Controls</h4>
      <br />
      <h6 style={{ marginLeft: "230px" }}>
        Light &nbsp;&nbsp;Window &nbsp;&nbsp;Door
      </h6>
      <div
        style={{
          fontWeight: "600",
          fontSize: "17px",
          borderTop: "1px solid black",
          borderBottom: "1px solid black",
        }}
      >
        {layout.map((item) => (
          <div key={item.id} style={{ display: "inline" }}>
            &nbsp;{" "}
            <div style={{ display: "inline", position: "absolute" }}>
              {item.name}
            </div>{" "}
            <div
              style={{
                paddingLeft: "210px",
                display: "inline",
                position: "relative",
              }}
            >
              <BootstrapSwitchButton
                checked={false}
                size="xs"
                onstyle="dark"
                offstyle="light"
                style="border"
              />
            </div>
            &nbsp;{" "}
            <div style={{ display: "inline" }}>
              <BootstrapSwitchButton
                checked={false}
                size="xs"
                onstyle="dark"
                offstyle="light"
                style="border"
              />
            </div>
            &nbsp;{" "}
            <div style={{ display: "inline" }}>
              <BootstrapSwitchButton
                checked={false}
                size="xs"
                onstyle="dark"
                offstyle="light"
                style="border"
              />
            </div>
            <br />
          </div>
        ))}
      </div>
      <br />
        
        <br />
        Auto Mode
        <br />
        <BootstrapSwitchButton width={100} onChange={toggleAutoMode} />
        <br />
        <br />
    </>
  );
};

export default SHC;