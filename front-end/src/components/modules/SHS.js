import React from "react";
import {Button} from 'react-bootstrap';

// SHS module
const SHS = () => {
// add/edit/remove users

  return (
    <>
      <Button variant="primary">Add</Button>{' '}
      <Button variant="info">Remove</Button>{' '}
      <Button variant="dark">Edit</Button>
    </>
  );
};

export default SHS;
