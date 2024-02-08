import {Component, OnInit} from '@angular/core';
import {Categorias} from "../../interfaces/categorias";
import {FormBuilder, FormsModule, Validators} from "@angular/forms";
import {DataService} from "../../core/service/productos-service";
import {Producto, ProductoConCantidad} from "../../interfaces/producto";
import {ActivatedRoute, Router} from "@angular/router";
import {catchError, EMPTY, Observable} from "rxjs";

@Component({
   selector: 'app-agregar-orden',
   standalone: true,
   imports: [
      FormsModule
   ],
   templateUrl: './agregar-orden.component.html',
   styleUrl: './agregar-orden.component.css'
})
export class AgregarOrdenComponent {

   public productosResultados$!: Observable<Producto[]>
   productoSeleccionado: string = '';
   cantidadSeleccionada: number = 0;
   opcionesEnum: string[] = ['Producto 1', 'Producto 2', 'Producto 3'];
   productosAgregados: ProductoConCantidad[] = [];


   constructor(private service: DataService, private router: Router) {

   }


   ngOnInit(): void {
      this.productosResultados = this.service.obtenerProductos().pipe(catchError((error: string) => {
         return EMPTY;
      }))
   }

   agregarProducto(): void {
      if (this.productoSeleccionado && this.cantidadSeleccionada > 0) {
         //Busco producto con nombre
         const producto = this.service.obtenerProducto()
         const nuevoProducto: ProductoConCantidad = {
            nombre: this.productoSeleccionado,
            cantidad: this.cantidadSeleccionada
         }; // No es necesario convertir a ProductoConCantidad
         this.productosAgregados.push(nuevoProducto);
         // Limpiar campos después de agregar el producto si es necesario
         this.productoSeleccionado = '';
         this.cantidadSeleccionada = 0;
      }
   }

   puedoEnviar(): boolean {
      return this.productosAgregados.length > 0;
   }

   enviarFormulario(): void {
      console.log('Productos agregados:', this.productosAgregados);
      this.productosAgregados = [];
   }

}
