import { Injectable } from '@angular/core';
import { ApiBase } from '../infrastructure/api/api-base.service';
import { endpoint } from '../infrastructure/endpoint/endpoint';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Category } from '../models/category.model';


@Injectable({
  providedIn: 'root'
})
export class CategoryServices {

 private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})
  constructor(private http: HttpClient) { }

  getCategorias(): Observable<Category[]> {
    
    return this.http.get(endpoint.Category).pipe(
      map(response => response as Category[])
    );
  }

  getCategoria(id): Observable<Category>{
    return this.http.get<Category>(`${endpoint.Category}/${id}`)
  }

  create(category: Category) : Observable<Category> {
    return this.http.post<Category>(endpoint.Category, category, {headers: this.httpHeaders})
  }

  update(category: Category): Observable<Category>{
    return this.http.put<Category>(endpoint.Category, category, {headers: this.httpHeaders})
    
  }







}
