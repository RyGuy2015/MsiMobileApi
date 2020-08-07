import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './delivery.reducer';
import { IDelivery } from 'app/shared/model/delivery.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDeliveryUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DeliveryUpdate = (props: IDeliveryUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { deliveryEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/delivery');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...deliveryEntity,
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
          <h2 id="mobileapiApp.delivery.home.createOrEditLabel">Create or edit a Delivery</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : deliveryEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="delivery-id">ID</Label>
                  <AvInput id="delivery-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="deliveryNumberLabel" for="delivery-deliveryNumber">
                  Delivery Number
                </Label>
                <AvField id="delivery-deliveryNumber" type="text" name="deliveryNumber" />
              </AvGroup>
              <AvGroup>
                <Label id="deliveryStatusLabel" for="delivery-deliveryStatus">
                  Delivery Status
                </Label>
                <AvField id="delivery-deliveryStatus" type="text" name="deliveryStatus" />
              </AvGroup>
              <AvGroup>
                <Label id="deliveryWarehouseLabel" for="delivery-deliveryWarehouse">
                  Delivery Warehouse
                </Label>
                <AvField id="delivery-deliveryWarehouse" type="text" name="deliveryWarehouse" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/delivery" replace color="info">
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
  deliveryEntity: storeState.delivery.entity,
  loading: storeState.delivery.loading,
  updating: storeState.delivery.updating,
  updateSuccess: storeState.delivery.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DeliveryUpdate);
