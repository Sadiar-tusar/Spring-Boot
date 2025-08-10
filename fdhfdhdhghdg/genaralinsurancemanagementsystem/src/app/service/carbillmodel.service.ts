import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { CarBillModel } from '../model/carbil.model';

@Injectable({
  providedIn: 'root'
})
export class CarbillmodelService {

baseUrl:string="http://localhost:3000/carbill";

  constructor(private http: HttpClient) { }

  viewAllCarBill(): Observable<any> {
      return this.http.get(this.baseUrl);
    }
  
    getAllCarBillForReciept(): Observable<CarBillModel[]> {
      return this.http.get<CarBillModel[]>(this.baseUrl)
        .pipe(
          catchError(this.handleError)
        );
    }
  
    createCarBill(carBills: CarBillModel): Observable<CarBillModel> {
      return this.http.post<CarBillModel>(this.baseUrl, carBills);
    }
  
    deleteCarBill(id: string): Observable<any> {
      return this.http.delete(this.baseUrl+"/"+ id);
    }
  
    // updateBill(bill: BillModel): Observable<BillModel> {
    //   return this.http.put<BillModel>(this.baseUrl + bill.id, bill);
    // }
    updateCarBill(id: string, carBill:CarBillModel): Observable<any> {
      return this.http.put(this.baseUrl +"/"+id, carBill);
    }
  
    getByCarBillId(id: string): Observable<CarBillModel> {
      return this.http.get<CarBillModel>(this.baseUrl+"/"+ id);
    }
  
    
  
  
  
    private handleError(error: any) {
      console.error('An error occurred:', error);
      return throwError(() => new Error('An error occurred while processing the request.'));
    }
}
