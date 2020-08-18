import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './dealer-stop.reducer';
import { IDealerStop } from 'app/shared/model/dealer-stop.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDealerStopProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const DealerStop = (props: IDealerStopProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { dealerStopList, match, loading } = props;
  return (
    <div>
      <h2 id="dealer-stop-heading">
        Dealer Stops
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Dealer Stop
        </Link>
      </h2>
      <div className="table-responsive">
        {dealerStopList && dealerStopList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Route Number</th>
                <th>Stop Number</th>
                <th>Sequence Number</th>
                <th>Sales Rep Code</th>
                <th>Customer Number 1</th>
                <th>Customer Number 2</th>
                <th>Customer Name</th>
                <th>Customer Address</th>
                <th>Status</th>
                <th>Delivery</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {dealerStopList.map((dealerStop, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${dealerStop.id}`} color="link" size="sm">
                      {dealerStop.id}
                    </Button>
                  </td>
                  <td>{dealerStop.routeNumber}</td>
                  <td>{dealerStop.stopNumber}</td>
                  <td>{dealerStop.sequenceNumber}</td>
                  <td>{dealerStop.salesRepCode}</td>
                  <td>{dealerStop.customerNumber1}</td>
                  <td>{dealerStop.customerNumber2}</td>
                  <td>{dealerStop.customerName}</td>
                  <td>{dealerStop.customerAddress}</td>
                  <td>{dealerStop.status}</td>
                  <td>{dealerStop.delivery ? <Link to={`delivery/${dealerStop.delivery.id}`}>{dealerStop.delivery.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${dealerStop.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${dealerStop.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${dealerStop.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Dealer Stops found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ dealerStop }: IRootState) => ({
  dealerStopList: dealerStop.entities,
  loading: dealerStop.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DealerStop);
