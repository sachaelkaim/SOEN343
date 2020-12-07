import React, { useEffect, useState, useContext } from "react";
import { Button, Form, DropdownButton, Dropdown } from "react-bootstrap";
import axios from "axios";
import { UserContext } from "../UserProvider";
import { LayoutContext } from "../LayoutProvider";

// SHH module
const SHH = () => {
  const { currentUser, setCurrentUser } = useContext(UserContext);
  const { layout, setLayout } = useContext(LayoutContext);
  const [location, setLocation] = useState([]);
  const [locations, setLocations] = useState([]);
  const [zones, setZones] = useState([]);
  const [zoneSelected, setZoneSelected] = useState("");
  const [periodSelected, setPeriodSelected] = useState("Period");
  const [temperatureSelected, setTemperatureSelected] = useState("");
  const [season, setSeason] = useState("Season");
  const [seasonTemperature, setSeasonTemperature] = useState("");
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

  const setSelectedZone = (e) => {
    setZoneSelected(e);
  };

  const setSelectedPeriod = (e) => {
    setPeriodSelected(e);
  };

  const setSelectedTemperature = (e) => {
    setTemperatureSelected(e);
  };

  // change zone temperature
  const handleSetTemperature = async (e) => {
    if (currentUser == undefined || periodSelected === "Period") {
      return console.log("cannot process");
    }
    e.preventDefault(); // prevent refresh on submit
    const response = await axios
      .post(
        `http://localhost:8080/api/heating/setZoneTemperature`,
        {
          userPrivilege: currentUser.privilege,
          zone: zoneSelected,
          period: periodSelected,
          temperature: temperatureSelected,
        },
        {
          data: {
            userPrivilege: currentUser.privilege,
            zone: zoneSelected,
            period: periodSelected,
            temperature: temperatureSelected,
          },
        }
      )
      .catch((err) => console.log("Error", err));
  };

  // change zone temperature
  const handleSeasonTemperature = async (e) => {
    if (currentUser === undefined || season === "Season") {
      return console.log("cannot process");
    }
    e.preventDefault(); // prevent refresh on submit
    const response = await axios
      .post(
        `http://localhost:8080/api/heating/setSeasonTemperature`,
        {
          userPrivilege: currentUser.privilege,
          seasons: season,
          temperature: seasonTemperature,
        },
        {
          data: {
            userPrivilege: currentUser.privilege,
            seasons: season,
            temperature: seasonTemperature,
          },
        }
      )
      .catch((err) => console.log("Error", err));
  };

  //update available checkboxes and hide if sim is off
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
              id="selectBox"
              custom
              onChange={(e) => setSelectedZone(e.target.value)}
            >
              <option>Zones</option>
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
              onChange={(e) => setSelectedPeriod(e.target.value)}
            >
              <option value={"Period"}>Period</option>
              <option value={0}>00:00 to 08:00</option>
              <option value={1}>08:00 to 16:00</option>
              <option value={2}>16:00 to 00:00</option>
            </Form.Control>
            <input
              name="temp"
              type="number"
              placeholder="0"
              style={{ width: "15%", height: "37px" }}
              onChange={(e) => setSelectedTemperature(e.target.value)}
            />
            &nbsp;
            <Button
              size="ms"
              variant="dark"
              className="my-1"
              onClick={handleSetTemperature}
            >
              Submit
            </Button>
          </Form>
        </Form.Group>
      </Form>
      <h6>Set Season Temperature</h6>
      <Form>
        <Form.Group>
          <Form inline>
            <Form.Control
              as="select"
              className="my-1 mr-sm-2"
              id="selectBox1"
              custom
              onChange={(e) => setSeason(e.target.value)}
            >
              <option value={"Season"}>Season</option>
              <option value={0}>Summer</option>
              <option value={1}>Winter</option>
            </Form.Control>
            <input
              name="temp"
              type="number"
              placeholder="0"
              style={{ width: "15%", height: "37px" }}
              onChange={(e) => setSeasonTemperature(e.target.value)}
            />
            &nbsp;
            <Button
              size="ms"
              variant="dark"
              className="my-1"
              onClick={handleSeasonTemperature}
            >
              Submit
            </Button>
          </Form>
        </Form.Group>
          </Form>
      <Form>
      <h6>Set Room Temperature</h6>
      <Form.Group>
          <Form inline>
            <Form.Control
               as="select"
               className="my-1 mr-sm-2"
               id="selectBox1"
               custom
               onChange={(e) => setLocation(e.target.value)}
             >
               <option>Select location</option>
               {layout.map((newlocation) => (
                 <option key={newlocation.id} value={newlocation.name}>
                   {newlocation.name}
                 </option>
               ))}
            </Form.Control>
            <input
              name="temp"
              type="number"
              placeholder="0"
              style={{ width: "15%", height: "37px" }}
              onChange={(e) => setSelectedTemperature(e.target.value)}
            />
            &nbsp;
            <Button
              size="ms"
              variant="dark"
              className="my-1"
              onClick={handleSeasonTemperature}
            >
              Submit
            </Button>
           
      </Form>
      </Form.Group>
      </Form>
    </>
  );
};

export default SHH;
