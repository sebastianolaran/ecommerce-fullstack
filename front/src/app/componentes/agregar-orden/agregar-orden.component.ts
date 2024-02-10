import {Component, OnInit, SimpleChanges} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {DataService} from "../../core/service/productos-service";
import {Producto, ProductoConCantidad} from "../../interfaces/producto";
import {catchError, EMPTY, Observable} from "rxjs";
import {AsyncPipe, CommonModule} from "@angular/common";
import {OrdenService} from "../../core/service/orden.service";

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
export class AgregarOrdenComponent implements OnInit {


   productosResultados!: Observable<Producto[]>
   productoSeleccionado: string = '';
   cantidadSeleccionada: number = 0;
   productosAgregados: ProductoConCantidad[] = [];


   constructor(private service: DataService, private ordenService: OrdenService) {

   }


   ngOnInit(): void {
      this.productosResultados = this.service.obtenerProductos().pipe(catchError((error: string) => {
         return EMPTY;
      }))
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
               precio: producto.precio
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
      let valorTotal: number = 0;
      const estado: string = "Pendiente"

      this.ordenService.obtenerNuevoid().subscribe(
         (orden_id: number) => {
            console.log("Nuevo ID de orden:", orden_id);

            // Iterar sobre los productos agregados
            this.productosAgregados.forEach(producto => {
               valorTotal += producto.precio * producto.cantidad;

               // Llamar a la función para agregar el producto con el ID de orden
               this.ordenService.agregarProductoConOrden(orden_id, producto.id_producto, producto.cantidad, producto.precio).subscribe(
                  (response: { mensaje: any }) => {
                     console.log('Producto agregado:', response.mensaje);
                  },
                  (error: any) => {
                     console.error('Error al agregar Producto:', error);
                  }
               );
            });

            this.ordenService.agregarOrden(orden_id, valorTotal, estado).subscribe(
               (response: { mensaje: any }) => {
                  console.log('Producto agregado:', response.mensaje);
               },
               (error: any) => {
                  console.error('Error al agregar Producto:', error);
               }
            );
            // Aquí puedes hacer algo con el valorTotal, si es necesario
            console.log('Valor total de la orden:', valorTotal);
         },
         (error) => {
            console.error("Error al obtener el ID de la orden:", error);
         }
      );
   }


}
