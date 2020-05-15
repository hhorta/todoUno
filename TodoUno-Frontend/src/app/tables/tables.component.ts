import { Component, OnInit } from '@angular/core';
import { ViewEncapsulation } from '@angular/core';
import { CategoryServices } from '../core/service/category.service';
import { Category } from '../core/models/category.model';
import { ProductServices } from '../core/service/product.service';
import { Product } from '../core/models/product.models';

@Component({
  selector: 'app-tables',
  templateUrl: './tables.component.html',
  encapsulation: ViewEncapsulation.None
})
export class TablesComponent implements OnInit {

  private categoria: Category = new Category();
  categorias: Category[];
  productos: Product[];
  errores: string[]=[];

  constructor(private allserviceCategotia: CategoryServices,
    private serviceProduct: ProductServices) { }

  ngOnInit() {
    this.ObtenerCategorias();
    this.ObtenerProductos();
  }

  ObtenerCategorias(){
    this.allserviceCategotia.getCategorias().subscribe(
      categorias => {this.categorias = categorias},
      errores => {this.errores = errores.status}
 
    );
  }

  ObtenerProductos(){
    this.serviceProduct.getProducts().subscribe(
      productos => {this.productos = productos},
      errores => {this.errores = errores.status}
 
    );
  }

}
