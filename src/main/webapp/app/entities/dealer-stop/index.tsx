import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DealerStop from './dealer-stop';
import DealerStopDetail from './dealer-stop-detail';
import DealerStopUpdate from './dealer-stop-update';
import DealerStopDeleteDialog from './dealer-stop-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DealerStopUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DealerStopUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DealerStopDetail} />
      <ErrorBoundaryRoute path={match.url} component={DealerStop} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DealerStopDeleteDialog} />
  </>
);

export default Routes;
