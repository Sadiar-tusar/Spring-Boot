import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CarReceiptModel } from '../model/carreceipt.model';

@Injectable({
  providedIn: 'root'
})
export class CarreceiptService {

  baseUrl: string="http://localhost:3000/carreceipt"

  constructor(private http: HttpClient) { }

  getAllCarReciept():Observable<CarReceiptModel[]>{
      return this.http.get<CarReceiptModel[]>(this.baseUrl);
    }
  
    getCarReciptById(id:string):Observable<CarReceiptModel>{
      return this.http.get<CarReceiptModel>(this.baseUrl+"/"+id);
    }
  
    creatCarRecipt(carReceipt:CarReceiptModel): Observable<CarReceiptModel>{
      return this.http.post<CarReceiptModel>(this.baseUrl, carReceipt);
    }
  
    deleteCarRecipt(id:string):Observable<any>{
  return this.http.delete(this.baseUrl+"/"+id);
    }
  
     updateMoneyReceipt(id: string, moneyreciept: CarReceiptModel): Observable<any> {
      return this.http.put(this.baseUrl + "update/" + id, moneyreciept);
    }
  
    // Filter receipts by policyholder, bankName, or id on the client side
    searchByPolicyHolderAndBankNameAndId(receipts: CarReceiptModel[], searchTerm: string): CarReceiptModel[] {
      const lowerCaseSearchTerm = searchTerm.toLowerCase();
  
      return receipts.filter(item =>
      (
        item.issuingOffice?.toLowerCase().includes(lowerCaseSearchTerm) ||
        item.carBill?.cars.policyholder?.toLowerCase().includes(lowerCaseSearchTerm) ||
        item.carBill?.cars.bankName?.toLowerCase().includes(lowerCaseSearchTerm) ||
        item.carBill?.cars.id?.toString().includes(lowerCaseSearchTerm))
      );
    }
}
