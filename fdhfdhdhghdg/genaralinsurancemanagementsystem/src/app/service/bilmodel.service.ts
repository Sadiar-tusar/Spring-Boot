import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { BillModel } from '../model/bill.model';
import { environment } from '../environment/environment';

@Injectable({
  providedIn: 'root'
})
export class BilmodelService {

  private baseUrl = environment.apiBaseUrl+'/firebill';

  constructor(  private http: HttpClient) { }

   viewAllBill(): Observable<any> {
    return this.http.get(this.baseUrl);
  }

  getAllBillForReciept(): Observable<BillModel[]> {
    return this.http.get<BillModel[]>(this.baseUrl)
      .pipe(
        catchError(this.handleError)
      );
  }

  createBill(bills: BillModel): Observable<BillModel> {
    return this.http.post<BillModel>(this.baseUrl, bills);
  }

  deleteBill(id: number): Observable<any> {
    return this.http.delete(this.baseUrl+"/"+ id);
  }

  // updateBill(bill: BillModel): Observable<BillModel> {
  //   return this.http.put<BillModel>(this.baseUrl + bill.id, bill);
  // }
  updateBill(id: number, bill:BillModel): Observable<any> {
    return this.http.put(this.baseUrl +"/"+id, bill);
  }

  getByBillId(id: number): Observable<BillModel> {
    return this.http.get<BillModel>(this.baseUrl+"/"+ id);
  }
  getBillNoById(id: number): Observable<BillModel> {
    return this.http.get<BillModel>(this.baseUrl+"/"+ id);
  }

  



  private handleError(error: any) {
    console.error('An error occurred:', error);
    return throwError(() => new Error('An error occurred while processing the request.'));
  }


}
