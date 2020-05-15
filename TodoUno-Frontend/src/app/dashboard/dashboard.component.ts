import { Component, OnInit } from '@angular/core';
import { ViewEncapsulation } from '@angular/core';
import { TablesComponent } from '../tables/tables.component';
import { ProductServices } from '../core/service/product.service';
import { Product } from '../core/models/product.models';
import { CategoryServices } from '../core/service/category.service';
import { Category } from '../core/models/category.model';
import { NgbModalOptions, ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalProductoComponent } from './modal-producto/modal-producto.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['../app.component.scss', './dashboard.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class DashboardComponent implements OnInit {
  closeResult: string;
  modalOptions: NgbModalOptions;
  producto: Product = new Product();
  categorias: Category[];
  productos: Product[];
  errores: string[] = [];
  buttonDisabled= true;
  alert: any[] = [];
  constructor(private serviceProduct: ProductServices, private allserviceCategotia: CategoryServices,
     private modalService: NgbModal) {
      this.buttonDisabled;

    this.modalOptions = {
      backdrop: 'static',
      backdropClass: 'customBackdrop'
    }
  }
  ngOnInit() {
    this.ObtenerProductos();
    this.ObtenerCategorias();
  }

  ObtenerProductos() {
    this.serviceProduct.getProducts().subscribe(
      productos => { 
        this.productos = productos
        if(this.producto.existence<=0){
          this.buttonDisabled = true;
        }
       },
      errores => { this.errores = errores.status }

    );
  }

  ObtenerCategorias() {
    this.allserviceCategotia.getCategorias().subscribe(
      categorias => { this.categorias = categorias },
      errores => { this.errores = errores.status }

    );
  }
  open(content) {
    this.modalService.open(content, this.modalOptions).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }
  openmodal(id: number) {
    console.log("id" + id)
    this.serviceProduct.getProduct(id).subscribe(producto => {
      this.producto = producto
      console.log("produc", this.producto)
      const modalRef = this.modalService.open(ModalProductoComponent);
      modalRef.componentInstance.my_modal_title = this.producto.name;
      modalRef.componentInstance.my_modal_content =  this.producto.description;
      modalRef.componentInstance.my_modal_price =  this.producto.priceUnit;
    })

  }

}
