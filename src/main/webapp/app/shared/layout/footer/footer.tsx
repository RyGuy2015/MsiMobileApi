import './footer.scss';

import React from 'react';

import { Col, Row } from 'reactstrap';

const Footer = props => (
  <div className="footer page-content">
    <Row>
      <Col md="12">
        <p>&copy; 2020 DPS, Inc.</p>
      </Col>
    </Row>
  </div>
);

export default Footer;
