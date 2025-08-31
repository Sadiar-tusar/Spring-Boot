import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environment/environment';
import { CompanyVoltAccount } from '../model/companyvolt.model';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
   private baseUrl = environment.apiBaseUrl+'/payment';
  constructor(private http: HttpClient) { }

  // Deposit money into user account
  deposit(id: number, amount: number): Observable<string> {
    const params = new HttpParams().set('amount', amount.toString());
    return this.http.post(`${this.baseUrl}/deposit/${id}`, null, { params, responseType: 'text' });
  }

 // Pay to company volt account
pay(id: number, amount: number): Observable<string> {
  const params = new HttpParams().set('amount', amount.toString());
  return this.http.post(`${this.baseUrl}/pay/${id}`, null, { 
    params,
    responseType: 'text'  // <-- Important fix
  });
}


  // Get user account balance
  getUserBalance(id: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/balance/${id}`);
  }

  // // Get company balance
  // getCompanyBalance(id: number): Observable<any> {
  //   return this.http.get<any>(`${this.baseUrl}/company-balance/${id}`);
  // }
  // Get company balance
  getCompanyBalance(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/company-balance`);
  }

     getAllCompanyDetails(): Observable<CompanyVoltAccount[]> {
    return this.http.get<CompanyVoltAccount[]>(`${this.baseUrl}/showcompanydetails`);
  }
}
