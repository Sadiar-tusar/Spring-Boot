import { CarBillModel } from "./carbil.model";

export class CarReceiptModel {
    id?: string;
    issuingOffice?: string;
    classOfInsurance?: string;
    date?: Date;
    modeOfPayment?: string;
    issuedAgainst?: string;

    carBill?: CarBillModel;
}