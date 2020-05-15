import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/core/models/product.models';
import { Category } from 'src/app/core/models/category.model';
import { Supplier } from 'src/app/core/models/supplier.models';
import { ProductServices } from 'src/app/core/service/product.service';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';
import { SupplierService } from 'src/app/core/service/supplier.service';
import { CategoryServices } from 'src/app/core/service/category.service';

@Component({
  selector: 'app-forms-products',
  templateUrl: './forms-products.component.html'
})
export class FormsProductsComponent implements OnInit {

  public categoria: Category = new Category();
  public producto: Product = new Product();
  public proveedor: Supplier = new Supplier();
  public proveedores: Supplier[];
  errores: string[]=[];
  public categorias: Category[];
  
  alert: any;
  constructor(private productosService: ProductServices,
    private categoriaService: CategoryServices,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private proveedorService: SupplierService) { this.ObtenerProveedor() ,this.ObtenerCategorias()}

  ngOnInit() {
    this.obtenerProducto()
  }

  createProduct(

  ): void {

    this.producto.supplierId = this.proveedor;
    this.producto.categoryId = this.categoria;
    this.productosService.create(JSON.stringify(this.producto))
      .subscribe(
        producto => {
          this.alert = producto
          console.log("insertar product", this.producto);
          console.log("insertar product", this.alert);
          this.router.navigate(['/tables']);
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Producto insertado satisfactoria',
            showConfirmButton: false,
            timer: 1500
          })
        },
        err => {

          console.error('Código del error desde el backend: ' + err.status);
          if (err.status == 500) {
            Swal.fire({
              icon: 'error',
              title: 'Error...',
              text: 'Error en insertar producto, el campo no puede ir vacio',
              footer: '<a href>Why do I have this issue?</a>'
            })
          }

        }
      );
  }

  ObtenerProveedor() {
    this.proveedorService.getProveedor().subscribe(
      proveedores => {
        this.proveedores = proveedores
        console.log("lista de proveedores" + proveedores)
      },
      errores => { this.errores = errores.status }

    );
  }

  ObtenerCategorias() {
    this.categoriaService.getCategorias().subscribe(
      categorias => {
        this.categorias = categorias
        console.log("lista de categoriaas" + categorias)
      },
      errores => { this.errores = errores.status }

    );
  }


  obtenerProducto(): void {
    this.activatedRoute.params.subscribe(params => {
      let id = params['id']
      if (id) {
        this.productosService.getProduct(id).subscribe((producto) => this.producto = producto)
      }
    })
  }


  files:any;
  filestring:any;
  getFiles(event) {
    this.files = event.target.files;
    var reader = new FileReader();
    reader.onload = this._handleReaderLoaded.bind(this);
    reader.readAsBinaryString(this.files[0]);
}

_handleReaderLoaded(readerEvt) {
    var binaryString = readerEvt.target.result;
    this.filestring = btoa(binaryString);  // Converting binary string data.
}
}
