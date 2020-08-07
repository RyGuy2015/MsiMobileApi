import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Stop from './stop';
import StopDetail from './stop-detail';
import StopUpdate from './stop-update';
import StopDeleteDialog from './stop-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={StopUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={StopUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={StopDetail} />
      <ErrorBoundaryRoute path={match.url} component={Stop} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={StopDeleteDialog} />
  </>
);

export default Routes;
