import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './delivery.reducer';
import { IDelivery } from 'app/shared/model/delivery.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDeliveryProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Delivery = (props: IDeliveryProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { deliveryList, match, loading } = props;
  return (
    <div>
      <h2 id="delivery-heading">
        Deliveries
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Delivery
        </Link>
      </h2>
      <div className="table-responsive">
        {deliveryList && deliveryList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Delivery Number</th>
                <th>Delivery Status</th>
                <th>Delivery Warehouse</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {deliveryList.map((delivery, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${delivery.id}`} color="link" size="sm">
                      {delivery.id}
                    </Button>
                  </td>
                  <td>{delivery.deliveryNumber}</td>
                  <td>{delivery.deliveryStatus}</td>
                  <td>{delivery.deliveryWarehouse}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${delivery.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${delivery.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${delivery.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Deliveries found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ delivery }: IRootState) => ({
  deliveryList: delivery.entities,
  loading: delivery.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Delivery);
