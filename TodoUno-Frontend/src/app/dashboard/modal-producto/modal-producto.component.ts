import { Component, OnInit, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-modal-producto',
  templateUrl: './modal-producto.component.html',
  styleUrls: ['./modal-producto.component.scss']
})
export class ModalProductoComponent implements OnInit {

  cantidad: number= 0;
  @Input() my_modal_title;
  @Input() my_modal_content;
  @Input() my_modal_price;
 
  constructor(public activeModal: NgbActiveModal) {this.cantidad;}
 
  ngOnInit() {
  } 

  aumentar(): number{
    this.cantidad = ++this.cantidad;
    return this.cantidad;
  }
  disminuir(): number{
    this.cantidad = --this.cantidad;
    if(this.cantidad<0){
      return this.cantidad=0;
    }else{
    return this.cantidad;
    }
  }
}
