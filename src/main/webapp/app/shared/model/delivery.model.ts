import { IDealerStop } from 'app/shared/model/dealer-stop.model';

export interface IDelivery {
  id?: number;
  deliveryNumber?: string;
  deliveryStatus?: string;
  deliveryWarehouse?: string;
  dealerstops?: IDealerStop[];
}

export const defaultValue: Readonly<IDelivery> = {};
