import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ReceiptModel } from '../model/receipt.model';

@Injectable({
  providedIn: 'root'
})
export class ReceiptService {

  baseUrl: string="http://localhost:3000/reciept";

  constructor(private http: HttpClient) { }

  getAllReciept():Observable<ReceiptModel[]>{
    return this.http.get<ReceiptModel[]>(this.baseUrl);
  }

  getReciptById(id:string):Observable<ReceiptModel>{
    return this.http.get<ReceiptModel>(this.baseUrl+"/"+id);
  }

  creatRecipt(receipt:ReceiptModel): Observable<ReceiptModel>{
    return this.http.post<ReceiptModel>(this.baseUrl, receipt);
  }

  deleteRecipt(id:string):Observable<any>{
return this.http.delete(this.baseUrl+"/"+id);
  }

   updateMoneyReceipt(id: string, moneyreciept: ReceiptModel): Observable<any> {
    return this.http.put(this.baseUrl + "update/" + id, moneyreciept);
  }

  // Filter receipts by policyholder, bankName, or id on the client side
  searchByPolicyHolderAndBankNameAndId(receipts: ReceiptModel[], searchTerm: string): ReceiptModel[] {
    const lowerCaseSearchTerm = searchTerm.toLowerCase();

    return receipts.filter(item =>
    (
      item.issuingOffice?.toLowerCase().includes(lowerCaseSearchTerm) ||
      item.bill?.policies.policyholder?.toLowerCase().includes(lowerCaseSearchTerm) ||
      item.bill?.policies.bankName?.toLowerCase().includes(lowerCaseSearchTerm) ||
      item.bill?.policies.id?.toString().includes(lowerCaseSearchTerm))
    );
  }
}
