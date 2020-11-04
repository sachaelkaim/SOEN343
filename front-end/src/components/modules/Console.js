import React, { useContext, useEffect, useState } from "react";
import { UserContext } from "../UserProvider";
import { LayoutContext } from "../LayoutProvider";
import axios from "axios";
import BootstrapSwitchButton from "bootstrap-switch-button-react";
import {
    Button,
    Form
} from "react-bootstrap";

// SHC module
const Console = () => {
  const { currentUser, setCurrentUser } = useContext(UserContext);
  const { layout, setLayout } = useContext(LayoutContext);
  const [notifications, setNotifications] = useState(["WElcome"]);

  const [seconds, setSeconds] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      setSeconds(seconds => seconds + 1);
    }, 1000);
    return () => clearInterval(interval);
  }, []);
  
  useEffect(()=>{
    getNotifications()
  }, [seconds])

  const getNotifications = async () => {
    const response = await axios
      .get("http://localhost:8080/api/console")
      .catch((err) => console.log("Error", err));
    if (response) setNotifications(response.data);
  };

  return (
    <>
         {notifications.map((newNotification) => (
                      <div key={newNotification.id} >
                        {newNotification.time}&nbsp;<span style={{color:"blue"}}>{newNotification.module}</span>&nbsp;{newNotification.message}
                      </div>
                    ))}
    </>
  );
};

export default Console;