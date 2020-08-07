import { IStop } from 'app/shared/model/stop.model';

export interface IDelivery {
  id?: number;
  deliveryNumber?: string;
  deliveryStatus?: string;
  deliveryWarehouse?: string;
  stops?: IStop[];
}

export const defaultValue: Readonly<IDelivery> = {};
