import {Component, OnInit} from '@angular/core';
import {AsyncPipe} from "@angular/common";
import {DataService} from "../../core/service/productos-service";
import {catchError, EMPTY, map, Observable, tap} from "rxjs";
import {Producto} from "../../interfaces/producto";

import {Router, RouterLink, RouterOutlet} from "@angular/router";
import {NavBarComponent} from "../../componentes/nav-bar/nav-bar.component";

import  {Location} from "@angular/common";
import { CookieService } from 'ngx-cookie-service';
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-productos',
   imports: [AsyncPipe, RouterLink, RouterOutlet, NavBarComponent, FormsModule],
  templateUrl: './productos.component.html',
  standalone: true,
  styleUrl: './productos.component.css'

})


export class ProductosComponent implements OnInit {

  public productosResultados$!: Observable<Producto[]>
  public errorMessage!: string;
  criterioOrdenamiento: string = "nombre";

  constructor(private service: DataService,private router: Router,private location: Location , private cookieService: CookieService) {

  }

   ngOnInit(): void {
      this.criterioOrdenamiento = this.cookieService.get('criterioOrdenamiento') || 'nombre';
      this.ordenar();
   }

   obtenerProductos(): Observable<Producto[]> {
      return this.service.obtenerProductos().pipe(
         catchError((error: string) => {
            this.errorMessage = error;
            return EMPTY;
         })
      );
   }

   ordenar(): void {
      const criterio = this.criterioOrdenamiento;
      console.log(criterio)
      this.productosResultados$ = this.obtenerProductos().pipe(
         tap(() => this.cookieService.set('criterioOrdenamiento', criterio)), // Save sorting criteria in cookie
         map(productos => productos.slice()), // Create a shallow copy of the array
         map(productos => {
            switch (criterio) {
               case 'nombre':
                  return productos.sort((a, b) => a.nombre.localeCompare(b.nombre));
               case 'precio':
                  return productos.sort((a, b) => a.precio - b.precio);
               default:
                  return productos;
            }
         })
      );
   }


  eliminarProducto(id_producto: number): void {
    this.service.eliminarProducto(id_producto).subscribe(
      response => {
        console.log('Successfully deleted product:', response);
         window.location.reload();
      },
      error => {
        console.error('Error deleting product:', error);
        // Handle errors here
      }
    );
  }



  agregarProducto(){
    this.router.navigate(['/productos/agregar']);
  }


  editarProducto(id_producto: number){
    this.router.navigate(['/productos/editar'],{ queryParams: { id: id_producto  } })
  }




}
