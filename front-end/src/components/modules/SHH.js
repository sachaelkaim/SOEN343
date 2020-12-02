import React, { useEffect, useState, useContext } from "react";
import { Button, Form, DropdownButton, Dropdown } from "react-bootstrap";
import axios from "axios";
import { UserContext } from "../UserProvider";

// SHH module
const SHH = () => {
  const { currentUser, setCurrentUser } = useContext(UserContext);
  const [locations, setLocations] = useState([]);
  const [checkBoxState, setCheckBoxState] = useState([]);
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
      e.preventDefault(); // prevent refresh on submit
      const response = await axios
      .post(`http://localhost:8080/api/heating/${checkBoxArr}`)
      .catch((err) => console.log("Error", err));
    if (response && response.data) getAvailableLocations();;
      
    };

  // checkbox onchange
  const onChange = (e) => {
    if (e.target.type === "checkbox" && !e.target.checked) {
      for (var i=checkBoxArr.length; i--; ) {
        if (checkBoxArr[i] === e.target.value) checkBoxArr.splice(i, 1);
     }
    } else {
      checkBoxArr.push(e.target.value);
    }
    console.log(checkBoxArr);
  };

  useEffect(() => {
    getAvailableLocations();
  }, []);

  return (
    <>
      <h4 style={{ textAlign: "center" }}>Heating</h4>
      <br />
      <h6>Set Zone</h6>
      <Form onSubmit={createNewZone}>
        <div></div>
        {["checkbox"].map((type) => (
          <div key={`inline-${type}`} className="mb-3">
            {locations.map((item) => (
              <div key={item.id}>
                <Form.Check
                  inline
                  label={item}
                  type={type}
                  value={item}
                  id={`inline-${type}-1`}
                  onChange={onChange}
                />
              </div>
            ))}
          </div>
        ))}
        <Button variant="dark" on type="submit">New Zone</Button>
      </Form>
    </>
  );
};

export default SHH;
