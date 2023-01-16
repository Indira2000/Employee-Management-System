import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from './employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
 
  private  URL= "http://localhost:8085/api/v1/employees";
  
  constructor(private httpClient:HttpClient){ }
  
  getAllEmployee(): Observable<Employee[]>{
    return this.httpClient.get<Employee[]>(`${this.URL}`);
  }

  createEmployee(employee:Employee):Observable<object>{
    return this.httpClient.post(`${this.URL}`,employee);
  }

  getEmployeeById(id: number): Observable<Employee>{
    return this.httpClient.get<Employee>(`${this.URL}/${id}`);
  }

  updateEmployee(id: number, employee: Employee): Observable<Object>{
    return this.httpClient.put(`${this.URL}/${id}`, employee);
  }

  deleteEmployee(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.URL}/${id}`);
  }
}