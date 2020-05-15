import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CategoryServices } from '../core/service/category.service';
import { Category } from '../core/models/category.model';
import Swal from 'sweetalert2';
import { ProductServices } from '../core/service/product.service';
import { Product } from '../core/models/product.models';
import { Supplier } from '../core/models/supplier.models';
import { TablesComponent } from '../tables/tables.component';
import { SupplierService } from '../core/service/supplier.service';

@Component({
  selector: 'app-forms',
  templateUrl: './forms.component.html',
  styleUrls: ['./forms.component.scss']
})
export class FormsComponent implements OnInit {

  public categoria: Category = new Category();

  errores: string[]=[];
  public producto: Product = new Product();

  public proveedor: Supplier = new Supplier();
  public proveedores: Supplier[];
  public obj: [];
  alert: any;

  constructor() { }


  ngOnInit() {}


  

  
 
  

}
