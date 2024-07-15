import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { Observable } from 'rxjs';
import { Payment } from '../model/payment.model';
import { Student } from '../model/student.model';

@Injectable({
  providedIn: 'root'
})
export class StduentsService {

  constructor(private http : HttpClient) { }

  public getAllPaymnts() : Observable <Array<Payment>> {
    return this.http.get<Array<Payment>>(`${environment.backendHost}/payments`);
  }
  public getStudents() : Observable <Array<Student>> {
    return this.http.get<Array<Student>>(`${environment.backendHost}/students`);
  }
  public getStudentPayments(code : string) : Observable <Array<Payment>> {
    return this.http.get<Array<Payment>>(`${environment.backendHost}/students/${code}/payments`);
  }
  public savePayment(formData : FormData) : Observable <Payment> {
    return this.http.post<Payment>(`${environment.backendHost}/payments`,formData);
  }

  public getPaymentDetails(paymentId: number)  {
    return this.http.get(`${environment.backendHost}/paymentFile/${paymentId}`,{responseType :'blob'});
  }
}
