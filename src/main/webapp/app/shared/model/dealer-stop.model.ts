import { IDelivery } from 'app/shared/model/delivery.model';

export interface IDealerStop {
  id?: number;
  routeNumber?: number;
  stopNumber?: number;
  sequenceNumber?: number;
  salesRepCode?: string;
  customerNumber1?: number;
  customerNumber2?: number;
  customerName?: string;
  customerAddress?: string;
  status?: string;
  delivery?: IDelivery;
}

export const defaultValue: Readonly<IDealerStop> = {};
