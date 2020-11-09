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
  const [timeAuthorities, setTimeAuthorities] = useState("1");

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

   // retrieve time to notify auth
   const handleChange = (e) => {
    setTimeAuthorities({ ...timeAuthorities, [e.target.name]: e.target.value });
  };

  // change time to notify auth
  const notifyAuthorities = async (e) => {
    e.preventDefault();
    const response = await axios
      .post(
        "http://localhost:8080/api/security/notifyAuthoritiesTime",
        { timeAuthorities: timeAuthorities.id },
        {
          data: {
            timeAuthorities: timeAuthorities.id,
          },
        }
      )
      .catch((err) => console.log("Error", err));
  };

  return (
    <>
      <h4 style={{ textAlign: "center" }}>Security</h4>
      <br />
      <h6>Set Away Mode</h6>
      <BootstrapSwitchButton width={100} onChange={changeState} />
      <br/>
      <br/>
      <div>
          <Form>
            <Form.Group style={{ display: "inline" }}>
            <div style={{ fontWeight: "600" }}>Call Authorities Time (in minutes)</div>
              <input
                name="id"
                type="number"
                placeholder="Value"
                style={{ width: "10%" }}
                onChange={handleChange}
              />
            </Form.Group>
            &nbsp;
            <Button
              variant="dark"
              type="submit"
              style={{ display: "inline" }}
              onClick={notifyAuthorities}
            >
              Submit
            </Button>
          </Form>
        </div>
    </>
  );
};

export default SHP;
