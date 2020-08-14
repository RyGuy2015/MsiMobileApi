import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './dealer-stop.reducer';
import { IDealerStop } from 'app/shared/model/dealer-stop.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDealerStopDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DealerStopDetail = (props: IDealerStopDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dealerStopEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          DealerStop [<b>{dealerStopEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="routeNumber">Route Number</span>
          </dt>
          <dd>{dealerStopEntity.routeNumber}</dd>
          <dt>
            <span id="stopNumber">Stop Number</span>
          </dt>
          <dd>{dealerStopEntity.stopNumber}</dd>
          <dt>
            <span id="sequenceNumber">Sequence Number</span>
          </dt>
          <dd>{dealerStopEntity.sequenceNumber}</dd>
          <dt>
            <span id="customerNumber1">Customer Number 1</span>
          </dt>
          <dd>{dealerStopEntity.customerNumber1}</dd>
          <dt>
            <span id="customerNumber2">Customer Number 2</span>
          </dt>
          <dd>{dealerStopEntity.customerNumber2}</dd>
          <dt>
            <span id="customerName">Customer Name</span>
          </dt>
          <dd>{dealerStopEntity.customerName}</dd>
          <dt>
            <span id="customerAddress">Customer Address</span>
          </dt>
          <dd>{dealerStopEntity.customerAddress}</dd>
          <dt>
            <span id="status">Status</span>
          </dt>
          <dd>{dealerStopEntity.status}</dd>
          <dt>Delivery</dt>
          <dd>{dealerStopEntity.delivery ? dealerStopEntity.delivery.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/dealer-stop" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/dealer-stop/${dealerStopEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ dealerStop }: IRootState) => ({
  dealerStopEntity: dealerStop.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DealerStopDetail);
