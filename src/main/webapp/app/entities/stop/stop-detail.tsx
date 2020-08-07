import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './stop.reducer';
import { IStop } from 'app/shared/model/stop.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IStopDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const StopDetail = (props: IStopDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { stopEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Stop [<b>{stopEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="routeNumber">Route Number</span>
          </dt>
          <dd>{stopEntity.routeNumber}</dd>
          <dt>
            <span id="stopNumber">Stop Number</span>
          </dt>
          <dd>{stopEntity.stopNumber}</dd>
          <dt>
            <span id="customerNumber1">Customer Number 1</span>
          </dt>
          <dd>{stopEntity.customerNumber1}</dd>
          <dt>
            <span id="customerNumber2">Customer Number 2</span>
          </dt>
          <dd>{stopEntity.customerNumber2}</dd>
          <dt>
            <span id="customerName">Customer Name</span>
          </dt>
          <dd>{stopEntity.customerName}</dd>
          <dt>
            <span id="customerAddress">Customer Address</span>
          </dt>
          <dd>{stopEntity.customerAddress}</dd>
          <dt>Delivery</dt>
          <dd>{stopEntity.delivery ? stopEntity.delivery.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/stop" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/stop/${stopEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ stop }: IRootState) => ({
  stopEntity: stop.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(StopDetail);
