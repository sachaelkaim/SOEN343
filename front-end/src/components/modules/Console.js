import React, { useContext, useEffect, useState } from "react";
import { UserContext } from "../UserProvider";
import { LayoutContext } from "../LayoutProvider";
import axios from "axios";
import BootstrapSwitchButton from "bootstrap-switch-button-react";
import { Button, Form } from "react-bootstrap";

// Console
const Console = () => {
  const { currentUser, setCurrentUser } = useContext(UserContext);
  const { layout, setLayout } = useContext(LayoutContext);
  const [notifications, setNotifications] = useState([]);

  const [seconds, setSeconds] = useState(0);

  // set interval to fetch notifications
  useEffect(() => {
    const interval = setInterval(() => {
      setSeconds((seconds) => seconds + 1);
    }, 500);
    return () => clearInterval(interval);
  }, []);

  //call getnotifications to get notifications from backend
  useEffect(() => {
    getNotifications();
  }, [seconds]);

  const getNotifications = async () => {
    const response = await axios
      .get("http://localhost:8080/api/console")
      .catch((err) => console.log("Error", err));
    if (response) setNotifications(response.data);
  };

  return (
    <>
      {notifications.map((newNotification) => (
        <div key={newNotification.id}>
          {newNotification.time}&nbsp;
          {newNotification.module == "SHS" && (
            <span style={{ color: "#1E90FF" }}>{newNotification.module}</span>
          )}
           {newNotification.module == "SHC" && (
            <span style={{ color: "orange" }}>{newNotification.module}</span>
          )}
          {newNotification.module == "SHP" && (
            <span style={{ color: "green" }}>{newNotification.module}</span>
          )}
           {newNotification.module == "SHH" && (
            <span style={{ color: "red" }}>{newNotification.module}</span>
          )}
          &nbsp;{newNotification.message}
        </div>
      ))}
    </>
  );
};

export default Console;
