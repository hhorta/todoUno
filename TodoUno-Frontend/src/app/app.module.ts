import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { FooterComponent } from './footer/footer.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { FormsComponent } from './forms/forms.component';
import { TablesComponent } from './tables/tables.component';



import { registerLocaleData } from '@angular/common';
import localeES from '@angular/common/locales/es';
import { FormsCategoryComponent } from './forms/forms-category/forms-category.component';
import { FormsProductsComponent } from './forms/forms-products/forms-products.component';
import { ModalProductoComponent } from './dashboard/modal-producto/modal-producto.component';

registerLocaleData(localeES, 'es');


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SidebarComponent,
    FooterComponent,
    DashboardComponent,
    FormsComponent,
    TablesComponent,
    FormsCategoryComponent,
    FormsProductsComponent,
    ModalProductoComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    FormsModule,
    NgbModule,
    HttpClientModule 
  ],
  entryComponents:[
    ModalProductoComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }