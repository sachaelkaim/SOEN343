import React, { useContext } from "react";
import { UserContext } from "../UserProvider";
import { LayoutContext } from "../LayoutProvider";
import axios from "axios";
import BootstrapSwitchButton from "bootstrap-switch-button-react";
import {
    Button,
    Form
} from "react-bootstrap";

// SHC module
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
      <Form>
              <Form.Group>
                <Form inline>
                  <Form.Control
                    as="select"
                    className="my-1 mr-sm-2"
                    id="selectBox1"
                    custom
                  >
                    <option>Select location</option>
                    {layout.map((newlocation) => (
                      <option key={newlocation.id} value={newlocation.name}>
                        {newlocation.name}
                      </option>
                    ))}
                  </Form.Control>
                  <Button
                  size="ms"
                    type="submit"
                    variant="dark"
                    className="my-1"
                  >
                    Light
                  </Button>
                  &nbsp;
                  <Button
                    type="submit"
                    variant="dark"
                    className="my-1"
                  >
                    Door
                  </Button>
                  &nbsp;
                  <Button
                    type="submit"
                    variant="dark"
                    className="my-1"
                  >
                    Window
                  </Button>
                </Form>
              </Form.Group>
            </Form>
    </>
  );
};

export default SHC;