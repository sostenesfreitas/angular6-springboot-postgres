import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class DataService {
   
  baseUrl = 'http://localhost:8080/phone'

  constructor(private http: HttpClient) { }

  getPhones() {
    return this.http.get(`${this.baseUrl}/`);
  }

  getPhone(id: number) {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createPhone(phone: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}` + `/create`, phone);
  }

  deletePhone(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  updatePhone(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }
}
