import {Component} from '@angular/core';
import {FormBuilder, ReactiveFormsModule, Validators} from "@angular/forms";
import {Router, RouterLink} from "@angular/router";
import {Categorias} from "../../interfaces/categorias";
import {DataService} from "../../core/service/productos-service";


@Component({
  selector: 'app-agregar-producto',
  standalone: true,
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './agregar-producto.component.html',
  styleUrl: './agregar-producto.component.css'
})
export class AgregarProductoComponent {

  opcionesEnum = Object.values(Categorias);

  formularioRegistro = this.fb.group({
    nombre: ['', Validators.required],
    precio: ['', [Validators.required]],
    categoria: ['', Validators.required],
    descripcion: ['', Validators.required]
  });

  mostrarError: string | undefined


  constructor(private service: DataService, private router: Router, private fb: FormBuilder) {

  }

   onRegister() {
      // Obtener los controles del formulario
      const nombreControl = this.formularioRegistro.get("nombre");
      const precioControl = this.formularioRegistro.get("precio");
      const categoriaControl = this.formularioRegistro.get("categoria");
      const descripcionControl = this.formularioRegistro.get("descripcion");

      // Verificar si todos los campos requeridos están presentes y tienen valores válidos
      if (
         nombreControl && nombreControl.value &&
         precioControl && precioControl.value &&
         categoriaControl && categoriaControl.value &&
         descripcionControl && descripcionControl.value
      ) {
         // Obtener los valores de los controles
         const nombre = nombreControl.value;
         const precio: number = parseFloat(precioControl.value);
         const categoria = categoriaControl.value;
         const descripcion = descripcionControl.value;

         // Verificar si el precio es válido (mayor que cero)
         if (precio > 0) {
            // Llamar al servicio para agregar el producto
            this.service.agregarProducto(nombre, precioControl.value, categoria, descripcion)
               .subscribe(
                  (response) => {
                     // Verificar si la respuesta contiene un mensaje de éxito
                     if (response.mensaje) {
                        // Redirigir al usuario a la página de productos después de agregar el producto
                        this.router.navigate(['/productos']);
                     } else {
                        // Mostrar el mensaje de error recibido en la respuesta
                        this.mostrarError = response.error;
                        const elemento = document.getElementsByClassName("mensaje-error")[0];
                        elemento.classList.add('activo');
                     }
                  },
                  (error) => {
                     // Manejar el error en caso de que ocurra durante la llamada al servicio
                     console.error("Error al agregar producto:", error);
                     // Aquí podrías mostrar un mensaje genérico de error al usuario si lo deseas
                  }
               );
         } else {
            // Mostrar un mensaje de error si el precio no es válido
            this.mostrarError = "El precio debe ser mayor a 0$";
            const elemento = document.getElementsByClassName("mensaje-error")[0];
            elemento.classList.add('activo');
         }
      } else {
         // Mostrar un mensaje de error si algún campo requerido está vacío o no es válido
         console.error("Todos los campos son obligatorios.");
         // Aquí podrías mostrar un mensaje de error al usuario si lo deseas
      }
   }


   volver() {
      this.router.navigate(['/productos']);
   }
}
