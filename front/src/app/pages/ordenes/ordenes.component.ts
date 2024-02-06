import {Component, OnInit} from '@angular/core';
import {Router, RouterOutlet} from "@angular/router";
import {AsyncPipe, Location} from "@angular/common";
import {NavBarComponent} from "../../componentes/nav-bar/nav-bar.component";
import {catchError, EMPTY, Observable} from "rxjs";

import {OrdenService} from "../../core/service/orden.service";
import {Orden} from "../../interfaces/orden";
import {ProductoConCantidad} from "../../interfaces/producto";

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
   ordenSeleccionada: number | undefined;
   productosDeOrden: ProductoConCantidad[] = [];


   constructor(private service: OrdenService, private router: Router, private location: Location) {

   }

   ngOnInit(): void {
      this.ordenesResultados = this.service.obtenerOrdenes().pipe(catchError((error: string) => {
         this.errorMessage = error;
         return EMPTY;
      }))
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

   verOrden(idOrden: number): void {
      if (this.mostrarDetallesFlag && this.ordenSeleccionada == idOrden) {
         this.mostrarDetallesFlag = false;
      } else {
         this.mostrarDetallesFlag = true;

         this.ordenSeleccionada = idOrden;

         // Llama a tu servicio para obtener los productos de la orden seleccionada
         this.obtenerProductos(idOrden);
      }
   }

   obtenerProductos(idOrden: number): void {
      if (this.componenteEstaActivo()) {
         this.service.obtenerProductos(idOrden).subscribe(
            productos => {
               this.productosDeOrden = productos;
               console.log('Productos obtenidos en el componente:', productos);
            },
            error => {
               console.error('Error al obtener productos en el componente:', error);
            }
         );
      }
   }


   private componenteEstaActivo(): boolean {
      // Lógica para verificar si el componente aún está activo
      return true; // O implementa la lógica según tus necesidades
   }

   agregarOrden() {
      this.router.navigate(['ordenes/agregar'])
   }
}
