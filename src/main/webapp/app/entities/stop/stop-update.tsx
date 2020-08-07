import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDelivery } from 'app/shared/model/delivery.model';
import { getEntities as getDeliveries } from 'app/entities/delivery/delivery.reducer';
import { getEntity, updateEntity, createEntity, reset } from './stop.reducer';
import { IStop } from 'app/shared/model/stop.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IStopUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const StopUpdate = (props: IStopUpdateProps) => {
  const [deliveryId, setDeliveryId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { stopEntity, deliveries, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/stop');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getDeliveries();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...stopEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="mobileapiApp.stop.home.createOrEditLabel">Create or edit a Stop</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : stopEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="stop-id">ID</Label>
                  <AvInput id="stop-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="routeNumberLabel" for="stop-routeNumber">
                  Route Number
                </Label>
                <AvField id="stop-routeNumber" type="string" className="form-control" name="routeNumber" />
              </AvGroup>
              <AvGroup>
                <Label id="stopNumberLabel" for="stop-stopNumber">
                  Stop Number
                </Label>
                <AvField id="stop-stopNumber" type="string" className="form-control" name="stopNumber" />
              </AvGroup>
              <AvGroup>
                <Label id="customerNumber1Label" for="stop-customerNumber1">
                  Customer Number 1
                </Label>
                <AvField id="stop-customerNumber1" type="string" className="form-control" name="customerNumber1" />
              </AvGroup>
              <AvGroup>
                <Label id="customerNumber2Label" for="stop-customerNumber2">
                  Customer Number 2
                </Label>
                <AvField id="stop-customerNumber2" type="string" className="form-control" name="customerNumber2" />
              </AvGroup>
              <AvGroup>
                <Label id="customerNameLabel" for="stop-customerName">
                  Customer Name
                </Label>
                <AvField id="stop-customerName" type="text" name="customerName" />
              </AvGroup>
              <AvGroup>
                <Label id="customerAddressLabel" for="stop-customerAddress">
                  Customer Address
                </Label>
                <AvField id="stop-customerAddress" type="text" name="customerAddress" />
              </AvGroup>
              <AvGroup>
                <Label for="stop-delivery">Delivery</Label>
                <AvInput id="stop-delivery" type="select" className="form-control" name="delivery.id">
                  <option value="" key="0" />
                  {deliveries
                    ? deliveries.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/stop" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  deliveries: storeState.delivery.entities,
  stopEntity: storeState.stop.entity,
  loading: storeState.stop.loading,
  updating: storeState.stop.updating,
  updateSuccess: storeState.stop.updateSuccess,
});

const mapDispatchToProps = {
  getDeliveries,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(StopUpdate);
