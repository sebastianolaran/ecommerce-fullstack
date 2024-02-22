import {Component, OnInit, SimpleChanges} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {DataService} from "../../core/service/productos-service";
import {Producto, ProductoConCantidad} from "../../interfaces/producto";
import {catchError, EMPTY, Observable} from "rxjs";
import {AsyncPipe, CommonModule} from "@angular/common";
import {OrdenService} from "../../core/service/orden.service";
import {Router} from "@angular/router";

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
   mostrarMensaje: string | undefined = "La cantidad debe ser mayor a 0";


   constructor(private service: DataService, private ordenService: OrdenService,private router: Router) {

   }


   ngOnInit(): void {
      this.productosResultados = this.service.obtenerProductos().pipe(catchError(() => {
         return EMPTY;
      }))
   }

   agregarProducto(nombreProducto: string) {
      const elemento = document.getElementsByClassName("mensaje-error")[0];
      if (this.cantidadSeleccionada > 0) {
         elemento.classList.remove('activo');
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
      else{

         elemento.classList.add('activo');
         this.productoSeleccionado = '';
         this.cantidadSeleccionada = 0;
      }
   }

   puedoEnviar(): boolean {
      return this.productosAgregados.length > 0;
   }

   enviarFormulario(): void {
      let valorTotal: number = 0;
      const estado: string = "Pendiente"

      this.ordenService.obtenerNuevoid().subscribe(
         (id_orden: string) => {
            console.log("Nuevo ID de orden:", id_orden.toString());

            // Iterar sobre los productos agregados
            this.productosAgregados.forEach(producto => {
               valorTotal += producto.precio * producto.cantidad;

               // Llamar a la función para agregar el producto con el ID de orden
               this.ordenService.agregarProductoConOrden(id_orden, producto.id_producto, producto.cantidad, producto.precio).subscribe(
                  (response: { mensaje: any }) => {
                     console.log('Producto agregado:', response.mensaje);
                  },
                  (error: any) => {
                     console.error('Error al agregar Producto:', error);
                  }
               );
            });

            // Aquí puedes hacer algo con el valorTotal, si es necesario
            console.log('Valor total de la orden:', valorTotal);

            // Llamar a la función para agregar la orden con el ID de orden
            this.ordenService.agregarOrden(id_orden, valorTotal, estado).subscribe(
               (response: any) => {
                  console.log('Orden agregada:', response);
                  this.router.navigate(['/ordenes']);
               },
               (error: any) => {
                  console.error('Error al agregar la orden:', error);
               }
            );
         },
         (error) => {
            console.error("Error al obtener el ID de la orden:", error);
         }
      );
   }


   volver() {
      this.router.navigate(['/ordenes'])
   }

   eliminar(nombre:string) {
      this.productosAgregados = this.productosAgregados.filter(producto => producto.nombre !== nombre);
   }

}
