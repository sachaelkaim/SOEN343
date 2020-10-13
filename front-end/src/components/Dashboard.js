import React from "react";
import { Container, Row, Col } from "react-bootstrap";
import HouseLayout from "./HouseLayout";
import Simulation from "./Simulation";

// Dashboard contains the SHS/simulation profile/layout
const Dashboard = () => {
  return (
    <>
      <Container
        style={{
          border: "1px solid black",
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
                <h4
                  style={{
                    border: "1px solid black",
                    height: "30rem",
                    marginTop: "3rem",
                    textAlign: "center",
                  }}
                >
                  Modules
                </h4>
              </Col>
              <Col xs={12} md={12} lg={6}>
                <h4
                  style={{
                    border: "1px solid black",
                    height: "30rem",
                    marginTop: "3rem",
                  }}
                >
                 <HouseLayout />
                </h4>
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
                >
                  Console
                </h4>
              </Col>
            </Row>
          </Col>
        </Row>
      </Container>
    </>
  );
};

export default Dashboard;
