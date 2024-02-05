import {Component, OnInit} from '@angular/core';
import {Router, RouterOutlet} from "@angular/router";
import {AsyncPipe, Location} from "@angular/common";
import {NavBarComponent} from "../../componentes/nav-bar/nav-bar.component";
import {catchError, EMPTY, Observable} from "rxjs";

import {OrdenService} from "../../core/service/orden.service";
import {Orden} from "../../interfaces/orden";

@Component({
  selector: 'app-ordenes',
  standalone: true,
  imports: [
    RouterOutlet,
    AsyncPipe,
    NavBarComponent
  ],
  templateUrl: './ordenes.component.html',
  styleUrl: './ordenes.component.css'
})
export class OrdenesComponent implements OnInit {


  public ordenesResultados!: Observable<Orden[]>
  public errorMessage!: string;
  mostrarDetallesFlag: boolean = false;
  ordenSeleccionada: number | null = null; // ID de la orden seleccionada


  constructor(private service: OrdenService, private router: Router, private location: Location) {

  }

  ngOnInit(): void {
    this.ordenesResultados = this.service.obtenerOrdenes().pipe(catchError((error: string) => {
      this.errorMessage = error;
      return EMPTY;
    }))
  }


  obtenerArrayIdsProductos(cadenaIds: string): number[] {
    try {
      const arrayStrings = cadenaIds.replace('[', '').replace(']', '').split(',');
      return arrayStrings.map(str => +str);
    } catch (error) {
      console.error('Error al convertir la cadena a un array de números', error);
      return [];
    }
  }

  borrarOrden(id_orden: number) {
    this.service.eliminarOrden(id_orden.toString()).subscribe(
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

  verOrden(idOrden: number) {
    // Puedes cargar los detalles de la orden desde tu servicio o realizar otras acciones necesarias
    this.ordenSeleccionada = idOrden;
    this.mostrarDetallesFlag = true;
  }

  trackById(index: number, item: any): number {
    return item.id_orden;
  }

}
