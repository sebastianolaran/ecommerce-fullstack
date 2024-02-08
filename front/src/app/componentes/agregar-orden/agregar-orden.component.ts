import {Component, OnInit, SimpleChanges} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {DataService} from "../../core/service/productos-service";
import {Producto, ProductoConCantidad} from "../../interfaces/producto";
import {catchError, EMPTY, Observable} from "rxjs";
import {AsyncPipe, CommonModule} from "@angular/common";

@Component({
   selector: 'app-agregar-orden',
   standalone: true,
   imports: [
      FormsModule,
      AsyncPipe,
      CommonModule
   ],
   templateUrl: './agregar-orden.component.html',
   styleUrl: './agregar-orden.component.css'
})
export class AgregarOrdenComponent implements OnInit{


   productosResultados!: Observable<Producto[]>
   productoSeleccionado: string = '';
   cantidadSeleccionada: number = 0;
   productosAgregados: ProductoConCantidad[] = [];


   constructor(private service: DataService) {

   }


   ngOnInit(): void {
      this.productosResultados = this.service.obtenerProductos().pipe(catchError((error: string) => {
         return EMPTY;
      }))
   }

   ngOnChanges(changes: SimpleChanges): void {
      if (changes['productoSeleccionado']) {
         console.log('Nuevo valor de productoSeleccionado:', changes['productoSeleccionado'].currentValue);
      }
   }


   agregarProducto(nombreProducto: string) {
      console.log("LLega hasdta axa")
      this.productosResultados.subscribe(productos => {
         console.log(nombreProducto)
         const producto = productos.find(opcion => opcion.nombre === nombreProducto);
         if (producto) {
            const nuevoProducto: ProductoConCantidad = {
               nombre: this.productoSeleccionado,
               cantidad: this.cantidadSeleccionada,
               id_producto: producto.id_producto,
               categoria: producto.categoria,
               descripcion: producto.descripcion,
               precio : producto.precio
            };
            this.productosAgregados.push(nuevoProducto);
            // Limpiar campos después de agregar el producto si es necesario
            this.productoSeleccionado = '';
            this.cantidadSeleccionada = 0;
            console.log(nuevoProducto);
         }
      });
   }

   puedoEnviar(): boolean {
      return this.productosAgregados.length > 0;
   }

   enviarFormulario(): void {
      console.log('Productos agregados:', this.productosAgregados);
      this.productosAgregados = [];
   }

}
