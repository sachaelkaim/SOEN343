import React, { useContext } from "react";
import { UserContext } from "../UserProvider";
import { LayoutContext } from "../LayoutProvider";
import axios from "axios";
import BootstrapSwitchButton from "bootstrap-switch-button-react";

const SHC = () => {
  const { currentUser, setCurrentUser } = useContext(UserContext);
  const { layout, setLayout } = useContext(LayoutContext);

  const getRooms = async () => {
    const response = await axios
      .get("http://localhost:8080/api/rooms")
      .catch((err) => console.log("Error", err));
    if (response) setLayout(response.data);
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
    </>
  );
};

export default SHC;
