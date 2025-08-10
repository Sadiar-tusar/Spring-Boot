import { BillModel } from "./bill.model";


export class ReceiptModel {
    id?: string;
    issuingOffice?: string;
    classOfInsurance?: string;
    date?: Date;
    modeOfPayment?: string;
    issuedAgainst?: string;

    bill?: BillModel;
}
