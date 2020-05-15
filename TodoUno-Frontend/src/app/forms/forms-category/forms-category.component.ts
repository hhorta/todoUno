import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { Router, ActivatedRoute } from '@angular/router';
import { CategoryServices } from 'src/app/core/service/category.service';
import { Category } from 'src/app/core/models/category.model';

@Component({
  selector: 'app-forms-category',
  templateUrl: './forms-category.component.html'
})
export class FormsCategoryComponent implements OnInit {
  public categoria: Category = new Category();
  public categorias: Category[];
  errores: string[] = [];
  alert: any;
  constructor(private categoriaService: CategoryServices,
    private router: Router,
    private activatedRoute: ActivatedRoute) {  }

  ngOnInit() {
    this.cargarCategoria()
  }
  create(

  ): void {
    this.categoriaService.create(this.categoria)
      .subscribe(
        category => {
          this.alert = category
          this.router.navigate(['/tables']);
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Categoría insertada satisfactoria',
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
              text: 'Error en insertar categoría, el campo no puede ir vacio',
              footer: '<a href>Why do I have this issue?</a>'
            })
          }

        }
      );
  }
 
  cargarCategoria(): any {
    this.activatedRoute.params.subscribe(params => {
      let id = params['id']
      if (id) {
        console.log("paramet" + id);
        this.categoriaService.getCategoria(id).subscribe((categoria) => {
          this.categoria = categoria
          return this.categoria
        })

      }
    })
  }

  update(
  ): void {
    this.categoriaService.update(this.categoria)
      .subscribe(categoria => {
        this.router.navigate(['/tables'])
        Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: 'Your work has been saved',
          showConfirmButton: false,
          timer: 1500
        })
      }

      )

  }
}
