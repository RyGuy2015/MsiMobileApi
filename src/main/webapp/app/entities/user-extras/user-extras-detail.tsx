import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './user-extras.reducer';
import { IUserExtras } from 'app/shared/model/user-extras.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IUserExtrasDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const UserExtrasDetail = (props: IUserExtrasDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { userExtrasEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          UserExtras [<b>{userExtrasEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="customerNumber1">Customer Number 1</span>
          </dt>
          <dd>{userExtrasEntity.customerNumber1}</dd>
          <dt>
            <span id="customerNumber2">Customer Number 2</span>
          </dt>
          <dd>{userExtrasEntity.customerNumber2}</dd>
          <dt>
            <span id="salesRepCode">Sales Rep Code</span>
          </dt>
          <dd>{userExtrasEntity.salesRepCode}</dd>
          <dt>User</dt>
          <dd>{userExtrasEntity.user ? userExtrasEntity.user.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/user-extras" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/user-extras/${userExtrasEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ userExtras }: IRootState) => ({
  userExtrasEntity: userExtras.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(UserExtrasDetail);
