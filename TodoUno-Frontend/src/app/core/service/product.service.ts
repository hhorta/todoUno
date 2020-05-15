import { Injectable } from '@angular/core';
import { ApiBase } from '../infrastructure/api/api-base.service';
import { endpoint } from '../infrastructure/endpoint/endpoint';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Product } from '../models/product.models';


@Injectable({
  providedIn: 'root'
})
export class ProductServices {

 private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})
  constructor(private http: HttpClient) { }

  getProducts(): Observable<Product[]> {
    
    return this.http.get(endpoint.Product).pipe(
      map(response => response as Product[])
    );
  }

  getProduct(id): Observable<any>{
    return this.http.get<any>(`${endpoint.Product}/${id}`)
  }

  create(Product: any) : Observable<Product> {
    return this.http.post<Product>(endpoint.Product, Product, {headers: this.httpHeaders})
  }

  update(Product: Product): Observable<Product>{
    return this.http.put<Product>(endpoint.Product, Product, {headers: this.httpHeaders})
    
  }







}
