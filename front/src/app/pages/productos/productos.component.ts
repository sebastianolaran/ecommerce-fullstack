import {Component, OnInit} from '@angular/core';
import {AsyncPipe} from "@angular/common";
import {DataService} from "../../core/service/data-service.service";
import {catchError, EMPTY, Observable} from "rxjs";
import {Producto} from "../../interfaces/producto";

import {RouterLink, RouterOutlet} from "@angular/router";
import {NavBarComponent} from "../../componentes/nav-bar/nav-bar.component";
import {HttpClient} from "@angular/common/http";

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

  constructor(private service: DataService) {

  }

  ngOnInit(): void {
    this.productosResultados$ = this.service.obtenerProductos().pipe(catchError((error: string) => {
      this.errorMessage = error;
      return EMPTY;
    }))
  }

}
