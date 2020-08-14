import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDealerStop, defaultValue } from 'app/shared/model/dealer-stop.model';

export const ACTION_TYPES = {
  FETCH_DEALERSTOP_LIST: 'dealerStop/FETCH_DEALERSTOP_LIST',
  FETCH_DEALERSTOP: 'dealerStop/FETCH_DEALERSTOP',
  CREATE_DEALERSTOP: 'dealerStop/CREATE_DEALERSTOP',
  UPDATE_DEALERSTOP: 'dealerStop/UPDATE_DEALERSTOP',
  DELETE_DEALERSTOP: 'dealerStop/DELETE_DEALERSTOP',
  RESET: 'dealerStop/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDealerStop>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type DealerStopState = Readonly<typeof initialState>;

// Reducer

export default (state: DealerStopState = initialState, action): DealerStopState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DEALERSTOP_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DEALERSTOP):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_DEALERSTOP):
    case REQUEST(ACTION_TYPES.UPDATE_DEALERSTOP):
    case REQUEST(ACTION_TYPES.DELETE_DEALERSTOP):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_DEALERSTOP_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DEALERSTOP):
    case FAILURE(ACTION_TYPES.CREATE_DEALERSTOP):
    case FAILURE(ACTION_TYPES.UPDATE_DEALERSTOP):
    case FAILURE(ACTION_TYPES.DELETE_DEALERSTOP):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_DEALERSTOP_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_DEALERSTOP):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_DEALERSTOP):
    case SUCCESS(ACTION_TYPES.UPDATE_DEALERSTOP):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_DEALERSTOP):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/dealer-stops';

// Actions

export const getEntities: ICrudGetAllAction<IDealerStop> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_DEALERSTOP_LIST,
  payload: axios.get<IDealerStop>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IDealerStop> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DEALERSTOP,
    payload: axios.get<IDealerStop>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IDealerStop> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DEALERSTOP,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDealerStop> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DEALERSTOP,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDealerStop> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DEALERSTOP,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
