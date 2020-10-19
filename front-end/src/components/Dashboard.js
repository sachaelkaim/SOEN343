import React from "react";
import { Container, Row, Col } from "react-bootstrap";
import HouseLayout from "./HouseLayout";
import Simulation from "./Simulation";
import SHS from "./modules/SHS";

// Dashboard contains the SHS/simulation profile/layout
const Dashboard = () => {
  return (
    <>
      <Container
        style={{
          borderRadius: "0.5rem",
          maxWidth: "90%",
          marginTop: "2%",
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
                  <SHS />
                </div>
              </Col>
              <Col xs={12} md={12} lg={6}>
                <div
                  style={{
                    border: "1px solid black",
                    height: "30rem",
                    marginTop: "3rem",
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
    </>
  );
};

export default Dashboard;
