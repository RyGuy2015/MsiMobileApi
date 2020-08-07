import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './stop.reducer';
import { IStop } from 'app/shared/model/stop.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IStopProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Stop = (props: IStopProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { stopList, match, loading } = props;
  return (
    <div>
      <h2 id="stop-heading">
        Stops
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Stop
        </Link>
      </h2>
      <div className="table-responsive">
        {stopList && stopList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Route Number</th>
                <th>Stop Number</th>
                <th>Customer Number 1</th>
                <th>Customer Number 2</th>
                <th>Customer Name</th>
                <th>Customer Address</th>
                <th>Delivery</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {stopList.map((stop, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${stop.id}`} color="link" size="sm">
                      {stop.id}
                    </Button>
                  </td>
                  <td>{stop.routeNumber}</td>
                  <td>{stop.stopNumber}</td>
                  <td>{stop.customerNumber1}</td>
                  <td>{stop.customerNumber2}</td>
                  <td>{stop.customerName}</td>
                  <td>{stop.customerAddress}</td>
                  <td>{stop.delivery ? <Link to={`delivery/${stop.delivery.id}`}>{stop.delivery.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${stop.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${stop.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${stop.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Stops found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ stop }: IRootState) => ({
  stopList: stop.entities,
  loading: stop.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Stop);
