import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { CarModel } from '../model/car.model';

@Injectable({
  providedIn: 'root'
})
export class CarService {

  baseUrl: string="http://localhost:3000/cars";

  constructor(private http :HttpClient) { }


// View all carpolicy
  viewAllCarPolicy(): Observable<any> {
    return this.http.get(this.baseUrl)
      .pipe(
        catchError(this.handleError) // Handle error globally
      );
  }

  // View all carpolicy with typing
  viewAllCarPolicyForBill(): Observable<CarModel[]> {
    return this.http.get<CarModel[]>(this.baseUrl)
      .pipe(
        catchError(this.handleError)
      );
  }

  // Create a new policy
  createCarPolicy(car: CarModel): Observable<any> {
    return this.http.post(this.baseUrl, car)
      .pipe(
        catchError(this.handleError)
      );
  }

  // Delete a Car policy by ID
  deleteCarPolicy(id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`)
      .pipe(
        catchError(this.handleError)
      );
  }

  // Update a Car policy by ID
  updateCarPolicy(id: string, car: CarModel): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, car)
      .pipe(
        catchError(this.handleError)
      );
  }

  // Get a policy by ID
  getByCarPolicyId(id: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`)
      .pipe(
        catchError(this.handleError)
      );
  }

  

  // Get all policies
  getAllCarPolicy(): Observable<CarModel[]> {
    return this.http.get<CarModel[]>(this.baseUrl);
  }

  // Error handling
  private handleError(error: any) {
    console.error('An error occurred:', error);
    return throwError(() => new Error('Something went wrong; please try again later.'));
  }

  getLastBillNo(): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/last-bill-no`);
  }

    // Search Car policies by policyholder
    policyholder(query: string): Observable<CarModel[]> {
      const searchUrl = `${this.baseUrl}?policyholder_like=${query}`;
      return this.http.get<CarModel[]>(searchUrl)
        .pipe(
          catchError(this.handleError)
        );
    }
  }