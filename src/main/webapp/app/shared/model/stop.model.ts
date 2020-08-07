import { IDelivery } from 'app/shared/model/delivery.model';

export interface IStop {
  id?: number;
  routeNumber?: number;
  stopNumber?: number;
  customerNumber1?: number;
  customerNumber2?: number;
  customerName?: string;
  customerAddress?: string;
  delivery?: IDelivery;
}

export const defaultValue: Readonly<IStop> = {};
