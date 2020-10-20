import React from "react";
import { Container, Row, Col, Tab, Tabs } from "react-bootstrap";
import HouseLayout from "./HouseLayout";
import Simulation from "./Simulation";
import SHS from "./modules/SHS";
import AllUsersProvider from "./AllUsersProvider";

// Dashboard contains the SHS/simulation profile/layout
const Dashboard = () => {
  return (
    <>
    <AllUsersProvider>
      <Container
        style={{
          maxWidth: "90%",
          marginTop: "2%"
        }}
      >
        <Row>
          <Col xs={12} md={12} lg={3}>
            <Simulation />
          </Col>
          <Col>
            <Row>
              <Col xs={12} md={12} lg={6}>
                <div
                  style={{
                    border: "1px solid black",
                    height: "30rem",
                    marginTop: "3rem",
                  }}
                >
                  <Tabs defaultActiveKey="profile" style={{fontWeight:"600"}}>
  <Tab eventKey="SHS" title="SHS">
  <SHS />
  </Tab>
  <Tab eventKey="SHC" title="SHC">

  </Tab>
  <Tab eventKey="SHP" title="SHP">

  </Tab>
  <Tab eventKey="SHH" title="SHH">

  </Tab>
  </Tabs>
 
                
                </div>
              </Col>
              <Col xs={12} md={12} lg={6} >
                <div
                  style={{
                    border: "1px solid black",
                    height: "30rem",
                    marginTop: "3rem",
                    overflowY: "scroll"
                  }}
                >
                  <HouseLayout />
                </div>
              </Col>
            </Row>
            <Row>
              <Col xs={12} md={12} lg={12}>
                <h4
                  style={{
                    border: "1px solid black",
                    height: "11rem",
                    marginTop: "1rem",
                  }}
                ></h4>
              </Col>
            </Row>
          </Col>
        </Row>
      </Container>
      </AllUsersProvider>
    </>
  );
};

export default Dashboard;