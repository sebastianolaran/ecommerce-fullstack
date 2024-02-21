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


  constructor(private service: DataService, private router: Router, private fb: FormBuilder) {

  }

  onRegister() {
    const nombreControl = this.formularioRegistro.get("nombre")
    const precioControl = this.formularioRegistro.get("precio");
    const categoriaControl = this.formularioRegistro.get("categoria");
    const descripcionControl = this.formularioRegistro.get("descripcion");

    console.log(nombreControl)

    if (nombreControl && nombreControl.value && precioControl && precioControl.value && categoriaControl && categoriaControl.value && descripcionControl && descripcionControl.value)   {
      const nombre = nombreControl.value;
      const precio = precioControl.value;
      const categoria = categoriaControl.value;
      const descripcion = descripcionControl.value;

      this.service.agregarProducto(nombre,precio,categoria,descripcion).subscribe(
        (response: { mensaje: any; }) => {
          // Manejar la respuesta del servidor, por ejemplo, almacenar el token en el almacenamiento local.
          console.log('Token de autenticación recibido:', response.mensaje);
          this.router.navigate(['/productos']);
        },
        (error: any) => {
          // Manejar errores, por ejemplo, mostrar un mensaje de error al usuario.
          console.error('Error al iniciar sesión:', error);
        }
      );
    }
  }


   volver() {
      this.router.navigate(['/productos']);
   }
}
