import {Component, OnInit} from '@angular/core';
import {Categorias} from "../../interfaces/categorias";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {DataService} from "../../core/service/productos-service";
import {ActivatedRoute, Router} from "@angular/router";
import {catchError, EMPTY} from "rxjs";

@Component({
  selector: 'app-editar-producto',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './editar-producto.component.html',
  styleUrl: './editar-producto.component.css'
})
export class EditarProductoComponent implements OnInit {


  opcionesEnum = Object.values(Categorias);

  id_producto: string | undefined

  formularioEdicion!: FormGroup;  // Definir el formulario


  constructor(private service: DataService, private router: Router, private fb: FormBuilder, private route: ActivatedRoute) {

  }


  ngOnInit(): void {

    this.route.queryParams.subscribe(params => {
      this.id_producto = params['id'];
      // Obtén el producto usando el servicio (asumiendo que es un Observable)
      this.service.obtenerProducto(this.id_producto).subscribe(
        (productoResultado: any) => {
          // Asigna valores al formulario
          this.formularioEdicion = this.fb.group({
            nombre: [productoResultado.nombre, Validators.required],
            precio: [productoResultado.precio, Validators.required],
            categoria: [productoResultado.categoria, Validators.required],
            descripcion: [productoResultado.descripcion, Validators.required]
          });
        },
        error => {
          console.error('Error al obtener el producto:', error);
        }
      );

      console.log('ID Producto:', this.id_producto);
    });


  }

  protected readonly FormGroup = FormGroup;

  editarProducto() {
    const nombreControl = this.formularioEdicion.get("nombre")
    const precioControl = this.formularioEdicion.get("precio");
    const categoriaControl = this.formularioEdicion.get("categoria");
    const descripcionControl = this.formularioEdicion.get("descripcion");

    console.log(nombreControl)

    if (nombreControl && nombreControl.value && precioControl && precioControl.value && categoriaControl && categoriaControl.value && descripcionControl && descripcionControl.value) {
      const nombre = nombreControl.value;
      const precio = precioControl.value;
      const categoria = categoriaControl.value;
      const descripcion = descripcionControl.value;

      this.service.editarProducto(this.id_producto, nombre, precio, categoria, descripcion).subscribe(
        (response) => {
          // Manejar la respuesta del servidor, por ejemplo, mostrar un mensaje de éxito.
          console.log('Edición correcta:', response.mensaje);
          this.router.navigate(['/productos'])

        },
        (error) => {
          // Manejar errores, por ejemplo, mostrar un mensaje de error al usuario.
          console.error('Error al editar producto:', error.error.mensaje);
        }
      );


    }
  }


}


