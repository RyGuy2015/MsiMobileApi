import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { getEntity, updateEntity, createEntity, reset } from './user-extras.reducer';
import { IUserExtras } from 'app/shared/model/user-extras.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IUserExtrasUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const UserExtrasUpdate = (props: IUserExtrasUpdateProps) => {
  const [userId, setUserId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { userExtrasEntity, users, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/user-extras');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getUsers();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...userExtrasEntity,
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
          <h2 id="mobileapiApp.userExtras.home.createOrEditLabel">Create or edit a UserExtras</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : userExtrasEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="user-extras-id">ID</Label>
                  <AvInput id="user-extras-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="customerNumber1Label" for="user-extras-customerNumber1">
                  Customer Number 1
                </Label>
                <AvField id="user-extras-customerNumber1" type="string" className="form-control" name="customerNumber1" />
              </AvGroup>
              <AvGroup>
                <Label id="customerNumber2Label" for="user-extras-customerNumber2">
                  Customer Number 2
                </Label>
                <AvField id="user-extras-customerNumber2" type="string" className="form-control" name="customerNumber2" />
              </AvGroup>
              <AvGroup>
                <Label id="salesRepCodeLabel" for="user-extras-salesRepCode">
                  Sales Rep Code
                </Label>
                <AvField id="user-extras-salesRepCode" type="text" name="salesRepCode" />
              </AvGroup>
              <AvGroup>
                <Label for="user-extras-user">User</Label>
                <AvInput id="user-extras-user" type="select" className="form-control" name="user.id">
                  <option value="" key="0" />
                  {users
                    ? users.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.login}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/user-extras" replace color="info">
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
  users: storeState.userManagement.users,
  userExtrasEntity: storeState.userExtras.entity,
  loading: storeState.userExtras.loading,
  updating: storeState.userExtras.updating,
  updateSuccess: storeState.userExtras.updateSuccess,
});

const mapDispatchToProps = {
  getUsers,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(UserExtrasUpdate);
