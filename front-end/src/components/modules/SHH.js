import React, { useEffect, useState, useContext } from "react";
import { Button, Form, DropdownButton, Dropdown } from "react-bootstrap";
import axios from "axios";
import { UserContext } from "../UserProvider";
import { LayoutContext } from "../LayoutProvider";

// SHH module
const SHH = () => {
  const { currentUser, setCurrentUser } = useContext(UserContext);
  const { layout, setLayout } = useContext(LayoutContext);
  const [locations, setLocations] = useState([]);
  const [zones, setZones] = useState([]);
  let checkBoxArr = [];

  // retrieve list of all available locations for zone selection
  const getAvailableLocations = async () => {
    const response = await axios
      .get("http://localhost:8080/api/heating/availableLocations")
      .catch((err) => console.log("Error", err));
    if (response && response.data) setLocations(response.data);
  };

  // Create new zone
  const createNewZone = async (e) => {
    if (currentUser == undefined) {
      return console.log("cannot process");
    }
    e.preventDefault(); // prevent refresh on submit
    unCheck();
    const response = await axios
      .post(
        `http://localhost:8080/api/heating/${checkBoxArr}`,
        { userPrivilege: currentUser.privilege },
        {
          data: {
            userPrivilege: currentUser.privilege,
          },
        }
      )
      .catch((err) => console.log("Error", err));
    if (response && response.data) getAvailableLocations();
    displayZones();
  };

  // display zones
  const displayZones = async () => {
    const response = await axios
      .get("http://localhost:8080/api/heating/displayZones")
      .catch((err) => console.log("Error", err));
    if (response && response.data) setZones(response.data);
  };

  // checkbox onchange
  const onChange = (e) => {
    if (e.target.type === "checkbox" && !e.target.checked) {
      for (var i = checkBoxArr.length; i--; ) {
        if (checkBoxArr[i] === e.target.value) checkBoxArr.splice(i, 1);
      }
    } else {
      checkBoxArr.push(e.target.value);
    } 
  };

  // uncheck all after submitting zone
  const unCheck = () => {
    var x = document.getElementsByClassName("myCheck");
    for (var i = 0; i <= x.length - 1; i++) {
      x[i].checked = false;
    }
  };

  const setZoneTemperature = (e) => {
    console.log(e);
  };

  useEffect(() => {
    getAvailableLocations();
    displayZones();
  }, [layout]);

  return (
    <>
      <h4 style={{ textAlign: "center" }}>Heating</h4>
      <br />
      <h6>Set Zone</h6>
      {locations.map((item) => (
        <div key={item.id} style={{ display: "inline-block" }}>
          <input
            type="checkbox"
            className="myCheck"
            name={item}
            value={item}
            onChange={onChange}
          />
          &nbsp;
          <label for={item}>{item}</label>
          &nbsp; &nbsp;
        </div>
      ))}
      <br />
      <Button variant="dark" onClick={createNewZone}>
        New Zone
      </Button>
      <br />
      <br />
      <h6>Set Zone Temperature</h6>
      <Form>
        <Form.Group>
          <Form inline>
            <Form.Control
              as="select"
              className="my-1 mr-sm-2"
              id="selectBox1"
              custom
              onChange={(e) => setZoneTemperature(e.target.value)}
            >
              <option>Zone</option>
              {zones.map((item) => (
                <option key={item.zone} value={item.zone}>
                  {item.zone}
                </option>
              ))}
            </Form.Control>
            <Form.Control
              as="select"
              className="my-1 mr-sm-2"
              id="selectBox1"
              custom
              onChange={(e) => setZoneTemperature(e.target.value)}
            >
              <option>Period</option>
              {zones.map((item) => (
                <option key={item.zone} value={item.zone}>
                  {item.zone}
                </option>
              ))}
            </Form.Control>
            <Button size="ms" variant="dark" className="my-1">
              Submit
            </Button>
          </Form>
        </Form.Group>
      </Form>
    </>
  );
};

export default SHH;
