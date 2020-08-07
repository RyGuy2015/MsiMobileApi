import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IStop, defaultValue } from 'app/shared/model/stop.model';

export const ACTION_TYPES = {
  FETCH_STOP_LIST: 'stop/FETCH_STOP_LIST',
  FETCH_STOP: 'stop/FETCH_STOP',
  CREATE_STOP: 'stop/CREATE_STOP',
  UPDATE_STOP: 'stop/UPDATE_STOP',
  DELETE_STOP: 'stop/DELETE_STOP',
  RESET: 'stop/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IStop>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type StopState = Readonly<typeof initialState>;

// Reducer

export default (state: StopState = initialState, action): StopState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_STOP_LIST):
    case REQUEST(ACTION_TYPES.FETCH_STOP):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_STOP):
    case REQUEST(ACTION_TYPES.UPDATE_STOP):
    case REQUEST(ACTION_TYPES.DELETE_STOP):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_STOP_LIST):
    case FAILURE(ACTION_TYPES.FETCH_STOP):
    case FAILURE(ACTION_TYPES.CREATE_STOP):
    case FAILURE(ACTION_TYPES.UPDATE_STOP):
    case FAILURE(ACTION_TYPES.DELETE_STOP):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_STOP_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_STOP):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_STOP):
    case SUCCESS(ACTION_TYPES.UPDATE_STOP):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_STOP):
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

const apiUrl = 'api/stops';

// Actions

export const getEntities: ICrudGetAllAction<IStop> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_STOP_LIST,
  payload: axios.get<IStop>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IStop> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_STOP,
    payload: axios.get<IStop>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IStop> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_STOP,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IStop> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_STOP,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IStop> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_STOP,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
