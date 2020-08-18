import { IUser } from 'app/shared/model/user.model';

export interface IUserExtras {
  id?: number;
  customerNumber1?: number;
  customerNumber2?: number;
  salesRepCode?: string;
  user?: IUser;
}

export const defaultValue: Readonly<IUserExtras> = {};
