import {Component, OnInit} from '@angular/core';
import {AsyncPipe} from "@angular/common";
import {DataService} from "../../core/service/productos-service";
import {catchError, EMPTY, Observable} from "rxjs";
import {Producto} from "../../interfaces/producto";

import {Router, RouterLink, RouterOutlet} from "@angular/router";
import {NavBarComponent} from "../../componentes/nav-bar/nav-bar.component";

import  {Location} from "@angular/common";

@Component({
  selector: 'app-productos',
  imports: [AsyncPipe, RouterLink, RouterOutlet, NavBarComponent],
  templateUrl: './productos.component.html',
  standalone: true,
  styleUrl: './productos.component.css'

})


export class ProductosComponent implements OnInit {

  public productosResultados$!: Observable<Producto[]>
  public errorMessage!: string;

  constructor(private service: DataService,private router: Router,private location: Location) {

  }

  ngOnInit(): void {
    this.productosResultados$ = this.service.obtenerProductos().pipe(catchError((error: string) => {
      this.errorMessage = error;
      return EMPTY;
    }))
  }


  eliminarProducto(id_producto: number): void {
    this.service.eliminarProducto(id_producto).subscribe(
      response => {
        console.log('Successfully deleted product:', response);
        this.location.go(this.location.path()); // Recarga la página actual
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
