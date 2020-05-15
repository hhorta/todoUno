import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Supplier } from '../models/supplier.models';
import { endpoint } from '../infrastructure/endpoint/endpoint';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SupplierService {

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})
  constructor(private http: HttpClient) { }

  getProveedor(): Observable<Supplier[]> {
    
    return this.http.get(endpoint.Supplier).pipe(
      map(response => response as Supplier[])
    );
  }
}
