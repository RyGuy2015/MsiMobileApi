import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Delivery from './delivery';
import Stop from './stop';
import DealerStop from './dealer-stop';
import UserExtras from './user-extras';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}delivery`} component={Delivery} />
      <ErrorBoundaryRoute path={`${match.url}dealer-stop`} component={DealerStop} />
      <ErrorBoundaryRoute path={`${match.url}user-extras`} component={UserExtras} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
