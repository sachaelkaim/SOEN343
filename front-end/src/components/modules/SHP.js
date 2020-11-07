import React, { useContext, useState } from "react";
import { UserContext } from "../UserProvider";
import { LayoutContext } from "../LayoutProvider";
import axios from "axios";
import BootstrapSwitchButton from "bootstrap-switch-button-react";
import {
    Button,
    Form
} from "react-bootstrap";

// SHP module
const SHP = () => {
  const { currentUser, setCurrentUser } = useContext(UserContext);
  const { layout, setLayout } = useContext(LayoutContext);
  const [awayModeToggle, setawayModeToggle] = useState(true);

  const getRooms = async () => {
    const response = await axios
      .get("http://localhost:8080/api/rooms")
      .catch((err) => console.log("Error", err));
    if (response) setLayout(response.data);
  };

   // send awaystate to backend
   const changeState = async () => {
    if (
      currentUser == undefined 
    ) {
      return console.log("cannot process");
    }
    if (awayModeToggle == false) setawayModeToggle(true);
    else setawayModeToggle(false);
    const response = await axios
      .post("http://localhost:8080/api/security/setAwayMode", 
      { awayMode: awayModeToggle, userPrivilege: currentUser.privilege },
      {
        data: {
            awayMode: awayModeToggle,
            userPrivilege: currentUser.privilege
        },
      }
      )
      .catch((err) => console.log("Error", err));
    getRooms();
    getRooms();
  };

  return (
    <>
      <h4 style={{ textAlign: "center" }}>Security</h4>
      <br />
      <h6>Set Away Mode</h6>
      <BootstrapSwitchButton width={100} onChange={changeState} />
    </>
  );
};

export default SHP;
